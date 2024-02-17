package rail.app.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import rail.app.entity.TrainMaster;
import rail.app.entity.TrainRunningDays;
import rail.app.entity.TrainSeatAvailability;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchTrainResponse {
    private TrainMaster trainMaster;
    private TrainRunningDays trainRunningDays;
    private TrainSeatAvailability trainSeatAvailability;

}
