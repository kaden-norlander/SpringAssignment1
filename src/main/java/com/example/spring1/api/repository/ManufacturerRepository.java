package com.example.spring1.api.repository;
import com.example.spring1.api.entity.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManufacturerRepository extends JpaRepository<Manufacturer, Long> {
    // Custom queries can be defined here if needed later
}