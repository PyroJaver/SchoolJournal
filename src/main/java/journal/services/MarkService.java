package journal.services;

import journal.dto.MarkDTO;
import journal.models.Mark;
import journal.models.Student;
import journal.repositories.MarkRepository;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public class MarkService {
    private final MarkRepository markRepository;
    private final StudentService studentService;
    private final ModelMapper modelMapper;

    public MarkService(MarkRepository markRepository, StudentService studentService, ModelMapper modelMapper) {
        this.markRepository = markRepository;
        this.studentService = studentService;
        this.modelMapper = modelMapper;
    }

    public List<Mark> findAllMarks() {
        return markRepository.findAll();
    }

    public List<Mark> findAllMarksOfStudent(String diaryNumber){
        return markRepository.findMarksByStudent(studentService.findStudentByDiaryNumber(diaryNumber));
    }

    public List<Mark> findSubjectMarksOfStudent(String diaryNumber, String subject){
        return markRepository.findMarksByStudentAndSubject(studentService
                        .findStudentByDiaryNumber(diaryNumber), subject);
    }

    @Transactional
    public void addMark(Mark mark) {
        setMarkDetails(mark);
        markRepository.save(mark);
    }

    public void setMarkDetails(Mark mark) {
        mark.setStudent(studentService.findStudentBySurname(mark.getStudent().getSurname()));
        mark.setMarkDateAndTime(new Date());
    }

    public Mark convertDTOtoMark(MarkDTO markDTO) {
        return modelMapper.map(markDTO, Mark.class);
    }

    public MarkDTO convertMarkToDTO(Mark mark) {
        return modelMapper.map(mark, MarkDTO.class);
    }

}
