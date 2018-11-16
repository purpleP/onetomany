import javax.persistence.*;

@Entity
@Table(name = "field_view")
public class Field {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "field_id")
    private long id;

    @Column(name = "object_id")
    long objectId;

    @Column(name = "string_value")
    String strValue;

    @Column(name = "num_value")
    Long numValue;

    @Column(name = "field_name")
    String name;

    @Column(name = "field_type")
    FieldType type;
}
