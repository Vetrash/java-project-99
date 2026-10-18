package hexlet.code.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TaskParamsDTO {
    Integer start;
    Integer end;
    String sort;
    String order;
    String titleCont;
    Long assigneeId;
    String status;
    Long labelId;
}
