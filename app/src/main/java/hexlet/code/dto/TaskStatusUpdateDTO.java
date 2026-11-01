package hexlet.code.dto;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.openapitools.jackson.nullable.JsonNullable;

@Setter
@Getter
public class TaskStatusUpdateDTO {

    @Size(max = 255, message = "Name must not exceed 255 characters")
    private JsonNullable<String> name = JsonNullable.undefined();

    @Pattern(
            regexp = "^[a-z0-9]+(-[a-z0-9]+)*$",
            message = "must not be blank"
    )
    private JsonNullable<String> slug = JsonNullable.undefined();
}
