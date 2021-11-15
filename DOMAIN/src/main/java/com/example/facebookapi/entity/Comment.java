package com.example.facebookapi.entity;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="Comments")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Comment {

    @Id
    @GeneratedValue
    private int commentID;

    @Column(name="postID")
    private int postID;

    @Column(name="userID")
    private int userID;

    @Column(name="userImage")
    private String userImage;

    @Column(name = "userName")
    private String userName;

    @Column(name = "comment")
    private String comment;

    @Column(name = "time")
    private LocalDateTime time;

}
