import javax.persistence.*;

@Entity
@Table(name = "field_definition")
public class FieldDefinition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "field_definition_id")
    long id;

    @OneToOne
    ObjectLink link;

    @Column
    String name;

    FieldType type;

}
