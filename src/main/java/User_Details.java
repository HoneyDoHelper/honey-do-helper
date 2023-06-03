import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "user_details")

public class User_Details {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "address", nullable = false, length = 50)
    private String address;

    @Column(name = "address2", length = 50)
    private String address2;

    @Column(name = "city", nullable = false, length = 30)
    private String city;

    @Column(name = "state", nullable = false, length = 2)
    private String state;

    @Column(name = "zip")
    private Integer zip;

    @Column(name = "join_date", nullable = false)
    private LocalDate joinDate;

    @Column(name = "phone", nullable = false)
    private int phone;

    @Column(name = "img_file_path", nullable = false, length = 125)
    private String imgFilePath;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

}
