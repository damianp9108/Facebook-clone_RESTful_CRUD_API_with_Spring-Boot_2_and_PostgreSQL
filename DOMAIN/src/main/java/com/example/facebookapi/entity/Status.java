package com.example.facebookapi.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="Statuses")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Status {

    @Id
    @GeneratedValue
    private int statusID;

    @Column(name = "userID")
    private int userID;

    @Column(name = "userName")
    private String userName;

    @Column(name = "statusImageURL")
    private String statusImageURL;

    @Column(name = "uploadTime")
    private LocalDateTime uploadTime;

}
