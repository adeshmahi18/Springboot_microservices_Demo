package rail.app.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrainSeatAvailability {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long trainId;
    private long sleeperCount;
    private long twoTierCount;
    private long threeTierCount;
}
