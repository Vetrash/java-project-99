package hexlet.code.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.openapitools.jackson.nullable.JsonNullable;

import java.util.Set;

@Setter
@Getter
public class TaskUpdateDTO {
    private JsonNullable<Integer> index = JsonNullable.undefined();

    private JsonNullable<String> title = JsonNullable.undefined();

    private JsonNullable<String> content = JsonNullable.undefined();

    @JsonProperty("assignee_id")
    @JsonAlias("assigneeId")
    private JsonNullable<Long> assigneeId = JsonNullable.undefined();

    private JsonNullable<String> status = JsonNullable.undefined();

    private JsonNullable<Set<Long>> taskLabelIds = JsonNullable.undefined();
}
