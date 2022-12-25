package journal.controllers;

import journal.dto.StudentDTO;
import journal.models.Student;
import journal.services.StudentService;
import journal.util.MarkErrorResponse;
import journal.util.MarkException;
import journal.util.StudentValidator;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static journal.util.ErrorsUtil.returnErrorsToClient;

@RestController
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;
    private final ModelMapper modelMapper;
    private final StudentValidator studentValidator;

    public StudentController(StudentService studentService, ModelMapper modelMapper, StudentValidator studentValidator) {
        this.studentService = studentService;
        this.modelMapper = modelMapper;
        this.studentValidator = studentValidator;
    }

    @PostMapping("/registerStudent")
    public ResponseEntity<HttpStatus> registration(@RequestBody @Valid StudentDTO studentDTO,
                                                   BindingResult bindingResult) {
        Student studentToRegister = studentService.convertDTOtoStudent(studentDTO);
        studentValidator.validate(studentToRegister, bindingResult);

        if (bindingResult.hasErrors()) {
            returnErrorsToClient(bindingResult);
        }
        studentService.addStudent(studentToRegister);
        return ResponseEntity.ok(HttpStatus.OK);

    }
    @ExceptionHandler
    private ResponseEntity<MarkErrorResponse> handleException(MarkException e){
        MarkErrorResponse response = new MarkErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
