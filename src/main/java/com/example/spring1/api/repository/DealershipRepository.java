package com.example.spring1.api.repository;
import com.example.spring1.api.entity.Dealership;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DealershipRepository extends JpaRepository<Dealership, Long> {
    // Custom queries can be defined here if needed later
}
