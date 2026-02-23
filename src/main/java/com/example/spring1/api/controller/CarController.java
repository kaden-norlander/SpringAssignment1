package com.example.spring1.api.controller;
import com.example.spring1.api.models.Car;
import com.example.spring1.api.repository.CarRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;


@RestController
@RequestMapping("/api/cars")
public class CarController {

    private final CarRepository repository;

    public CarController(CarRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public Iterable<Car> getAllCars() {
        return repository.findAll();
    }

    @PostMapping
    public Car createCar(@RequestBody Car car) {
        return repository.save(car);
    }

    @GetMapping("/{vinid}")
    public ResponseEntity<Car> getCarByVinid(@PathVariable Long vinid) {
        Optional<Car> car = repository.findById(vinid);
        return car.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{vinid}")
    public ResponseEntity<Car> updateCar(@PathVariable Long vinid, @RequestBody Car carDetails) {
        return repository.findById(vinid).map(car -> {
            car.setMake(carDetails.getMake());
            car.setColor(carDetails.getColor());
            Car updatedCar = repository.save(car);
            return ResponseEntity.ok(updatedCar);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{vinid}")
    public ResponseEntity<Void> deleteCar(@PathVariable Long vinid) {
        if(repository.existsById(vinid)) {
            repository.deleteById(vinid);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
