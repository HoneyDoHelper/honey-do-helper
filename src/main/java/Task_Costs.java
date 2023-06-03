import jakarta.persistence.*;

@Entity
@Table(name = "task_costs")

public class Task_Costs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "hd_pay", nullable = false)
    private float hdPay;

    @Column(name = "site_pay", nullable = false)
    private float sitePay;

    @Column(name = "total_user_cost", nullable = false)
    private float totalUserCost;

    @Column(name = "taxes", nullable = false)
    private float taxes;

    @ManyToOne
    @JoinColumn(name = "task_id", nullable = false)
    private Tasks task;

}
