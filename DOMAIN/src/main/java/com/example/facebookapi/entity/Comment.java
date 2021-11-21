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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int commentID;

    @ManyToOne(fetch = FetchType.EAGER)             // cascade = CascadeType.ALL
    @JoinColumn(name = "userID", referencedColumnName = "userID")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "postID", referencedColumnName = "postID")
    private Post post;

    @Column(name = "comment")
    private String comment;

    @Column(name = "time")
    private LocalDateTime time;

}
