package com.hospital_name.claims_hci.structures.result;

import java.util.Date;

public class ClaimsResultData {
    private String claimID;               // Assuming there's a claimID field
    private String claimQID;
    private String accreNo;
    private Date dateCreated;
    private String json;
    private String seriesNo;
    private String memPin;
    private String dateAdmitted;

    // Getter and Setter for claimID
    public String getClaimID() {
        return claimID;
    }

    public void setClaimID(String claimID) {
        this.claimID = claimID;
    }

    // Getter and Setter for claimQID
    public String getClaimQID() {
        return claimQID;
    }

    public void setClaimQID(String claimQID) {
        this.claimQID = claimQID;
    }

    // Getter and Setter for accreNo
    public String getAccreNo() {
        return accreNo;
    }

    public void setAccreNo(String accreNo) {
        this.accreNo = accreNo;
    }

    // Getter and Setter for dateCreated
    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    // Getter and Setter for json
    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    // Getter and Setter for seriesNo
    public String getSeriesNo() {
        return seriesNo;
    }

    public void setSeriesNo(String seriesNo) {
        this.seriesNo = seriesNo;
    }

    // Getter and Setter for memPin
    public String getMemPin() {
        return memPin;
    }

    public void setMemPin(String memPin) {
        this.memPin = memPin;
    }

    public String getDateAdmitted() {
        return dateAdmitted;
    }

    public void setDateAdmitted(String dateAdmitted) {
        this.dateAdmitted = dateAdmitted;
    }
    
}
