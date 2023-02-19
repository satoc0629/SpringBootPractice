package om19.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import om19.api.exception.SampleException;
import om19.api.response.APIErrorResponse;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Arrays;
import java.util.Locale;
import java.util.Objects;

@Component
@ControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    private final MessageSource messageSource;

    @ExceptionHandler(SampleException.class)
    public ResponseEntity<APIErrorResponse> sampleExceptionHandler(SampleException exception) {
        log.error(exception.getMessage(), exception);
        var error = new APIErrorResponse();
        error.setTitle(messageSource.getMessage("om19.error.0001", null, Locale.JAPANESE));
        error.setDetail(exception.getMessage());
        return ResponseEntity.badRequest().body(error);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.info("handleMethodArgumentNotValid start");
        exception.getFieldErrors().stream()
                .map(e -> e.getCode() + ":" + e.getDefaultMessage())
                .forEach(log::error);

        var error = new APIErrorResponse();
        var detail = exception.getFieldErrors().stream()
                .map(e -> e.getField() + ":" +
                        Arrays.stream(Objects.requireNonNull(e.getCodes()))
                                .reduce((a, b) -> a + "," + b) + ":" + e.getDefaultMessage())
                .reduce(String::concat);
        detail.ifPresent(error::setDetail);
        return ResponseEntity.badRequest().body(error);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
                                                                          HttpHeaders headers, HttpStatus status, WebRequest request) {
        var error = new APIErrorResponse();
        error.setTitle(ex.getMessage());
        error.setDetail(ex.getParameterName() + ":" + ex.getParameterType());
        return ResponseEntity.badRequest().body(error);
    }

}
