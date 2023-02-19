package om19.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BeanPropertyBindingResult;

import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import om19.form.ValidationCheckForm;

import javax.validation.ConstraintViolation;
import java.util.Arrays;
import java.util.Set;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ValidationCheckController {
    private final LocalValidatorFactoryBean localValidatorFactoryBean;
    private final Validator validator;
    private final MessageSource validationMessageSource;

    @ModelAttribute
    public ValidationCheckForm validationCheckForm() {
        return new ValidationCheckForm();
    }

    @GetMapping("/ValidationCheck")
    public String getValidationCheck(Model model) {
        return "validationCheckForm";
    }

    @PostMapping("/ValidationCheck")
    public String ValidationCheck(@ModelAttribute ValidationCheckForm form,
                                  BeanPropertyBindingResult bindingResult) {
        localValidatorFactoryBean.setValidationMessageSource(validationMessageSource);
        Set<ConstraintViolation<ValidationCheckForm>> result = localValidatorFactoryBean.validate(form);
        log.info("result.size:{}", result.size());

        for (ConstraintViolation<ValidationCheckForm> element : result) {
            log.info("{}", element.getMessage());
        }

        validator.validate(form, bindingResult);

        log.info("form:{}", form);
        log.info("bindingResult.hasFieldErrors():{}", bindingResult.hasFieldErrors());

        if (bindingResult.hasErrors()) {
            log.info("validation error exists.");

            bindingResult.getAllErrors().forEach(e ->
                    log.info(e.getObjectName() + ":" + e.getDefaultMessage()+":"+ Arrays.toString(e.getCodes())));
            log.info("--------------------------");
            bindingResult.getFieldErrors().forEach(e -> log.info("{}:{}:{}:{}:{}:{}",
                    e.getObjectName()
                    , e.getField(),
                    e.getDefaultMessage(),
                    e.getCode(),
                    e.getCodes(),
                    e));
        }

        return "validationCheckForm";
    }
}
