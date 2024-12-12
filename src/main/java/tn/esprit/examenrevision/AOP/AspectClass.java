package tn.esprit.examenrevision.AOP;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.ILoggerFactory;

import java.time.LocalDateTime;

@Aspect
@Slf4j

public class AspectClass {

    @AfterThrowing("execution(* tn.esprit.examenrevision.Service.*.ajouter*(..))")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        // Date système
        LocalDateTime startTime = LocalDateTime.now();
        log.info("Date système: " + startTime);

        // Mesure du temps d'exécution
        long start = System.currentTimeMillis();

        Object proceed = joinPoint.proceed();

        long executionTime = System.currentTimeMillis() - start;

        log.info("Méthode " + joinPoint.getSignature() + " exécutée en " + executionTime + "ms");

        return proceed;
    }
}
