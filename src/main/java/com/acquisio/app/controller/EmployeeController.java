package com.acquisio.app.controller;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.acquisio.app.domain.Employee;
import com.acquisio.app.repository.EmployeeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

  @Autowired
  private EmployeeRepository employeeRepository;

  @RequestMapping("/employees")
  @CrossOrigin(origins = "http://localhost:4200")
  public Collection<Employee> listEmployees() throws SQLException {
    List<Employee> list = employeeRepository.findAll();
      list.stream().forEach(emp -> {
        emp.setEmail(md5Hex(emp.getEmail()));
      });
      
      return list;
  }


  // Copy from Gravatar hash function
  private static String hex(byte[] array) {
    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < array.length; ++i) {
      sb.append(Integer.toHexString((array[i]
          & 0xFF) | 0x100).substring(1, 3));
    }
    return sb.toString();
  }

  private static String md5Hex(String message) {
    try {
      MessageDigest md =
          MessageDigest.getInstance("MD5");
      return hex(md.digest(message.getBytes("CP1252")));
    } catch (NoSuchAlgorithmException e) {
    } catch (UnsupportedEncodingException e) {
    }
    return null;
  }
}
