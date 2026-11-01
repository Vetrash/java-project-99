
package hexlet.code.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LabelCreateDTO {

    @NotBlank(message = "must not be blank")
    @Size(min = 3, max = 1000, message = "Name must be between 3 and 1000 characters")
    private String name;
}

