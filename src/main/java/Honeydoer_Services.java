import jakarta.persistence.*;

@Entity
@Table(name = "honeydoer_services")

public class Honeydoer_Services {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;

        @Column(name = "rate", nullable = false)
        private float rate;

        @Column(name = "about_service", nullable = false)
        private String aboutService;

        @ManyToOne
        @JoinColumn(name = "service_id", nullable = false)
        private Services services;

        @ManyToOne
        @JoinColumn(name = "hd_id", nullable = false)
        private Honeydoers honeydoers;

}
