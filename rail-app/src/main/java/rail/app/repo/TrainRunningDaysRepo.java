package rail.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import rail.app.entity.TrainRunningDays;

public interface TrainRunningDaysRepo extends JpaRepository<TrainRunningDays, Long> {

    TrainRunningDays findAllByTrainIdAndDays(long tempTrainId, String tempDayName);
}
