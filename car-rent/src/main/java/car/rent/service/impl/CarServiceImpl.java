package car.rent.service.impl;

import car.rent.dto.DebitTransactionDto;
import car.rent.entity.BookCar;
import car.rent.entity.Car;
import car.rent.repo.BookCarRepo;
import car.rent.repo.CarRepo;
import car.rent.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.File;
import java.util.List;

@Service
public class CarServiceImpl implements CarService {
    @Autowired
    private CarRepo carRepo;

    @Autowired
    private BookCarRepo bookCarRepo;
    @Autowired
    private WebClient webClient;
    @Override
    public Car saveCarDetails(Car car) {
        Car savedCar=carRepo.save(car);
        return savedCar;
    }

    @Override
    public List<Car> getAllCars() {
        List<Car> allCars=carRepo.findAll();

        return allCars;
    }


    @Override
    public String bookCar(BookCar bookCar) {

        DebitTransactionDto debitTransactionDto = new DebitTransactionDto();
        debitTransactionDto.setAccountNumber(bookCar.getAccountNumber());
        debitTransactionDto.setAmount(bookCar.getAmount());
        String resMessage = webClient.post()
                .uri("http://localhost:8081/debitTransaction")
                .body(Mono.just(debitTransactionDto), DebitTransactionDto.class)
                .retrieve()
                        .bodyToMono(String.class)
                                .block();

        System.out.println("message====="+resMessage);
         if(!resMessage.equals("In sufficient funds")){
            bookCarRepo.save(bookCar);
        }
        return resMessage;
    }
}
