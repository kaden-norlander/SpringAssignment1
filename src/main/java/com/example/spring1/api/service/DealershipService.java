
package com.example.spring1.api.service;
import com.example.spring1.api.dto.CarDTO;
import com.example.spring1.api.dto.DealershipDTO;
import com.example.spring1.api.entity.Car;
import com.example.spring1.api.entity.Dealership;
import com.example.spring1.api.entity.Manufacturer;
import com.example.spring1.api.repository.DealershipRepository;
import com.example.spring1.api.repository.ManufacturerRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DealershipService {

    private final DealershipRepository repository;
    private final ManufacturerRepository manufacturerRepository;

    public DealershipService(DealershipRepository repository, ManufacturerRepository manufacturerRepository) {
        this.repository = repository;
        this.manufacturerRepository = manufacturerRepository;
    }

    public List<DealershipDTO> getAllDealerships() {
        List<Dealership> dealershipList = repository.findAll();
        List<DealershipDTO> dtoList = new ArrayList<>();
        for (Dealership dealership : dealershipList) {
            dtoList.add(convertToDto(dealership));
        }
        return dtoList;
    }

    public DealershipDTO createDealership(Dealership dealership, Long manufacturerId) {
        if (manufacturerId != null) {
            Optional<Manufacturer> optionalManufacturer = manufacturerRepository.findById(manufacturerId);
            optionalManufacturer.ifPresent(dealership::setManufacturer);
        }

        Dealership newDealershipEntity = repository.save(dealership);
        return convertToDto(newDealershipEntity);
    }

    public Optional<DealershipDTO> deleteDealership(Long dealershipId) {
        Optional<Dealership> dealershipOption = repository.findById(dealershipId);
        if (dealershipOption.isPresent()) {
            Dealership dealershipToDelete = dealershipOption.get();
            DealershipDTO dealershipDeleteDto = convertToDto(dealershipToDelete);
            repository.deleteById(dealershipDeleteDto.getDealershipId());
            return Optional.of(dealershipDeleteDto);
        }
        return Optional.empty();
    }

    public Optional<DealershipDTO> getDealershipById(Long dealershipId) {
        Optional<Dealership> dealershipOption = repository.findById(dealershipId);
        return dealershipOption.map(this::convertToDto);
    }

    public DealershipDTO convertToDto(Dealership dealership) {
        DealershipDTO dto = new DealershipDTO();
        List<CarDTO> carDtos = new ArrayList<>();
        dto.setDealershipId(dealership.getDealershipId());
        dto.setName(dealership.getName());
        dto.setLocation(dealership.getLocation());

        if (dealership.getManufacturer() != null) {
            dto.setManufacturerName(dealership.getManufacturer().getName());
        }

        if (dealership.getCars() != null) {
            for (Car car : dealership.getCars()) {
                CarDTO carDto = new com.example.spring1.api.dto.CarDTO();
                carDto.setVinid(car.getId());
                carDto.setMake(car.getMake());
                carDto.setModel(car.getModel());
                carDto.setColor(car.getColor());
                carDtos.add(carDto);
            }
        }
        dto.setCars(carDtos);

        return dto;
    }
}
