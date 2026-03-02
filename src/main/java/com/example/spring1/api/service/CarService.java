package com.example.spring1.api.service;
import com.example.spring1.api.dto.CarDTO;
import com.example.spring1.api.entity.Car;
import com.example.spring1.api.entity.Dealership;
import com.example.spring1.api.repository.CarRepository;
import com.example.spring1.api.repository.DealershipRepository;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    private final CarRepository carRepository;
    // ADD THIS: Inject the DealershipRepository
    private final DealershipRepository dealershipRepository;

    // Update your constructor to include both repositories
    public CarService(CarRepository carRepository, DealershipRepository dealershipRepository) {
        this.carRepository = carRepository;
        this.dealershipRepository = dealershipRepository;
    }

    public CarDTO createCar(Car car, Long dealerId) {
        Car savedCar = carRepository.save(car);

        if (dealerId != null) {
            Optional<Dealership> dealerOpt = dealershipRepository.findById(dealerId);
            if (dealerOpt.isPresent()) {
                Dealership dealership = dealerOpt.get();

                dealership.addCar(savedCar);

                dealershipRepository.save(dealership);
            }
        }

        return convertToDto(savedCar);
    }

    public List<CarDTO> getAllCars() {
        List<Car> carList = carRepository.findAll();
        List<CarDTO> dtoList = new ArrayList<>();
        for (Car car : carList) {
            dtoList.add(convertToDto(car));
        }
        return dtoList;
    }

    public Optional<CarDTO> getCarByVinid(Long vinid) {
        Optional<Car> carOptional = carRepository.findById(vinid);
        return carOptional.map(this::convertToDto);
    }

    public boolean updateCar(Long vinid, String make, String model, String color) {
        Optional<Car> optionalCar = carRepository.findById(vinid);
        if (optionalCar.isPresent()) {
            Car existingCar = optionalCar.get();
            existingCar.setMake(make);
            existingCar.setColor(color);
            existingCar.setModel(model);
            carRepository.save(existingCar);
            return true;
        }
        return false;
    }

    public Optional<CarDTO> deleteCar(Long vinid) {
        Optional<Car> carOption = carRepository.findById(vinid);
        if (carOption.isPresent()) {
            Car carToDelete = carOption.get();
            CarDTO carDeleteDto = convertToDto(carToDelete);
            carRepository.deleteById(carToDelete.getId());
            return Optional.of(carDeleteDto);
        }
        return Optional.empty();
    }

    private CarDTO convertToDto(Car carEntity) {
        CarDTO dto = new CarDTO();
        dto.setVinid(carEntity.getId());
        dto.setMake(carEntity.getMake());
        dto.setColor(carEntity.getColor());
        dto.setModel(carEntity.getModel());
        dto.setDisplayname(carEntity.getMake() + " " + carEntity.getColor());
        return dto;
    }
}