package rail.app.services;

import rail.app.dto.SearchTrainsDto;
import rail.app.entity.TrainMaster;
import rail.app.entity.TrainRunningDays;
import rail.app.entity.TrainSeatAvailability;
import rail.app.response.SearchTrainResponse;

import java.util.List;

public interface TrainService {

    TrainMaster saveTrainMasterData(TrainMaster trainMaster);
    TrainRunningDays saveTrainRunningDays(TrainRunningDays trainRunningDays);
    TrainSeatAvailability saveSeatAvailability(TrainSeatAvailability trainSeatAvailability);

    List<SearchTrainResponse> searchTrains(SearchTrainsDto searchTrainsDto);
}
