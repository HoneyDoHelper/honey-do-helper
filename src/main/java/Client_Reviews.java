import jakarta.persistence.*;

@Entity
@Table(name = "client_reviews")

public class Client_Reviews {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "stars", nullable = false)
    @Enumerated(EnumType.STRING)
    private Stars stars;

    @Column(name = "comment")
    private String comment;

    @ManyToOne
    @JoinColumn(name = "task_id", nullable = false)
    private Tasks task;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

}
