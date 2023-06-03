import jakarta.persistence.*;

@Entity
@Table(name = "categories")

public class Categories {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "img_file_path", nullable = false)
    private String imgFilePath;

}

