package com.example.facebookapi.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import javax.persistence.Entity;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table("Post")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Post {

    @PrimaryKey
    private UUID postID;

    private UUID userID;
    private String userName;
    private String imageURL;

    private String description;
    private String postImgURL;

    private int likes;
    private LocalDateTime dateTime;



}
