package om19.api.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <a href="https://www.rfc-editor.org/rfc/rfc7807">rfc7807</a>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class APIErrorResponse {
    private String type;
    private String title;
    private String status;
    private String detail;
    private String instance;
}
