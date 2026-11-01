package hexlet.code.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TaskParamsDTO {

    @Min(value = 1, message = "Start page must be at least 1")
    private Integer start;

    @Min(value = 1, message = "End page must be at least 1")
    private Integer end;

    private String sort;

    @Pattern(regexp = "^(asc|desc)$", message = "Order must be 'asc' or 'desc'")
    private String order;

    private String titleCont;

    private Long assigneeId;

    private String status;

    private Long labelId;
}
