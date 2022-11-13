package com.example.securityServices.dataObject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentObject {

    private int id;
    private int post_id;
    String name;
    String email;
    String body;

}
