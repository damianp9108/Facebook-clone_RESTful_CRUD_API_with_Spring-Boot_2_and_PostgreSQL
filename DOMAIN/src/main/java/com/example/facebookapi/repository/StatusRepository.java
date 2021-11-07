package com.example.facebookapi.repository;

import com.example.facebookapi.entity.Status;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface StatusRepository extends CassandraRepository<Status, UUID> {


}
