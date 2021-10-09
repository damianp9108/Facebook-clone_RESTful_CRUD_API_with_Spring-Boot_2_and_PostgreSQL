package com.example.facebookapi.repository;

import com.example.facebookapi.entity.Status;
import org.springframework.data.cassandra.repository.CassandraRepository;
import java.util.UUID;

public interface StatusRepository extends CassandraRepository<Status, UUID> {


}
