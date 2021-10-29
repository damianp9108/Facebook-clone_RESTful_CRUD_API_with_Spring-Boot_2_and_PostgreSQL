package com.example.facebookapi.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import javax.persistence.Entity;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table("User")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {

    @PrimaryKey
    private UUID userID;

    @Pattern(regexp = "^((?!.*[\\s]).{4,14})", message = "nazwa uzytkownia musi zawierac 4-14 znakow, spacja niedozwolona")
    private String userName;

    @Pattern(regexp = "^((?!.*[\\s]).{5,16})", message = "Haslo musi zawierac 5-16 znakow, spacja niedozwolona")
    private String password;

    private String userImage;
    private boolean active;
    private LocalDateTime joiningDate;

}



