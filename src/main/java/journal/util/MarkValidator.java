package journal.util;

import journal.models.Mark;
import journal.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class MarkValidator implements Validator {
    private final StudentService studentService;

    @Autowired
    public MarkValidator(StudentService studentService) {
        this.studentService = studentService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Mark.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
    Mark mark = (Mark) target;
    if (studentService.findStudentByDiaryNumber(mark.getStudent().getDiaryNumber())==null){
        errors.rejectValue("student", "mark.error.student_not_found");
    }
    if(mark.getMark()<1||mark.getMark()>5){
        errors.rejectValue("mark", "mark.error.mark_not_in_range");
    }
    }
}
