import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;

import org.hibernate.Session;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class AppTest {
    EntityManager em;

    @BeforeEach
    public void setUp() {
        String query = String.join(
            "\n",
            "INSERT INTO a (a_id, parent_id, order_by) VALUES",
            "(1, null, 0),",
            "(2, 1, 1),",
            "(3, 1, 2)"
        );
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("test");
        em = factory.createEntityManager();
        em.unwrap(Session.class).doWork(conn -> {
            conn.createStatement().execute(query);
        });
    }

    @Test
    public void testQuery() {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        var q_ = builder.createQuery(Long.class);
        var root = q_.from(A.class);
        var q = q_.select(root.get("id"));
        q.where(builder.equal(root.get("parentId"), 1), builder.equal(root.get("order"), 2));
        List<Long> results = em.createQuery(q).setMaxResults(1).getResultList();
        assertArrayEquals(new Long[]{3L}, results.toArray());
        long count = em.createQuery(q.select(builder.count(root))).getSingleResult();
        assertEquals(count, 1L);
    }
}
