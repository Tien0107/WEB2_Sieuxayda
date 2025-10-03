package com.example.homework2.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "companies")
@Data
public class Company {
 @Id
 private String id;
 private String name;
 private String address;
 private String phone;
 private String email;
 private String website;
}
