import javax.persistence.*;

@Entity
@Table(name = "b")
public class B {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    // public E1() {
    //     fields = new ArrayList<>();
    // }
}
