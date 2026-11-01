package hexlet.code.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.openapitools.jackson.nullable.JsonNullable;

import java.util.Set;

@Setter
@Getter
public class TaskUpdateDTO {

    @Min(value = 0, message = "Index must be non-negative")
    private JsonNullable<Integer> index = JsonNullable.undefined();

    @Size(min = 1, message = "Title must not be empty")
    private JsonNullable<String> title = JsonNullable.undefined();

    private JsonNullable<String> content = JsonNullable.undefined();

    @JsonProperty("assignee_id")
    @JsonAlias("assigneeId")
    private JsonNullable<Long> assigneeId = JsonNullable.undefined();

    private JsonNullable<String> status = JsonNullable.undefined();

    private JsonNullable<Set<@NotNull Long>> taskLabelIds = JsonNullable.undefined();
}
