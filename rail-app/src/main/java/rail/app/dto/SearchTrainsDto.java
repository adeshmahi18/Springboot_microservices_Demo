package rail.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchTrainsDto {
    private String fromCity;
    private String toCity;
    private String journeyDate;
}
