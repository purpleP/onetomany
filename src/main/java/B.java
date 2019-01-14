import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "b")
public class B {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "b_id")
    public long id;

    @ManyToMany(mappedBy = "bs")
    public Set<A> as;
}

