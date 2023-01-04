package journal.controllers;

import journal.dto.MarkDTO;
import journal.dto.MarksResponse;
import journal.models.Mark;
import journal.services.MarkService;
import journal.util.MarkErrorResponse;
import journal.util.MarkException;
import journal.util.MarkValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.stream.Collectors;

import static journal.util.ErrorsUtil.returnErrorsToClient;

@RestController
@RequestMapping("/marks")
public class MarkController {
    private final MarkService markService;
    private final MarkValidator markValidator;

    public MarkController(MarkService markService, MarkValidator markValidator) {
        this.markService = markService;
        this.markValidator = markValidator;
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> add(@RequestBody @Valid MarkDTO markDTO, BindingResult bindingResult){
        Mark markToAdd = markService.convertDTOtoMark(markDTO);
        markValidator.validate(markToAdd, bindingResult);
        if(bindingResult.hasErrors())
            returnErrorsToClient(bindingResult);
        markService.addMark(markToAdd);
        return ResponseEntity.ok(HttpStatus.OK);
    }
    @GetMapping()
    public MarksResponse getAllMarks(){
        MarksResponse marksResponse = new MarksResponse(markService.findAllMarks().stream()
                .map(markService::convertMarkToDTO).collect(Collectors.toList()));
        return marksResponse;
    }

    @GetMapping("/{student}")
    public MarksResponse getAllMarksOfStudent(@PathVariable ("student") String diaryNumber){
        MarksResponse marksResponse = new MarksResponse(markService
                .findAllMarksOfStudent(diaryNumber)
                .stream()
                .map(markService::convertMarkToDTO)
                .collect(Collectors.toList()));
        return marksResponse;
    }
    @GetMapping("/{student}/{subject}")
    public MarksResponse getSubjectMarksOfStudent(@PathVariable ("student") String diaryNumber,
                                                  @PathVariable String subject){
        MarksResponse marksResponse = new MarksResponse(markService
                .findSubjectMarksOfStudent(diaryNumber, subject)
                .stream()
                .map(markService::convertMarkToDTO)
                .collect(Collectors.toList()));
        return marksResponse;
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
