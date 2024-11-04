///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
package com.hospital_name.claims_hci.method.account_roles;

import com.hospital_name.claims_hci.structures.ClaimsResult;
import com.hospital_name.claims_hci.structures.result.UserAccountResult;
import com.hospital_name.claims_hci.utilities.Utility;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import javax.enterprise.context.RequestScoped;
import javax.ejb.EJB;
import oracle.jdbc.OracleTypes;

@RequestScoped
public class authentication {

    @EJB
    private Utility utility;

    // Get Account Role
    public ClaimsResult authentication(DataSource ds, String userID, String password, String strRequest) {
        ClaimsResult result = this.utility.ClaimsResult();
        try (Connection connection = ds.getConnection(userID, password);
               CallableStatement statement = connection.prepareCall("begin :result := claims.AUTH_PKG.AUTHENTICATE_USER(:p_username, :p_password); end;")) {

            UserAccountResult request = utility.ObjectMapper().readValue(strRequest, UserAccountResult.class);

            // Register the output parameter as a cursor at position 3
            statement.registerOutParameter("result", OracleTypes.CURSOR);

            // Set input parameters using positional binding
            statement.setString("p_username", request.getUserID()); // p_userid
            statement.setString("p_password", request.getPassword()); // p_password

            // Execute the statement
            statement.execute();

            // Retrieve the result set from the output parameter
            try (ResultSet resultSet = (ResultSet) statement.getObject("result")) { // Get the result from the cursor
                if (resultSet.next()) {
                    UserAccountResult profile = new UserAccountResult();
                    profile.setUserID(resultSet.getString("USER_ID"));
                    profile.setEmail(resultSet.getString("EMAIL"));
//                    profile.setPassword(resultSet.getString("PASSWORD"));
                    profile.setUsername(resultSet.getString("USERNAME"));

                    result.setMessage("SUCCESSFULLY FOUND THE RECORD/S");
                    result.setResult(this.utility.ObjectMapper().writeValueAsString(profile));
                    result.setSuccess(true);
                } else {
                    result.setSuccess(false);
                    result.setMessage("NO RECORD FOUND");
                }
            }

        } catch (SQLException | IOException ex) {
            result.setMessage(ex.toString());
            Logger.getLogger(authentication.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }
}
