/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hospital_name.claims_hci.utilities;

import java.text.SimpleDateFormat;
import javax.ejb.Singleton;
import javax.enterprise.context.ApplicationScoped;
import com.hospital_name.claims_hci.structures.ClaimsResult;
import org.codehaus.jackson.map.ObjectMapper;

/**
 *
 * @author User
 */

@ApplicationScoped
@Singleton

public class Utility {
    public ClaimsResult ClaimsResult() {
    return new ClaimsResult();
  }
  
  public ObjectMapper ObjectMapper() {
    return new ObjectMapper();
  }
  
  public SimpleDateFormat SimpleDateFormat(String pattern) {
    return new SimpleDateFormat(pattern);
  }
}
