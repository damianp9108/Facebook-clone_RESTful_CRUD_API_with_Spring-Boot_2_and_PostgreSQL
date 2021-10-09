package com.example.facebookapi.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import javax.persistence.Entity;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Table("Comment")
public class Comment {

    @PrimaryKey
    private UUID commentID;

    private UUID postID;
    private String userID;

    private String userImage;
    private String userName;

    private String comment;
    private Timestamp timestamp;

}
