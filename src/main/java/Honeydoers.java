import jakarta.persistence.*;

@Entity
public class Honeydoers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "rating", nullable = false)
    private float rating;

    @Column(name = "about_self")
    private String aboutSelf;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

}

