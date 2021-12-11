package com.example.facebookapi.domain.entity;

import lombok.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Users")
@Data
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userID")
    private int userID;

    private String userName;
    private String password;
    private String userImage;
    private boolean active;
    private LocalDateTime joiningDate;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)  // cascade = CascadeType.ALL
    private List<Post> posts;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Comment> comments;

    public User(String userName, String password, String userImage) {
        this.userName = userName;
        this.password = password;
        this.userImage = userImage;
    }
}



