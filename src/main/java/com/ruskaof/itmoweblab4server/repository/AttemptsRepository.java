package com.ruskaof.itmoweblab4server.repository;

import com.ruskaof.itmoweblab4server.model.Attempt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttemptsRepository extends JpaRepository<Attempt, Integer> {
}
