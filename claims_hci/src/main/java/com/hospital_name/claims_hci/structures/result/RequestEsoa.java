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
import java.math.BigDecimal;

public class RequestEsoa {

    private String esoaID;
    private String esoaQID;
    private String accreNo;
    private BigDecimal profFee;
    private BigDecimal totalAmount;
    private BigDecimal sumPhilhealthAmount;
    private BigDecimal profPhilhealthAmount;
    private String json; // JSON 
    private String dateCreated; // JSON string

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    // Getters and Setters
    public String getEsoaID() {
        return esoaID;
    }

    public void setEsoaID(String esoaID) {
        this.esoaID = esoaID;
    }

    public String getEsoaQID() {
        return esoaQID;
    }

    public void setEsoaQID(String esoaQID) {
        this.esoaQID = esoaQID;
    }

    public String getAccreNo() {
        return accreNo;
    }

    public void setAccreNo(String accreNo) {
        this.accreNo = accreNo;
    }

    public BigDecimal getProfFee() {
        return profFee;
    }

    public void setProfFee(BigDecimal profFee) {
        this.profFee = profFee;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getSumPhilhealthAmount() {
        return sumPhilhealthAmount;
    }

    public void setSumPhilhealthAmount(BigDecimal sumPhilhealthAmount) {
        this.sumPhilhealthAmount = sumPhilhealthAmount;
    }

    public BigDecimal getProfPhilhealthAmount() {
        return profPhilhealthAmount;
    }

    public void setProfPhilhealthAmount(BigDecimal profPhilhealthAmount) {
        this.profPhilhealthAmount = profPhilhealthAmount;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }
}
