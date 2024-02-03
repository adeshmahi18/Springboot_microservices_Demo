package car.rent.controller;

import car.rent.entity.BookCar;
import car.rent.entity.Car;
import car.rent.response.Response;
import car.rent.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CarController {

    @Autowired
    private CarService carService;


     @PostMapping("/saveCarDetails")
    public ResponseEntity<?> saveCarDetails(@RequestBody Car car){
        Car savedCar=carService.saveCarDetails(car);
        return new ResponseEntity<>(savedCar, HttpStatus.CREATED);
    }

    @GetMapping("/getAllCarDetails")
    public ResponseEntity<?> getAllCars(){
        List<Car> allCars=carService.getAllCars();
        return new ResponseEntity<>(allCars, HttpStatus.CREATED);
    }

    @PostMapping("/bookCar")
    public ResponseEntity<?> bookCar(@RequestBody BookCar bookCar){
        System.out.println(bookCar.getAccountNumber());
         String message = carService.bookCar(bookCar);
        Response response= new Response();
        response.setMessage(message);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
