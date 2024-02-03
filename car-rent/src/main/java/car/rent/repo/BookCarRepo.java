package car.rent.repo;

import car.rent.entity.BookCar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookCarRepo extends JpaRepository<BookCar, Long> {
}
