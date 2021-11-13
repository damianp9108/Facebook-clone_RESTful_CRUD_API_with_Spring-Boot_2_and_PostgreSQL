package com.example.facebookapi.entity;

import lombok.*;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;
import javax.persistence.Entity;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table("Status")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Status {

    @PrimaryKey
    private UUID statusID;
    private UUID userID;
    private String userName;
    private String statusImageURL;
    private LocalDateTime uploadTime;

}
