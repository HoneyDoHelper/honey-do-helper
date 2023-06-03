import jakarta.persistence.*;

@Entity
@Table(name = "honeydoer_images")
public class Honeydoer_Images {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "file_path", nullable = false)
    private String filePath;

    @ManyToOne
    @JoinColumn(name = "hd_id", nullable = false)
    private Honeydoers honeydoer;

}

