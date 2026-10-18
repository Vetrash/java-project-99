package hexlet.code.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.RequestParam;

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
