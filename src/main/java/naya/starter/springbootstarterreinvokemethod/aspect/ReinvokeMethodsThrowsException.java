package naya.starter.springbootstarterreinvokemethod.aspect;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import naya.starter.springbootstarterreinvokemethod.TryToRunAfterThrowing;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;


@Aspect
@Slf4j
public class ReinvokeMethodsThrowsException {


    @Pointcut("@annotation(naya.starter.springbootstarterreinvokemethod.TryToRunAfterThrowing)")
    public void afterThrowingTryToRun() {

    }

    @Pointcut("@annotation(param)")
    public void AfterThrowingTryToRunValues(TryToRunAfterThrowing param) {
    }

    @SneakyThrows
    @Around("afterThrowingTryToRun() &&AfterThrowingTryToRunValues(param)")
    public Object around(ProceedingJoinPoint joinPoint, TryToRunAfterThrowing param) {
        int currentTry = 0;
        Throwable throwable = new Throwable();
        while (currentTry < param.numberTries()) {
            try {
                return joinPoint.proceed();
            } catch (Throwable e) {
                throwable = e;
                currentTry++;

                if (currentTry < param.numberTries()) {
                    Thread.sleep(param.delay());
                }

            }

        }
        throw throwable;
    }
}