package journal.services;

import journal.dto.StudentDTO;
import journal.models.Student;
import journal.repositories.StudentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;

    public StudentService(StudentRepository studentRepository, ModelMapper modelMapper) {
        this.studentRepository = studentRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public Student findStudentById(Long id){
        Optional<Student> student = studentRepository.findById(id);
        if(student.isPresent()) {
            return student.get();
        } else return new Student();
    }

    @Transactional
    public List<Student> findAllStudents(){
        return studentRepository.findAll();
    }

    @Transactional
    public void addStudent (Student student){
        studentRepository.save(student);
    }

    @Transactional
    public Student findStudentBySurname(String surname){
         return studentRepository.findStudentBySurname(surname);
    }

    public Student convertDTOtoStudent(StudentDTO studentDTO){
        return modelMapper.map(studentDTO, Student.class);
    }

    public  StudentDTO convertStudentToDTO(Student student){
        return modelMapper.map(student, StudentDTO.class);
    }
}
