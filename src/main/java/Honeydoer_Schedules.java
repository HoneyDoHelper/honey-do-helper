import jakarta.persistence.*;
import java.time.LocalDate;
@Entity
@Table(name = "honeydoer_schedules")

public class Honeydoer_Schedules {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "day_of_week", nullable = false)
    private LocalDate dayOfWeek;

    @ManyToOne
    @JoinColumn(name = "time_block_id", nullable = false)
    private Time_Blocks timeBlock;

    @ManyToOne
    @JoinColumn(name = "hd_id", nullable = false)
    private Honeydoers honeydoer;
}
