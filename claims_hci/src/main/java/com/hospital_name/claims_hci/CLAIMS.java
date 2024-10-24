/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hospital_name.claims_hci;

import com.hospital_name.claims_hci.method.account_roles.authentication;
import com.hospital_name.claims_hci.structures.ClaimsResult;
import com.hospital_name.claims_hci.utilities.Utility;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.sql.DataSource;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author User
 */
@Path("MAIN")
@RequestScoped
public class CLAIMS {

    @Resource(lookup = "jdbc/claimsDS")
    private DataSource claimsDS;
    
    
    
    @Inject
    private authentication authentication;

    @EJB
    private Utility utility;

    @GET
    @Path("GetWSVersion")
    @Produces({"text/plain"})
    public String getWSVersion(@HeaderParam("userID") String userID, @HeaderParam("password") String password) {
        return "VERSION 01.00.00.202401";
    }

    @POST
    @Path("Authentication")
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public ClaimsResult authentication(@HeaderParam("UserID") String userID, @HeaderParam("Password") String password, String strRequest) {
        return this.authentication.authentication(this.claimsDS, userID, password, strRequest);
    }

}
