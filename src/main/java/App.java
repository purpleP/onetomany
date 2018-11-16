import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

/*
 * This Java source file was generated by the Gradle 'init' task.
 * import javax.persistence.GenerationType
 */
public class App {

    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(
                "test");
        EntityManager em = factory.createEntityManager();
        for (char i = 0; i < 10; i++) {
            List<A> as = em.createQuery("select t from A t").getResultList();
            var fst = as.get(0);
            fst.fields.forEach(
                    (k, v) -> System.out.format("%s, %s, %s\n", k, v.strValue,
                            v.numValue
                    ));
            fst.fields.get("key").numValue = (long) i;
            var f = new Field();
            f.strValue = "newValue";
            // fst.fields.put("newKey", f);
            em.persist(fst);
        }
    }
}
