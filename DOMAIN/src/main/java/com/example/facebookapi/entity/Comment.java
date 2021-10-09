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
@Table("Comment")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Comment {

    @PrimaryKey
    private UUID commentID;

    private UUID postID;
    private UUID userID;

    private String userImage;
    private String userName;

    private String comment;
    private LocalDateTime time;

}
