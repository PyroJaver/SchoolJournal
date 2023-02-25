package journal.serviceTests;

import journal.models.Mark;
import journal.models.Student;
import journal.repositories.MarkRepository;
import journal.services.MarkService;
import journal.services.StudentService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MarkServiceTest {

    @InjectMocks
    MarkService markService;

    @Mock
    MarkRepository markRepository;

    @Mock
    StudentService studentService;

    String testDiaryNumber = "testDiaryNumber";
    String testSubject = "testSubject";

    @Test
    public void shouldReturnAllMarks() {
        when(markService.findAllMarks()).thenReturn(List.of(new Mark(), new Mark(), new Mark()));
        assertThat(markService.findAllMarks()).hasSize(3);
        verify(markRepository, times(1)).findAll();
        verifyNoMoreInteractions(markRepository);
    }

    @Test
    public void shouldReturnAllMarksOfStudent() {
        when(markService.findAllMarksOfStudent(testDiaryNumber))
                .thenReturn(List.of(new Mark(), new Mark(), new Mark()));
        assertThat(markService.findAllMarksOfStudent(testDiaryNumber)).hasSize(3);
        verify(markRepository, times(1))
                .findMarksByStudent(studentService.findStudentByDiaryNumber(testDiaryNumber));
        verifyNoMoreInteractions(markRepository);
    }

    @Test
    public void shouldReturnAllSubjectMarksOfStudent() {
        when(markService.findSubjectMarksOfStudent(testDiaryNumber, testSubject))
                .thenReturn(List.of(new Mark(), new Mark(), new Mark()));
        assertThat(markService.findSubjectMarksOfStudent(testDiaryNumber, testSubject)).hasSize(3);
        verify(markRepository, times(1))
                .findMarksByStudent(studentService.findStudentByDiaryNumber(testDiaryNumber));
        verifyNoMoreInteractions(markRepository);
    }

    @Test
    public void shouldAddMark() {
        Mark mark = new Mark();
        markService.addMark(mark);
        verify(markRepository, Mockito.times(1)).save(mark);
    }

    @Test
    public void shouldSetMarkDetails() {
        Student testStudent = new Student();
        testStudent.setDiaryNumber(testDiaryNumber);
        Mark testMark = new Mark();
        testMark.setStudent(testStudent);
        when(studentService.findStudentByDiaryNumber(testDiaryNumber))
                .thenReturn(testStudent);
       markService.setMarkDetails(testMark);
       Assert.assertNotNull(testMark.getMarkDateAndTime());
    }
}
