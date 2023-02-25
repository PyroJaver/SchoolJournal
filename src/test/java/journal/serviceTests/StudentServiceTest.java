package journal.serviceTests;

import journal.models.Student;
import journal.repositories.StudentRepository;
import journal.services.StudentService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentServiceTest {

    @Autowired
    StudentService studentService;
    @MockBean
    StudentRepository studentRepository;


    @Test
    public void shouldAddStudent() {
        Student student = new Student();
        studentService.addStudent(student);
        verify(studentRepository, Mockito.times(1)).save(student);
    }

    @Test
    public void shouldFindStudentBySurname() {
        Student student = new Student();
        student.setSurname("testSurname");
        when(studentRepository.findStudentBySurname(student.getSurname())).thenReturn(student);
        Student expectedStudent = studentService.findStudentBySurname(student.getSurname());
        Assert.assertEquals(expectedStudent, student);
        verify(studentRepository, Mockito.times(1)).findStudentBySurname(student.getSurname());
    }

    @Test
    public void shouldFindStudentByDiaryNumber() {
        Student student = new Student();
        student.setDiaryNumber("testDiaryNumber");
        when(studentRepository.findStudentByDiaryNumber(student.getDiaryNumber())).thenReturn(student);
        Student expectedStudent = studentService.findStudentByDiaryNumber(student.getDiaryNumber());
        Assert.assertEquals(expectedStudent, student);
        verify(studentRepository, Mockito.times(1)).findStudentByDiaryNumber(student
                .getDiaryNumber());
    }

}
