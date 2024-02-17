package rail.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import rail.app.entity.TrainMaster;

import java.util.List;

public interface TrainMasterRepo extends JpaRepository<TrainMaster, Long>{
    List<TrainMaster> findAllByFromStationAndToStation(String tempFromCity, String tempToCity);
}
