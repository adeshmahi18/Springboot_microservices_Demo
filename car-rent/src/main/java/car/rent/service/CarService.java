package car.rent.service;

import car.rent.entity.BookCar;
import car.rent.entity.Car;

import java.util.List;

public interface CarService {

    public Car saveCarDetails(Car car);

    public List<Car> getAllCars();

    public String bookCar(BookCar bookCar);
}
