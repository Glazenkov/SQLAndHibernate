import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml")
                .build();

        try {
            Metadata metadata = new MetadataSources(registry)
                    .getMetadataBuilder()
                    .build();
            SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();

            convertPurchaseListToLinked(sessionFactory);

            sessionFactory.close();
        } catch (Exception e) {
            e.printStackTrace();
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    private static void convertPurchaseListToLinked(SessionFactory sessionFactory) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Purchase> query = builder.createQuery(Purchase.class);
            Root<Purchase> root = query.from(Purchase.class);
            query.select(root);
            List<Purchase> purchases = session.createQuery(query).getResultList();

            for (Purchase purchase : purchases) {
                CriteriaQuery<Student> studentQuery = builder.createQuery(Student.class);
                Root<Student> studentRoot = studentQuery.from(Student.class);
                studentQuery.select(studentRoot)
                        .where(builder.equal(studentRoot.get("name"), purchase.getStudentName()));
                Student student = session.createQuery(studentQuery)
                        .setMaxResults(1)
                        .uniqueResult();

                CriteriaQuery<Course> courseQuery = builder.createQuery(Course.class);
                Root<Course> courseRoot = courseQuery.from(Course.class);
                courseQuery.select(courseRoot)
                        .where(builder.equal(courseRoot.get("name"), purchase.getCourseName()));
                Course course = session.createQuery(courseQuery)
                        .setMaxResults(1)
                        .uniqueResult();

                if (student != null && course != null) {
                    LinkedPurchaseListKey key = new LinkedPurchaseListKey(student.getId(), course.getId());
                    LinkedPurchaseList linkedPurchase = new LinkedPurchaseList();
                    linkedPurchase.setId(key);
                    linkedPurchase.setStudent(student);
                    linkedPurchase.setCourse(course);

                    session.save(linkedPurchase);
                }
            }

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
