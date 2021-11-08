package com.example.facebookapi.entity;

import lombok.*;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import javax.persistence.Entity;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table("User")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @PrimaryKey
    private UUID userID;
    private String userName;
    private String password;
    private String userImage;
    private boolean active;
    private LocalDateTime joiningDate;

}



