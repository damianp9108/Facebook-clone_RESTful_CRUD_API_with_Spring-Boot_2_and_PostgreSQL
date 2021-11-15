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
    @GeneratedValue
    private int postID;

    @Column(name = "userID")
    private int userID;

    @Column(name = "userName")
    private String userName;

    @Column(name = "imageURL")
    private String imageURL;

    @Column(name = "description")
    private String description;

    @Column(name = "postImgURL")
    private String postImgURL;

    @Column(name = "likes")
    private int likes;

    @Column(name = "dateTime")
    private LocalDateTime dateTime;

}
