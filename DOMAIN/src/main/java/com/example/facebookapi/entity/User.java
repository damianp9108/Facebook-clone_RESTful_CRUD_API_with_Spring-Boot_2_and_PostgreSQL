package com.example.facebookapi.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
@Table("User")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {

    @PrimaryKey
    private String userID;

    private String userName;
    private String userImage;
    private boolean active;
    private LocalDateTime joiningDate;

}



