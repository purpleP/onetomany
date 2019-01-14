import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.List;

import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "a")
@JsonIdentityInfo(
  generator = ObjectIdGenerators.PropertyGenerator.class,
  property = "id"
)
public class A {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "a_id")
    public long id;

    @Column(name = "parent_id")
    public Long parentId;

    @Column(name = "order_by")
    public Long order;

    @ManyToOne
    @JoinColumn(name = "parent_id", insertable = false, updatable = false)
    public A parent;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "parent_id")
    @OrderColumn(name = "order_by")
    public List<A> children;


    @ManyToMany
    @JoinTable(
        name = "a_b",
        joinColumns = @JoinColumn(name = "a_id", referencedColumnName = "a_id"),
        inverseJoinColumns = @JoinColumn(name = "b_id", referencedColumnName = "b_id")
    )
    public Set<B> bs;
}
