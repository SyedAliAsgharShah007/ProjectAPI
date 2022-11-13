package com.example.securityServices.dataObject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostObject {
    private int id;
    private int user_id;
    String title;
    String body;
}
