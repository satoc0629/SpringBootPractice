package om19.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ValidationCheckForm implements Serializable {
    @NotBlank
    @Size(min = 1, max = 10
//            , message = "{Size.validationCheckForm.name}"
    )
    private String name;

    @NotNull
    private Number age;
}
