package com.example.facebookapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userID")
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

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")  // cascade = CascadeType.ALL
    private List<Post> posts;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "user")
    private List<Comment> comments;
}



