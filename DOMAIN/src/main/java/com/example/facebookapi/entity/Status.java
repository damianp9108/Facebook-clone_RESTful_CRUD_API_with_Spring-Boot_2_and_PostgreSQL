package com.example.facebookapi.entity;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import javax.persistence.Entity;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table("Status")
public class Status {

    @PrimaryKey
    private UUID statusID;
    private String userID;
    private String statusImageURL;
    private Timestamp uploadTime;

    public Status() {
        super();
    }

    public Status(UUID statusID, String userID, String statusImageURL, Timestamp uploadTime) {
        this.statusID = statusID;
        this.userID = userID;
        this.statusImageURL = statusImageURL;
        this.uploadTime = uploadTime;
    }

    public UUID getStatusID() {
        return statusID;
    }

    public void setStatusID(UUID statusID) {
        this.statusID = statusID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getStatusImageURL() {
        return statusImageURL;
    }

    public void setStatusImageURL(String statusImageURL) {
        this.statusImageURL = statusImageURL;
    }

    public Timestamp getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Timestamp uploadTime) {
        this.uploadTime = uploadTime;
    }
}
