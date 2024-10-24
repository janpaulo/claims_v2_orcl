/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hospital_name.claims_hci.method.account_roles;

import com.hospital_name.claims_hci.structures.ClaimsResult;
import com.hospital_name.claims_hci.structures.result.UserProfileResult;
import com.hospital_name.claims_hci.utilities.Utility;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import javax.enterprise.context.RequestScoped;
import javax.ejb.EJB;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author User
 */
@RequestScoped
public class accountProfile {

    @EJB
    private Utility utility;

    // Insert User Profile
    public ClaimsResult insertUserProfile(DataSource ds, String userID, String password, String strRequest) {
        ClaimsResult result = this.utility.ClaimsResult();

        try (Connection connection = ds.getConnection(userID, password);
                CallableStatement statement = connection.prepareCall("begin claims_pkg.insert_user_profile(?, ?, ?, ?, ?, ?, ?, ?, ?, ?); end;")) {
            UserProfileResult profile = new UserProfileResult();
            profile = utility.ObjectMapper().readValue(strRequest, UserProfileResult.class);

            statement.setString(1, profile.getUserID());
            statement.setString(2, profile.getAccreNum());
            statement.setString(3, profile.getHciName());
            statement.setString(4, profile.getHciDesc());
            statement.setDate(5, new java.sql.Date(profile.getDateCreated().getTime()));
            statement.setString(6, profile.getHciCypherkey());
            statement.setString(7, profile.getRoleID());
            statement.setString(8, profile.getQID());
            statement.registerOutParameter(9, OracleTypes.VARCHAR);
            statement.registerOutParameter(10, OracleTypes.VARCHAR);

            statement.execute();

            if (statement.getString(9).equals("RM01_1_0_00000")) {
                result.setSuccess(true);
                result.setMessage(statement.getString("p_message"));
                connection.commit();
            } else {
                result.setMessage(statement.getString("p_message"));
                connection.rollback();
            }

        } catch (SQLException | IOException ex) {
            result.setMessage(ex.toString());
            Logger.getLogger(accountProfile.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }
    
    
    // Update User Profile
    public ClaimsResult updateUserProfile(DataSource ds, String userID, String password, String strRequest) {
        ClaimsResult result = this.utility.ClaimsResult();

        try (Connection connection = ds.getConnection(userID, password);
                CallableStatement statement = connection.prepareCall("begin claims_pkg.update_user_profile(?, ?, ?, ?, ?, ?, ?, ?, ?, ?); end;")) {
            UserProfileResult profile = new UserProfileResult();
            profile = utility.ObjectMapper().readValue(strRequest, UserProfileResult.class);

            statement.setString(1, profile.getUserID());
            statement.setString(2, profile.getAccreNum());
            statement.setString(3, profile.getHciName());
            statement.setString(4, profile.getHciDesc());
            statement.setDate(5, new java.sql.Date(profile.getDateCreated().getTime()));
            statement.setString(6, profile.getHciCypherkey());
            statement.setString(7, profile.getRoleID());
            statement.setString(8, profile.getQID());
            statement.registerOutParameter(9, OracleTypes.VARCHAR);
            statement.registerOutParameter(10, OracleTypes.VARCHAR);

            statement.execute();

            if (statement.getString(9).equals("RM01_1_0_00002")) {
                result.setSuccess(true);
                result.setMessage(statement.getString("p_message"));
                connection.commit();
            } else {
                result.setMessage(statement.getString("p_message"));
                connection.rollback();
            }

        } catch (SQLException | IOException ex) {
            result.setMessage(ex.toString());
            Logger.getLogger(accountProfile.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }
    

    // Get User Profile
    public ClaimsResult getUserProfile(DataSource ds, String userID, String password, String strRequest) {
        ArrayList<UserProfileResult> list = new ArrayList<>();
        ClaimsResult result = this.utility.ClaimsResult();

        try (Connection connection = ds.getConnection(userID, password);
                CallableStatement statement = connection.prepareCall("begin :lib := claims_pkg.get_user_profile(?); end;")) {

            UserProfileResult request = new UserProfileResult();
            request = utility.ObjectMapper().readValue(strRequest, UserProfileResult.class);

            statement.registerOutParameter("lib", OracleTypes.CURSOR);
            statement.setString(1, request.getUserID());
            statement.execute();

            try (ResultSet resultSet = (ResultSet) statement.getObject("lib")) {
                while (resultSet.next()) {
                    UserProfileResult profile = new UserProfileResult();
                    profile.setAccreNum(resultSet.getString("accre_num"));
                    profile.setHciName(resultSet.getString("hci_name"));
                    profile.setHciDesc(resultSet.getString("hci_desc"));
                    profile.setDateCreated(resultSet.getDate("date_created"));
                    profile.setHciCypherkey(resultSet.getString("hci_cyperkey"));
                    profile.setRoleID(resultSet.getString("role_id"));
                    profile.setQID(resultSet.getString("qid"));
                    list.add(profile);
                }
            }

            if (list.isEmpty()) {
                result.setSuccess(false);
                result.setMessage("NO RECORD FOUND");
            } else {
                result.setMessage("SUCCESSFULLY FOUND THE RECORD/S");
                result.setResult(this.utility.ObjectMapper().writeValueAsString(list));
                result.setSuccess(true);
            }
        } catch (SQLException | IOException ex) {
            result.setMessage(ex.toString());
            Logger.getLogger(accountProfile.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

}
