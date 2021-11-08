package com.example.facebookapi.entity;

import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table("Comment")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Comment {

    @PrimaryKey
    private UUID commentID;
    @NotNull
    private UUID postID;
    @NotNull
    private UUID userID;
    private String userImage;
    private String userName;
    private String comment;
    private LocalDateTime time;

}
