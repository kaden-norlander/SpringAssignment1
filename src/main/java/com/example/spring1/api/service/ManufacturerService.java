
package com.example.spring1.api.service;
import com.example.spring1.api.dto.ManufacturerDTO;
import com.example.spring1.api.entity.Manufacturer;
import com.example.spring1.api.repository.ManufacturerRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ManufacturerService {

    private final ManufacturerRepository repository;

    public ManufacturerService(ManufacturerRepository repository) {
        this.repository = repository;
    }

    public List<ManufacturerDTO> getAllManufacturers() {
        List<Manufacturer> manufacturerList = repository.findAll();
        List<ManufacturerDTO> dtoList = new ArrayList<>();
        for (Manufacturer manufacturer : manufacturerList) {
            dtoList.add(convertToDto(manufacturer));
        }
        return dtoList;
    }

    public ManufacturerDTO createManufacturer(Manufacturer manufacturer) {
        Manufacturer newManufacturerEntity = repository.save(manufacturer);
        if (newManufacturerEntity.getManufacturerId() != null) {
            return convertToDto(newManufacturerEntity);
        }
        return null;
    }

    public Optional<ManufacturerDTO> deleteManufacturer(Long manufacturerID) {
        Optional<Manufacturer> manufacturerOption = repository.findById(manufacturerID);
        if (manufacturerOption.isPresent()) {
            Manufacturer manufacturerToDelete = manufacturerOption.get();
            ManufacturerDTO manufacturerDeleteDto = convertToDto(manufacturerToDelete);
            repository.deleteById(manufacturerDeleteDto.getManufacturerId());
            return Optional.of(manufacturerDeleteDto);
        }
        return Optional.empty();
    }

    public ManufacturerDTO convertToDto(Manufacturer manufacturer) {
        ManufacturerDTO dto = new ManufacturerDTO();
        dto.setManufacturerId(manufacturer.getManufacturerId());
        dto.setName(manufacturer.getName());
        dto.setCountryOfOrigin(manufacturer.getCountryOfOrigin());

        return dto;
    }
}
