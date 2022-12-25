package journal.repositories;

import journal.models.Mark;
import journal.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MarkRepository extends JpaRepository<Mark, Long > {
    public List<Mark> findMarksByStudent(Student student);
    public List<Mark> findMarksByStudentAndSubject(Student student, String subject);
}
