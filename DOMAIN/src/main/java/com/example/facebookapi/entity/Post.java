package com.example.facebookapi.entity;

import lombok.*;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table("Post")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {

    @PrimaryKey
    private UUID postID;

    @NotNull(message = "prosze podac ID uzytkownika")
    private UUID userID;

    private String userName;

    private String imageURL;
    public String description;
    public String postImgURL;
    private int likes;
    private LocalDateTime dateTime;



}
