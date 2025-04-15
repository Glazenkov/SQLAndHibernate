import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "PurchaseList")
@Getter
@Setter
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "student_name")
    private String studentName;

    @Column(name = "course_name")
    private String courseName;

    private Integer price;

    @Column(name = "subscription_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date subscriptionDate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id")
    private Course course;
}
