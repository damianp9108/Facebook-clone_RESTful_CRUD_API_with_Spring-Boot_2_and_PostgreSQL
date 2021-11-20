package com.example.facebookapi.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="Posts")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "postID")
    private int postID;

    @ManyToOne(fetch = FetchType.EAGER) // cascade = CascadeType.ALL,
    @JoinColumn(name = "userId", referencedColumnName = "userID")
    private User user;

    @Column(name = "description")
    private String description;

    @Column(name = "postImgURL")
    private String postImgURL;

    @Column(name = "likes")
    private int likes;

    @Column(name = "dateTime")
    private LocalDateTime dateTime;

}
