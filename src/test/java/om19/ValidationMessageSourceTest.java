package om19;

import om19.api.request.SampleRequestDto;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.validation.*;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.util.Arrays;
import java.util.Locale;

@SpringBootTest
public class ValidationMessageSourceTest {

    Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MessageSource validationMessageSource;

    @Autowired
    private LocalValidatorFactoryBean validator;

    @Test
    public void test01() {
        var message = validationMessageSource.getMessage("NotNull.sampleRequestDto.message", null, Locale.JAPANESE);
        log.info(message);
    }

    @Test
    public void test02() {
        var request = new SampleRequestDto();
        Errors erros = new BeanPropertyBindingResult(request, "sampleRequestDto");
        ValidationUtils.invokeValidator(validator, request, erros);

        erros.getAllErrors().forEach(e -> log.info(e.getObjectName() + ":" + Arrays.toString(e.getCodes()) + "" + e.getDefaultMessage()));
    }
}
