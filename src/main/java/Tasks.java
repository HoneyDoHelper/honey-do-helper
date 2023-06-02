import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Tasks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "task_details", nullable = false)
    private String taskDetails;

    @Column(name = "date_assigned", nullable = false)
    private LocalDate dateAssigned;

    @Column(name = "date_completed")
    private LocalDate dateCompleted;

    @Column(name = "status")
    private String status;

    @Column(name = "is_accepted", nullable = false)
    private boolean accepted;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

    @ManyToOne
    @JoinColumn(name = "hd_service_id", nullable = false)
    private Honeydoer_Services honeydoerService;

}
