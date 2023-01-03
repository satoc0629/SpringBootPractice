package om19.infrastructure;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class ErrorHandler {
    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String error(Throwable throwable, Model model) {
        log.error("ErrorHandler start", throwable);

        model.addAttribute("error", throwable);
        return "error";
    }
}
