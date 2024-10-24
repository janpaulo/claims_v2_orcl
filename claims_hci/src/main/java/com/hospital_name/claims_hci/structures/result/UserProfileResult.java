/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hospital_name.claims_hci.structures.result;

/**
 *
 * @author User
 */

import java.util.Date;

public class UserProfileResult {
    private String userID;
    private String accreNum;
    private String hciName;
    private String hciDesc;
    private Date dateCreated;
    private String hciCypherkey;
    private String roleID;
    private String qid;

    // Getters and Setters
    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getAccreNum() {
        return accreNum;
    }

    public void setAccreNum(String accreNum) {
        this.accreNum = accreNum;
    }

    public String getHciName() {
        return hciName;
    }

    public void setHciName(String hciName) {
        this.hciName = hciName;
    }

    public String getHciDesc() {
        return hciDesc;
    }

    public void setHciDesc(String hciDesc) {
        this.hciDesc = hciDesc;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getHciCypherkey() {
        return hciCypherkey;
    }

    public void setHciCypherkey(String hciCypherkey) {
        this.hciCypherkey = hciCypherkey;
    }

    public String getRoleID() {
        return roleID;
    }

    public void setRoleID(String roleID) {
        this.roleID = roleID;
    }

    public String getQID() {
        return qid;
    }

    public void setQID(String qid) {
        this.qid = qid;
    }
}
