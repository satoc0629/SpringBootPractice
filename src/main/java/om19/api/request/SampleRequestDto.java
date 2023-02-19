package om19.api.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class SampleRequestDto implements Serializable {
    @NotNull(message = "{om19.warn.notnull}")
    @NotBlank(message = "{om19.warn.notblank}")
    private String message;
}
