import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.hibernate.Session;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

public class AppTest {
    EntityManager em;

    @BeforeEach
    public void setUp() {
        String q1 = String.join(
            "\n",
            "INSERT INTO a (a_id, parent_id, order_by) VALUES",
            "(1, 2, 0),",
            "(2, 1, 1)"
        );
        String q2 = String.join(
            "\n",
            "INSERT INTO b (b_id) VALUES",
            "(1), (2)"
        );
        String q3 = String.join(
            "\n",
            "INSERT INTO a_b (a_id, b_id) VALUES",
            "(1, 1), (1, 2), (2, 1), (2, 2)"
        );
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("test");
        em = factory.createEntityManager();
        em.unwrap(Session.class).doWork(conn -> {
            conn.createStatement().execute(q1);
            conn.createStatement().execute(q2);
            conn.createStatement().execute(q3);
        });
    }

    @Test
    public void testQuery() {
        var mapper = new ObjectMapper();
        var as = em.createQuery("SELECT a FROM A a").getResultList();
        try {
            System.out.println(mapper.writeValueAsString(as));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        // CriteriaBuilder builder = em.getCriteriaBuilder();
        // CriteriaQuery<A> q_ = builder.createQuery(A.class);
        // Root<A> root = q_.from(A.class);
        // CriteriaQuery<A> q = q_.select(root);
        // q.where(builder.equal(root.get("parentId"), 1), builder.equal(root.get("order"), 2));
        // CriteriaQuery<Long> cq = q.select(root.get("id"));
        // List<A> results = em.createQuery(q).setMaxResults(1).getResultList();
        // assertArrayEquals(new Long[]{3L}, results.toArray());
        // long count = em.createQuery(cq.select(builder.count(root))).getSingleResult();
        // assertEquals(count, 1L);
    }
}
