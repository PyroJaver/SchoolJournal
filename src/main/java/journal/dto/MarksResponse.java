package journal.dto;

import java.util.List;

public class MarksResponse {
    private List<MarkDTO> marks;

    public List<MarkDTO> getMarks() {
        return marks;
    }

    public void setMarks(List<MarkDTO> marks) {
        this.marks = marks;
    }

    public MarksResponse(List<MarkDTO> marks) {
        this.marks = marks;
    }
}
