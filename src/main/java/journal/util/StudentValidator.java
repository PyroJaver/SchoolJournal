package journal.util;

import journal.models.Student;
import journal.services.StudentService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class StudentValidator implements Validator {
    private final StudentService studentService;

    public StudentValidator(StudentService studentService) {
        this.studentService = studentService;
    }

    @Override
    public boolean supports(Class<?> clazz) {

        return Student.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Student student = (Student) target;
     if(studentService.findStudentByDiaryNumber(student.getDiaryNumber())!=null){
         errors.rejectValue("diaryNumber", "student.error.diary_number_should_be_unique");
     }
    }
}
