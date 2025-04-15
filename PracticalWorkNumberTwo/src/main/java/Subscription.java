import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Subscriptions")
@Getter
@Setter
public class Subscription {
    @EmbeddedId
    private SubscriptionKey id;

    @ManyToOne(cascade = CascadeType.ALL)
    @MapsId("studentId")
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne(cascade = CascadeType.ALL)
    @MapsId("courseId")
    @JoinColumn(name = "course_id")
    private Course course;

    @Column(name = "subscription_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date subscriptionDate;
}
