package com.example.securityServices.dataObject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserObject {
    private int id;
    String name;
    String email;
    String gender;
    String status;
}
