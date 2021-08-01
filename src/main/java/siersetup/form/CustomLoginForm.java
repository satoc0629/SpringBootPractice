package siersetup.form;

import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Data
public class CustomLoginForm {
    private String userId;
    private String password;
}
