import javax.persistence.*;

@Entity
@Table(name = "object_link")
public class ObjectLink {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "object_link_id")
    long id;

    @Column
    String objectName;
}
