import jakarta.persistence.*;

@Entity
@Table(name = "honeydoer_reviews")

public class Honeydoer_Reviews {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "stars", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private Stars stars;

    @Column(name = "comment")
    private String comment;

    @ManyToOne
    @JoinColumn(name = "task_id", nullable = false)
    private Tasks task;

    @ManyToOne
    @JoinColumn(name = "hd_id", nullable = false)
    private Honeydoers honeydoer;

}
