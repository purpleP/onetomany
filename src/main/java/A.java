import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "a")
public class A {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "a_id")
    long id;


    @Column(name = "parent_id")
    Long parent_id;

    @ManyToOne
    @JoinColumn(name = "parent_id", insertable = false, updatable = false)
    A parent;

    @OneToMany
    @JoinColumn(name = "parent_id")
    List<A> children;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "object_id")
    @Where(clause = "object_name = 'A'")
    @MapKey(name = "name")
    Map<String, Field> fields;
}
