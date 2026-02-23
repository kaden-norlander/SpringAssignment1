package com.example.spring1.api.repository;
import com.example.spring1.api.models.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    // Custom queries can be defined here if needed later
}