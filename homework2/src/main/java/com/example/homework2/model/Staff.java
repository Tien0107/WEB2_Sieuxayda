package com.example.homework2.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "staffs")
@Data
public class Staff {
 @Id
 private String id;
 private String name;
 private String position;
 private String email;
 private String phone;
 private String companyId;
}
