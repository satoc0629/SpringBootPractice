package om19.infrastructure;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingAdvice {
    @Before("execution(* *..*Controller.*(..))")
    public void controllerBefore(JoinPoint joinPoint){
        log.info("{}#{} start", joinPoint.getTarget().getClass(), joinPoint.getSignature().getName());
    }
}
