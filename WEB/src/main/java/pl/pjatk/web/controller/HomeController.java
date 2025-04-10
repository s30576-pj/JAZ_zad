package pl.pjatk.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pjatk.web.model.CarModel;

@RestController
@RequestMapping("/homework")
public class HomeController {

    @GetMapping("/path/{info}")
    public ResponseEntity<CarModel> getWithPathVariable(@PathVariable String info) {
        CarModel car = new CarModel(info, "GenericModel", 2020);
        return ResponseEntity.ok(car);
    }

    @GetMapping("/param")
    public ResponseEntity<CarModel> getWithRequestParam(@RequestParam String brand, @RequestParam String model, @RequestParam int year) {
        CarModel car = new CarModel(brand, model, year);
        return ResponseEntity.ok(car);
    }

    @PostMapping
    public ResponseEntity<CarModel> createCar(@RequestBody CarModel carModel) {
        return ResponseEntity.ok(carModel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarModel> updateCar(@PathVariable String id, @RequestBody CarModel carModel) {
        System.out.println("Updating car with ID: " + id);
        return ResponseEntity.ok(carModel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable String id) {
        System.out.println("Deleting car with ID: " + id);
        return ResponseEntity.ok().build();
    }
}

//http://localhost:8080/homework/path/BMW get
//http://localhost:8080/homework/param?brand=BMW&model=e38&year=1998 get
//http://localhost:8080/homework post
//http://localhost:8080/homework/123 put
//http://localhost:8080/homework/123 delete