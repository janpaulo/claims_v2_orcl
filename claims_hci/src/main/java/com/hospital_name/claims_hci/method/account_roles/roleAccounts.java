///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.hospital_name.claims_hci.method.account_roles;
//
///**
// *
// * @author User
// */
//public class roleAccounts {
//    
//}
package com.hospital_name.claims_hci.method.account_roles;

import com.hospital_name.claims_hci.structures.ClaimsResult;
import com.hospital_name.claims_hci.structures.result.AccountRoleResult;
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

@RequestScoped
public class roleAccounts {

    @EJB
    private Utility utility;

    // Insert Account Role
    public ClaimsResult insertAccountRole(DataSource ds, String userID, String password, String strRequest) {
        ClaimsResult result = this.utility.ClaimsResult();
        try (Connection connection = ds.getConnection(userID, password);
                CallableStatement statement = connection.prepareCall("begin claims_pkg.insert_account_role(?, ?, ?, ?, ?, ?); end;")) {

            AccountRoleResult res = new AccountRoleResult();
            res = utility.ObjectMapper().readValue(strRequest, AccountRoleResult.class);

            statement.setString(1, res.getRoleID());
            statement.setString(2, res.getRoleName());
            statement.setString(3, res.getRoleDesc());
            statement.setString(4, res.getStatus());
            statement.setString(5, res.getIsActive());
            statement.setDate(6, new java.sql.Date(res.getDateCreated().getTime()));
            statement.registerOutParameter(7, OracleTypes.VARCHAR);
            statement.registerOutParameter(8, OracleTypes.VARCHAR);
            statement.execute();

            if (statement.getString(7).equals("RM01_3_0_00001")) {
                result.setSuccess(true);
                result.setMessage(statement.getString("p_message"));
                connection.commit();
            } else {
                result.setMessage(statement.getString("p_message"));
                connection.rollback();
            }

        } catch (SQLException | IOException ex) {
            result.setMessage(ex.toString());
            Logger.getLogger(roleAccounts.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

    // Get Account Role
    public ClaimsResult getUserProfile(DataSource ds, String userID, String password, String strRequest) {
        ArrayList<AccountRoleResult> list = new ArrayList<>();
        ClaimsResult result = this.utility.ClaimsResult();

        try (Connection connection = ds.getConnection(userID, password);
                CallableStatement statement = connection.prepareCall("begin :lib := claims_pkg.AccountRoleResult(?); end;")) {

            AccountRoleResult request = new AccountRoleResult();
            request = utility.ObjectMapper().readValue(strRequest, AccountRoleResult.class);

            statement.registerOutParameter("lib", OracleTypes.CURSOR);
            statement.setString(1, request.getRoleID());
            statement.execute();

            try (ResultSet resultSet = (ResultSet) statement.getObject("lib")) {
                while (resultSet.next()) {
                    AccountRoleResult profile = new AccountRoleResult();
                    profile.setRoleID(resultSet.getString("ROLE_ID"));
                    profile.setRoleDesc(resultSet.getString("ROLE_DESC"));
                    profile.setRoleName(resultSet.getString("ROLE_NAME"));
                    profile.setDateCreated(resultSet.getDate("DATE_CREATED"));
                    profile.setStatus(resultSet.getString("STATUS"));
                    profile.setIsActive(resultSet.getString("IS_ACTIVE"));
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

    // Update Account Role
    public ClaimsResult updateAccountRole(DataSource ds, String userID, String password, String strRequest) {
        ClaimsResult result = this.utility.ClaimsResult();

        try (Connection connection = ds.getConnection(userID, password);
                CallableStatement statement = connection.prepareCall("begin claims_pkg.update_account_role(?, ?, ?, ?, ?, ?); end;")) {

            AccountRoleResult res = new AccountRoleResult();
            res = utility.ObjectMapper().readValue(strRequest, AccountRoleResult.class);

            statement.setString(1, res.getRoleID());
            statement.setString(2, res.getRoleName());
            statement.setString(3, res.getRoleDesc());
            statement.setString(4, res.getStatus());
            statement.setString(5, res.getIsActive());
            statement.setDate(6, new java.sql.Date(res.getDateCreated().getTime()));
            statement.registerOutParameter(7, OracleTypes.VARCHAR);
            statement.registerOutParameter(8, OracleTypes.VARCHAR);

            statement.execute();

            if (statement.getString(7).equals("RM01_3_0_00002")) {
                result.setSuccess(true);
                result.setMessage(statement.getString("p_message"));
                connection.commit();
            } else {
                result.setMessage(statement.getString("p_message"));
                connection.rollback();
            }

        } catch (SQLException | IOException ex) {
            result.setMessage(ex.toString());
            Logger.getLogger(roleAccounts.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

    // Delete Account Role
    public ClaimsResult deleteAccountRole(DataSource ds, String userID, String password, String strRequest) {
        ClaimsResult result = this.utility.ClaimsResult();

        try (Connection connection = ds.getConnection(userID, password);
                CallableStatement statement = connection.prepareCall("begin claims_pkg.delete_account_role(?, ?, ?); end;")) {
            AccountRoleResult res = new AccountRoleResult();
            res = utility.ObjectMapper().readValue(strRequest, AccountRoleResult.class);

            statement.setString(1, res.getRoleID());
            statement.registerOutParameter(2, OracleTypes.VARCHAR);
            statement.registerOutParameter(3, OracleTypes.VARCHAR);

            statement.execute();
            if (statement.getString(4).equals("RM01_3_0_00005")) {
                result.setSuccess(true);
                result.setMessage(statement.getString("p_message"));
                connection.commit();
            } else {
                result.setMessage(statement.getString("p_message"));
                connection.rollback();
            }

        } catch (SQLException | IOException ex) {
            result.setMessage(ex.toString());
            Logger.getLogger(roleAccounts.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }
}
