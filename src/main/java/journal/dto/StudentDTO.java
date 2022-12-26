package journal.dto;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class StudentDTO {
    @NotEmpty
    @Size(min = 2, max = 100)
    private String name;

    @NotEmpty
    @Size (min = 2, max = 100)
    private String surname;

    @NotEmpty
    private String diaryNumber;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public StudentDTO(String name, String surname, String diaryNumber) {
        this.name = name;
        this.surname = surname;
        this.diaryNumber = diaryNumber;
    }

    public StudentDTO() {
    }
    public String getDiaryNumber() {
        return diaryNumber;
    }

    public void setDiaryNumber(String diaryNumber) {
        this.diaryNumber = diaryNumber;
    }
}
