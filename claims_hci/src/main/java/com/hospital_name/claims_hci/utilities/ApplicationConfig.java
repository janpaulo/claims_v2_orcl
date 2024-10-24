/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hospital_name.claims_hci.utilities;

/**
 *
 * @author User
 */
import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import com.hospital_name.claims_hci.CLAIMS;

@ApplicationPath("/*")
public class ApplicationConfig extends Application {
  public Set<Class<?>> getClasses() {
    Set<Class<?>> resources = new HashSet<>();
    addRestResourceClasses(resources);
    return resources;
  }
  
  private void addRestResourceClasses(Set<Class<?>> resources) {
    resources.add(com.hospital_name.claims_hci.CLAIMS.class);
  }
}
