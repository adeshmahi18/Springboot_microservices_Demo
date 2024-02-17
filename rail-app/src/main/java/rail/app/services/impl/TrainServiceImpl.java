package rail.app.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rail.app.dto.SearchTrainsDto;
import rail.app.entity.FindDayName;
import rail.app.entity.TrainMaster;
import rail.app.entity.TrainRunningDays;
import rail.app.entity.TrainSeatAvailability;
import rail.app.repo.TrainMasterRepo;
import rail.app.repo.TrainRunningDaysRepo;
import rail.app.repo.TrainSeatAvailablityRepo;
import rail.app.response.SearchTrainResponse;
import rail.app.services.TrainService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Service
public class TrainServiceImpl implements TrainService {

    @Autowired
    private TrainMasterRepo trainMasterRepo;

    @Autowired
    private TrainRunningDaysRepo trainRunningDaysRepo;
    @Autowired
    private TrainSeatAvailablityRepo trainSeatAvailablityRepo;

    @Override
    public TrainMaster saveTrainMasterData(TrainMaster trainMaster) {

        return trainMasterRepo.save(trainMaster);
    }

    @Override
    public TrainRunningDays saveTrainRunningDays(TrainRunningDays trainRunningDays) {
        return trainRunningDaysRepo.save(trainRunningDays);
    }

    @Override
    public TrainSeatAvailability saveSeatAvailability(TrainSeatAvailability trainSeatAvailability) {
        return trainSeatAvailablityRepo.save(trainSeatAvailability);
    }

    @Override
    public List<SearchTrainResponse> searchTrains(SearchTrainsDto searchTrainsDto) {


        List<SearchTrainResponse> allTrainsResponse=new ArrayList<>();

        String tempFromCity=searchTrainsDto.getFromCity();
        String tempToCity=searchTrainsDto.getToCity();
        String tempJourneyDate=searchTrainsDto.getJourneyDate();
        String tempDayName=findDayName(tempJourneyDate);
        System.out.println("day===="+tempDayName);
        System.out.println("day===="+tempFromCity);
        System.out.println("day===="+tempToCity);
        List<TrainMaster> allTrains= trainMasterRepo.findAllByFromStationAndToStation(tempFromCity,tempToCity);
       for(TrainMaster train:allTrains){
           long tempTrainId=train.getId();
           System.out.println("tempTrainId===="+tempTrainId);
           TrainRunningDays trainRunningStatus=trainRunningDaysRepo.findAllByTrainIdAndDays(tempTrainId, tempDayName);
           System.out.println("trainRunningStatus===="+trainRunningStatus);
           if(trainRunningStatus!=null){
               SearchTrainResponse searchTrainResponse =new SearchTrainResponse();
                TrainSeatAvailability seatAvailablity=trainSeatAvailablityRepo.findAllByTrainId(tempTrainId);
               System.out.println("seatAvailablity===="+seatAvailablity);
               searchTrainResponse.setTrainMaster(train);
               searchTrainResponse.setTrainRunningDays(trainRunningStatus);
               searchTrainResponse.setTrainSeatAvailability(seatAvailablity);
               allTrainsResponse.add(searchTrainResponse);
           }
       }
        System.out.println(allTrainsResponse);
        return allTrainsResponse;
    }

    private String findDayName(String dateString) {
        Instant instant = Instant.parse(dateString);
        // Get the day of the week
        DayOfWeek dayOfWeek = instant.atZone(ZoneId.systemDefault()).getDayOfWeek();
        // Get the day name
        String dayName = dayOfWeek.toString();
        String formattedDayName = dayName.substring(0, 1) + dayName.substring(1).toLowerCase();
        System.out.println("Day Name: " + formattedDayName);
        return  formattedDayName;
    }
}
