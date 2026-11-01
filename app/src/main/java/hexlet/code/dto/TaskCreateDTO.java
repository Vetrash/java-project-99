package hexlet.code.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class TaskCreateDTO {

    private Integer index;

    @NotBlank(message = "must not be blank")
    @Size(min = 1, message = "Title must not be empty")
    private String title;

    private String content;

    @JsonProperty("assignee_id")
    @JsonAlias("assigneeId")
    private Long assigneeId;

    @NotBlank(message = "must not be blank")
    private String status;

    private List<Long> taskLabelIds;
}
