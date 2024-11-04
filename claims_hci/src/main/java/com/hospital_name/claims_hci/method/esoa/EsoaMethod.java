package com.hospital_name.claims_hci.method.esoa;

import com.hospital_name.claims_hci.structures.ClaimsResult;// Assume this is a structure for claim data
import com.hospital_name.claims_hci.structures.result.RequestEsoa;
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
public class EsoaMethod {

    @EJB
    private Utility utility;

    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

    // Insert Claim
    public ClaimsResult insertEsoa(DataSource ds, String userID, String password, String strRequest) {

        ClaimsResult result = utility.ClaimsResult();

        try (Connection connection = ds.getConnection(userID, password)) {
            try {
                connection.setAutoCommit(false);
                CallableStatement statement = connection.prepareCall(
                        "begin claims.esoa_pkg.insert_esoa(:p_esoa_qid, :p_accre_no, :p_prof_fee, :p_total_amount, "
                        + ":p_sum_philhealth_amount, :p_prof_philhealth_amount, :p_json, :p_code, :p_message); end;");

                RequestEsoa request = utility.ObjectMapper().readValue(strRequest, RequestEsoa.class);

                statement.setString("p_esoa_qid", request.getEsoaQID());
                statement.setString("p_accre_no", request.getAccreNo());
                statement.setBigDecimal("p_prof_fee", request.getProfFee());
                statement.setBigDecimal("p_total_amount", request.getTotalAmount());
                statement.setBigDecimal("p_sum_philhealth_amount", request.getSumPhilhealthAmount());
                statement.setBigDecimal("p_prof_philhealth_amount", request.getProfPhilhealthAmount());
                statement.setClob("p_json", new javax.sql.rowset.serial.SerialClob(request.getJson().toCharArray()));
                statement.registerOutParameter("p_code", OracleTypes.VARCHAR);
                statement.registerOutParameter("p_message", OracleTypes.VARCHAR);
                statement.executeUpdate();

                String code = statement.getString("p_code");
                result.setMessage(statement.getString("p_message"));
                if ("RM01_4_0_00000".equals(code)) {
                    result.setSuccess(true);
                    connection.commit();
                } else {
                    connection.rollback();
                }

            } catch (SQLException ex) {
                connection.rollback();
                Logger.getLogger(EsoaMethod.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(EsoaMethod.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (SQLException ex) {
            Logger.getLogger(EsoaMethod.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

    // Update updateEsoa
    public ClaimsResult updateEsoa(DataSource ds, String userID, String password, String strRequest) {

        ClaimsResult result = utility.ClaimsResult();

        try (Connection connection = ds.getConnection(userID, password)) {
            try {
                connection.setAutoCommit(false);
                CallableStatement statement = connection.prepareCall(
                        "begin claims.esoa_pkg.update_esoa(:p_esoa_id, :p_esoa_qid, :p_accre_no, :p_prof_fee, "
                        + ":p_total_amount, :p_sum_philhealth_amount, :p_prof_philhealth_amount, "
                        + ":p_json, :p_code, :p_message); end;");

                RequestEsoa request = utility.ObjectMapper().readValue(strRequest, RequestEsoa.class);

                statement.setString("p_esoa_id", request.getEsoaID());
                statement.setString("p_esoa_qid", request.getEsoaQID());
                statement.setString("p_accre_no", request.getAccreNo());
                statement.setBigDecimal("p_prof_fee", request.getProfFee());
                statement.setBigDecimal("p_total_amount", request.getTotalAmount());
                statement.setBigDecimal("p_sum_philhealth_amount", request.getSumPhilhealthAmount());
                statement.setBigDecimal("p_prof_philhealth_amount", request.getProfPhilhealthAmount());
                statement.setClob("p_json", new javax.sql.rowset.serial.SerialClob(request.getJson().toCharArray()));
                statement.registerOutParameter("p_code", OracleTypes.VARCHAR);
                statement.registerOutParameter("p_message", OracleTypes.VARCHAR);
                statement.executeUpdate();

                String code = statement.getString("p_code");
                result.setMessage(statement.getString("p_message"));
                if ("RM01_4_0_00000".equals(code)) {
                    result.setSuccess(true);
                    connection.commit();
                } else {
                    connection.rollback();
                }

            } catch (SQLException ex) {
                connection.rollback();
                Logger.getLogger(EsoaMethod.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(EsoaMethod.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (SQLException ex) {
            Logger.getLogger(EsoaMethod.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

    // Get ESOA
    public ClaimsResult getEsoa(DataSource ds, String userID, String password, String esoaID) {
        ArrayList<RequestEsoa> list = new ArrayList<>();
        ClaimsResult result = this.utility.ClaimsResult(); // Assuming you have a utility method for EsoaResult

        try (Connection connection = ds.getConnection(userID, password);
                CallableStatement statement = connection.prepareCall("begin :lib := claims.esoa_pkg.get_esoa(:esoa_id); end;")) {

            statement.registerOutParameter("lib", OracleTypes.CURSOR);
            statement.setString("esoa_id", esoaID); // Assuming esoaID is passed
            statement.execute();

            try (ResultSet resultSet = (ResultSet) statement.getObject("lib")) {
                while (resultSet.next()) {
                    RequestEsoa esoa = new RequestEsoa();
                    esoa.setEsoaID(resultSet.getString("ESOA_ID")); 
                    esoa.setEsoaQID(resultSet.getString("ESOA_QID"));
                    esoa.setAccreNo(resultSet.getString("accre_no"));
                    esoa.setProfFee(resultSet.getBigDecimal("prof_fee"));
                    esoa.setTotalAmount(resultSet.getBigDecimal("total_amount"));
                    esoa.setSumPhilhealthAmount(resultSet.getBigDecimal("sum_philhealth_amount"));
                    esoa.setProfPhilhealthAmount(resultSet.getBigDecimal("prof_philhealth_amount"));
                    esoa.setJson(resultSet.getString("json"));
                    esoa.setDateCreated(resultSet.getString("date_created"));
                    list.add(esoa);
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
            Logger.getLogger(EsoaMethod.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

}
