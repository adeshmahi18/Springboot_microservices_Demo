package rail.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import rail.app.entity.TrainSeatAvailability;

public interface TrainSeatAvailablityRepo extends JpaRepository<TrainSeatAvailability, Long> {
 TrainSeatAvailability findAllByTrainId(long tempTrainId);
}
