package com.example.facebookapi.entity;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue
    private int userID;

    @Column(name = "userName")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "userImage")
    private String userImage;

    @Column(name = "active")
    private boolean active;

    @Column(name = "joiningDate")
    private LocalDateTime joiningDate;

}



