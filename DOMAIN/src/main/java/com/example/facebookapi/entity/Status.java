package com.example.facebookapi.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table("Status")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Status {

    @PrimaryKey
    private UUID statusID;

    @NotNull(message = "Nalezy podac ID uzytkownika")
    private UUID userID;

    @NotNull(message = "Nie dodano zdjecia")
    private String statusImageURL;

    private LocalDateTime uploadTime;


}
