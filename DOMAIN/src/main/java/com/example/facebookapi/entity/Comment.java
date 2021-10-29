package com.example.facebookapi.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import javax.persistence.Entity;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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

    @NotNull
    private UUID postID;

    @NotNull
    private UUID userID;

    private String userImage;
    private String userName;

    @NotBlank(message = "komentarz nie moze byc pusty")
    @Length(max = 300, message = "komentarz jest za dlugi")
    private String comment;

    private LocalDateTime time;

}
