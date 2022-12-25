package journal.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Entity
@Table(name = "marks")
public class Mark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column (name = "subject")
    @NotEmpty
    private String subject;

    @Column (name = "mark")
    @NotEmpty
    private int mark;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "student", referencedColumnName = "surname")
    private Student student;

    @Column (name = "mark_date_and_time")
    @Temporal(TemporalType.TIMESTAMP)
    @NotEmpty
    private Date markDateAndTime;

    public Date getMarkDateAndTime() {
        return markDateAndTime;
    }

    public void setMarkDateAndTime(Date markDateAndTime) {
        this.markDateAndTime = markDateAndTime;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

}
