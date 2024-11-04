/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hospital_name.claims_hci;

import com.hospital_name.claims_hci.method.account_roles.accountProfile;
import com.hospital_name.claims_hci.method.account_roles.authentication;
import com.hospital_name.claims_hci.method.account_roles.roleAccounts;
import com.hospital_name.claims_hci.method.claims.ClaimsMethod;
import com.hospital_name.claims_hci.method.esoa.EsoaMethod;
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
    
    
    @Inject
    private ClaimsMethod claimsMethod;
    
    @Inject
    private accountProfile profile;
    
    @Inject
    private roleAccounts roleAccounts;
    
    
    @Inject
    private EsoaMethod esoaMethod;
    
    

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
    
    /////////////////////// CLAIMS //////////////////////////////////////
    @GET
    @Path("GetClaims/{claimID: .*}")
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public ClaimsResult ClaimsMethod(@HeaderParam("UserID") String userID, @HeaderParam("Password") String password,@PathParam("claimID") String claimID) {
        return this.claimsMethod.getClaim(this.claimsDS, userID, password, claimID);
    }
    
    @POST
    @Path("InsertClaims")
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public ClaimsResult AddClaim(@HeaderParam("UserID") String userID, @HeaderParam("Password") String password, String strRequest) {
        return this.claimsMethod.AddClaim(this.claimsDS, userID, password, strRequest);
    }
    
    @POST
    @Path("UpdateClaims")
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public ClaimsResult updateClaim(@HeaderParam("UserID") String userID, @HeaderParam("Password") String password,String strRequest) {
        return this.claimsMethod.updateClaim(this.claimsDS, userID, password, strRequest);
    }
    
    
    ///////////////////////-------------------------------- ESOA  //////////////////////////////////////
    @GET
    @Path("GetEsoa/{esoaID: .*}")
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public ClaimsResult getEsoa(@HeaderParam("UserID") String userID, @HeaderParam("Password") String password,@PathParam("esoaID") String esoaID) {
        return this.esoaMethod.getEsoa(this.claimsDS, userID, password, esoaID);
    }
    
    
    //////////////////////////////////////////////////////////// USER PROFILE  //////////////////////////////////////
    @GET
    @Path("GetUserProfile/{UserID: .*}")
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public ClaimsResult getUserProfile(@HeaderParam("UserID") String userID, @HeaderParam("Password") String password,@PathParam("UserID") String UserID) {
        return this.profile.getUserProfile(this.claimsDS, userID, password, UserID);
    }
    
    
    
    
    ///////////////////////-------------------------------- USER ROLE  //////////////////////////////////////
    @GET
    @Path("GetUserRole/{roleID: .*}")
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public ClaimsResult getUserRole(@HeaderParam("UserID") String userID, @HeaderParam("Password") String password,@PathParam("roleID") String roleID) {
        return this.roleAccounts.getUserRole(this.claimsDS, userID, password, roleID);
    }
    
    
    
    
    
    
    
    
    
    

}
