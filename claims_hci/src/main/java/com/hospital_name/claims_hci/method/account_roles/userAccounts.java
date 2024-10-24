/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hospital_name.claims_hci.method.account_roles;

import com.hospital_name.claims_hci.structures.ClaimsResult;
import com.hospital_name.claims_hci.structures.result.UserAccountResult;
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
public class userAccounts {

    @EJB
    private Utility utility;

    // Insert User Account
    public ClaimsResult insertUserAccount(DataSource ds, String userID, String password, String strRequest) {
        ClaimsResult result = this.utility.ClaimsResult();

        try (Connection connection = ds.getConnection(userID, password);
                CallableStatement statement = connection.prepareCall("begin claims_pkg.insert_users_account(?, ?, ?, ?, ?, ?); end;")) {

            UserAccountResult account = new UserAccountResult();
            account = utility.ObjectMapper().readValue(strRequest, UserAccountResult.class);

            statement.setString(1, account.getUserID());
            statement.setString(2, account.getUsername());
            statement.setString(3, account.getPassword());
            statement.setString(4, account.getEmail());
            statement.registerOutParameter(5, OracleTypes.VARCHAR);
            statement.registerOutParameter(6, OracleTypes.VARCHAR);

            statement.execute();

            if (statement.getString(5).equals("RM01_2_0_00000")) {
                result.setSuccess(true);
                result.setMessage(statement.getString("p_message"));
                connection.commit();
            } else {
                result.setMessage(statement.getString("p_message"));
                connection.rollback();
            }

        } catch (SQLException | IOException ex) {
            result.setMessage(ex.toString());
            Logger.getLogger(userAccounts.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

    // Update User Account
    public ClaimsResult updateUserAccount(DataSource ds, String userID, String password, String strRequest) {
        ClaimsResult result = this.utility.ClaimsResult();

        try (Connection connection = ds.getConnection(userID, password);
                CallableStatement statement = connection.prepareCall("begin claims_pkg.update_users_account(?, ?, ?, ?, ?, ?); end;")) {

            UserAccountResult account = new UserAccountResult();
            account = utility.ObjectMapper().readValue(strRequest, UserAccountResult.class);

            statement.setString(1, account.getUserID());
            statement.setString(2, account.getUsername());
            statement.setString(3, account.getPassword());
            statement.setString(4, account.getEmail());
            statement.registerOutParameter(5, OracleTypes.VARCHAR);
            statement.registerOutParameter(6, OracleTypes.VARCHAR);

            statement.execute();

            if (statement.getString(5).equals("RM01_2_0_00002")) {
                result.setSuccess(true);
                result.setMessage(statement.getString("p_message"));
                connection.commit();
            } else {
                result.setMessage(statement.getString("p_message"));
                connection.rollback();
            }

        } catch (SQLException | IOException ex) {
            result.setMessage(ex.toString());
            Logger.getLogger(userAccounts.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }
    
    
     // Get User Accounts
    public ClaimsResult getUserAccount(DataSource ds, String userID, String password, String strRequest) {
        ArrayList<UserAccountResult> list = new ArrayList<>();
        ClaimsResult result = this.utility.ClaimsResult();

        try (Connection connection = ds.getConnection(userID, password);
                CallableStatement statement = connection.prepareCall("begin :lib := claims_pkg.get_users_account(?); end;")) {

            UserAccountResult request = new UserAccountResult();
            request = utility.ObjectMapper().readValue(strRequest, UserAccountResult.class);

            statement.registerOutParameter("lib", OracleTypes.CURSOR);
            statement.setString(1, request.getUserID());
            statement.execute();

            try (ResultSet resultSet = (ResultSet) statement.getObject("lib")) {
                while (resultSet.next()) {
                    UserAccountResult profile = new UserAccountResult();
                    profile.setUserID(resultSet.getString("USER_ID"));
                    profile.setEmail(resultSet.getString("EMAIL"));
                    profile.setPassword(resultSet.getString("PASSWORD"));
                    profile.setUsername(resultSet.getString("USERNAME"));
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
            Logger.getLogger(userAccounts.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

    // Delete User Account
    public ClaimsResult deleteUserAccount(DataSource ds, String userID, String password, String strRequest) {
        ClaimsResult result = this.utility.ClaimsResult();

        try (Connection connection = ds.getConnection(userID, password);
                CallableStatement statement = connection.prepareCall("begin claims_pkg.delete_users_account(?, ?, ?); end;")) {

            UserAccountResult res = new UserAccountResult();
            res = utility.ObjectMapper().readValue(strRequest, UserAccountResult.class);

            statement.setString(1, res.getUserID());
            statement.registerOutParameter(2, OracleTypes.VARCHAR);
            statement.registerOutParameter(3, OracleTypes.VARCHAR);

            statement.execute();

            if (statement.getString(2).equals("RM01_2_0_00005")) {
                result.setSuccess(true);
                result.setMessage(statement.getString("p_message"));
                connection.commit();
            } else {
                result.setMessage(statement.getString("p_message"));
                connection.rollback();
            }

        } catch (SQLException | IOException ex) {
            result.setMessage(ex.toString());
            Logger.getLogger(userAccounts.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

}
