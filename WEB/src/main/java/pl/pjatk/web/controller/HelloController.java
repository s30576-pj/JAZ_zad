package pl.pjatk.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import pl.pjatk.web.model.CarModel;

@RestController
@RequestMapping("/test")
public class HelloController {

//    @GetMapping("/hello")
//    public ResponseEntity<String> sayHello() {
//        return ResponseEntity.ok("Hello World");
//    }

    @GetMapping("/hello/{someValue}")
    public ResponseEntity<String> saySome(@PathVariable String someValue) {
        return ResponseEntity.ok(someValue);
    }

    @GetMapping("/hello")
    public ResponseEntity<String> getHello(@RequestParam("reqParam") String reqParam) {
        return ResponseEntity.ok(reqParam);
    }
//    http://localhost:8080/test/hello?reqParam=kek

    @PostMapping("/model")
    public ResponseEntity<CarModel> getCarModel() {
        CarModel car = new CarModel("BMW", "e60", 2007);
        return ResponseEntity.ok(car);
    }

    @PostMapping("/model/from-body")
    public ResponseEntity<CarModel> createCarModelFromBody(@RequestBody CarModel carModel) {
        // Możesz tutaj też wykonać np. walidację lub modyfikacje danych
        return ResponseEntity.ok(carModel);
    }
}
