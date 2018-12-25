import javax.persistence.*;
import java.util.List;

import java.util.stream.Collectors;

@Entity
@Table(name = "a")
public class A {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "a_id")
    long id;

    @Column(name = "parent_id")
    Long parentId;

    @Column(name = "order_by")
    Long order;

    @ManyToOne
    @JoinColumn(name = "parent_id", insertable = false, updatable = false)
    A parent;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "parent_id")
    @OrderColumn(name = "order_by")
    List<A> children;


    public String toString() {
        return "{\"id\": " + String.valueOf(id)
            + ", \"children\": ["
            + this.children.stream().map(A::toString).collect(Collectors.joining(", "))
            + "]";
    }
}
