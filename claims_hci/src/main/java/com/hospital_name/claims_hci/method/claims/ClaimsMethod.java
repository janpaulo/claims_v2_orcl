package com.hospital_name.claims_hci.method.claims;

import com.hospital_name.claims_hci.structures.ClaimsResult;
import com.hospital_name.claims_hci.structures.result.ClaimsResultData; // Assume this is a structure for claim data
import com.hospital_name.claims_hci.utilities.Utility;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import javax.enterprise.context.RequestScoped;
import javax.ejb.EJB;
import oracle.jdbc.OracleTypes;

@RequestScoped
public class ClaimsMethod {

    @EJB
    private Utility utility;

    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
    // Insert Claim
    public ClaimsResult AddClaim(DataSource ds, String userID, String password, String strRequest) {

        ClaimsResult result = utility.ClaimsResult();
        try (Connection connection = ds.getConnection(userID, password)) {

            try {
                connection.setAutoCommit(false);
                CallableStatement statement = connection.prepareCall(
                        "begin claims.claims_pkg.insert_claim(:p_claim_qid, :p_accre_no, :p_json, :p_series_no, :p_mem_pin, :p_date_admited, :p_code, :p_message); end;");

                // Assuming you have a class that maps the request data similar to GetRequestEmployees
                ClaimsResultData request = utility.ObjectMapper().readValue(strRequest, ClaimsResultData.class);

                statement.setString("p_claim_qid", request.getClaimQID());
                statement.setString("p_accre_no", request.getAccreNo());
                statement.setClob("p_json", new javax.sql.rowset.serial.SerialClob(request.getJson().toCharArray()));
                statement.setString("p_series_no", request.getSeriesNo());
                statement.setString("p_mem_pin", request.getMemPin());
                statement.setDate("p_date_admited", new Date(sdf.parse(request.getDateAdmitted()).getTime()));
                statement.registerOutParameter("p_code", OracleTypes.VARCHAR);
                statement.registerOutParameter("p_message", OracleTypes.VARCHAR);
                statement.execute();

                String responseID = statement.getString("p_code");

                if ("RM01_2_0_00000".equals(responseID)) {
                    connection.commit();
                    result.setSuccess(true);
                    result.setResult(responseID);
                    result.setMessage(statement.getString("p_message"));
                } else {
                    result.setSuccess(false);
                    connection.rollback();
                    result.setMessage(statement.getString("p_message"));
                }

            } catch (SQLException | IOException ex) {
                result.setMessage(ex.toString());
                Logger.getLogger(ClaimsMethod.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(ClaimsMethod.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClaimsMethod.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    // Update Claim
    public ClaimsResult updateClaim(DataSource ds, String userID, String password, String strRequest) {

        ClaimsResult result = utility.ClaimsResult();

        try (Connection connection = ds.getConnection(userID, password)) {
            try {
                connection.setAutoCommit(false);
                CallableStatement statement = connection.prepareCall(
                        "begin claims.claims_pkg.update_claim(:p_claim_id, :p_claim_qid, :p_accre_no,"
                        + ":p_json, :p_series_no, :p_mem_pin, :p_date_admited, :p_code, :p_message); end;");

                ClaimsResultData request = utility.ObjectMapper().readValue(strRequest, ClaimsResultData.class);

                statement.setString("p_claim_id", request.getClaimID());
                statement.setString("p_claim_qid", request.getClaimQID());
                statement.setString("p_accre_no", request.getAccreNo());
                statement.setClob("p_json", new javax.sql.rowset.serial.SerialClob(request.getJson().toCharArray()));
                statement.setString("p_series_no", request.getSeriesNo());
                statement.setString("p_mem_pin", request.getMemPin());
                statement.setDate("p_date_admited", new Date(sdf.parse(request.getDateAdmitted()).getTime()));
                statement.registerOutParameter("p_code", OracleTypes.VARCHAR);
                statement.registerOutParameter("p_message", OracleTypes.VARCHAR);
                statement.executeUpdate();

                String code = statement.getString("p_code");
                result.setMessage(statement.getString("p_message"));
                if ("RM01_3_0_00000".equals(code)) {
                    result.setSuccess(true);
                    connection.commit();
                } else {
                    connection.rollback();
                }

            } catch (SQLException ex) {
                connection.rollback();
                Logger.getLogger(ClaimsMethod.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(ClaimsMethod.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(ClaimsMethod.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ClaimsMethod.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

    // Get Claim
    public ClaimsResult getClaim(DataSource ds, String userID, String password, String claimID) {
        ArrayList<ClaimsResultData> list = new ArrayList<>();
        ClaimsResult result = this.utility.ClaimsResult();

        try (Connection connection = ds.getConnection(userID, password);
                CallableStatement statement = connection.prepareCall("begin :lib := claims.claims_pkg.get_claims(:claim_id); end;")) {

            statement.registerOutParameter("lib", OracleTypes.CURSOR);
            statement.setString("claim_id", claimID); // Assuming claimID is passed
            statement.execute();

            try (ResultSet resultSet = (ResultSet) statement.getObject("lib")) {
                while (resultSet.next()) {
                    ClaimsResultData claim = new ClaimsResultData();
                    claim.setClaimQID(resultSet.getString("claim_qid"));
                    claim.setAccreNo(resultSet.getString("accre_no"));
                    claim.setDateCreated(resultSet.getDate("date_created"));
                    claim.setJson(resultSet.getString("json"));
                    claim.setSeriesNo(resultSet.getString("series_no"));
                    claim.setMemPin(resultSet.getString("mem_pin"));
                    claim.setDateAdmitted(resultSet.getString("date_admitted"));
                    list.add(claim);
                }
            }

            if (list.isEmpty()) {
                result.setSuccess(false);
                result.setResult("[]");
                result.setMessage("NO RECORD FOUND");
            } else {
                result.setMessage("SUCCESSFULLY FOUND THE RECORD/S");
                result.setResult(this.utility.ObjectMapper().writeValueAsString(list));
                result.setSuccess(true);
            }
        } catch (SQLException | IOException ex) {
            result.setMessage(ex.toString());
            Logger.getLogger(ClaimsMethod.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }
    
}
