package rail.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rail.app.dto.SearchTrainsDto;
import rail.app.entity.TrainMaster;
import rail.app.entity.TrainRunningDays;
import rail.app.entity.TrainSeatAvailability;
import rail.app.response.SearchTrainResponse;
import rail.app.services.TrainService;

import java.util.List;

@RestController
@RequestMapping("/api/train")
public class TrainController {
    @Autowired
    private TrainService trainService;

    @PostMapping("/saveTrainMasterData")
    public ResponseEntity<?> saveTrainMasterData(@RequestBody TrainMaster trainMaster){
        TrainMaster savedTrainDetails=trainService.saveTrainMasterData(trainMaster);
        return new ResponseEntity<>(savedTrainDetails, HttpStatus.CREATED);
    }

    @PostMapping("/saveTrainRunningDaysData")
    public ResponseEntity<?> saveTrainRunningDays(@RequestBody TrainRunningDays trainRunningDays){
        TrainRunningDays savedTsavedTrainRunningDays=trainService.saveTrainRunningDays(trainRunningDays);
        return new ResponseEntity<>(savedTsavedTrainRunningDays, HttpStatus.CREATED);
    }

    @PostMapping("/saveTrainSeatAvailability")
    public ResponseEntity<?> saveTrainSeatAvailability(@RequestBody TrainSeatAvailability trainSeatAvailability){
        TrainSeatAvailability savedTrainSeatAvail=trainService.saveSeatAvailability(trainSeatAvailability);
        return new ResponseEntity<>(savedTrainSeatAvail, HttpStatus.CREATED);
    }

    @PostMapping("/searchTrainAvailability")
    public ResponseEntity<?> searchTrains(@RequestBody SearchTrainsDto searchTrainsDto){
        System.out.println(searchTrainsDto.getFromCity());
        System.out.println(searchTrainsDto.getJourneyDate());
        List<SearchTrainResponse> allTrains= trainService.searchTrains(searchTrainsDto);
        return new ResponseEntity<>(allTrains, HttpStatus.OK);
    }




}
