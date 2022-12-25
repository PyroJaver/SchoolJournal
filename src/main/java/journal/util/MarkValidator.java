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
    if (mark.getStudent()==null){
        return;
    }
    if(studentService.findStudentById(mark.getStudent().getId()).getName().isEmpty()){
        errors.rejectValue("student", "Такого студента не существует");
    }
    }
}
