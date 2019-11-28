package naya.starter.springbootstarterreinvokemethod.aspect;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import naya.starter.springbootstarterreinvokemethod.TryToRunAfterThrowing;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;


@Aspect
@Slf4j
public class ReinvokeMethodsThrowsException {

    private static int count = 1;

    @Pointcut("@annotation(naya.starter.springbootstarterreinvokemethod.TryToRunAfterThrowing)")
    public void afterThrowingTryToRun() {

    }

    @Pointcut("@annotation(param)")
    public void AfterThrowingTryToRunValues(TryToRunAfterThrowing param) {
    }

    @SneakyThrows
    @Around("afterThrowingTryToRun() &&AfterThrowingTryToRunValues(param)")
    public Object around(ProceedingJoinPoint joinPoint, TryToRunAfterThrowing param) {

        Object result;
        try {
           result = joinPoint.proceed();
        } catch (Exception e) {
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            Object[] args = joinPoint.getArgs();
            if (count <= param.numberTries()) {
                Thread.sleep(param.delay());
                ++count;
                result = signature.getMethod().invoke(joinPoint.getThis(), args);
            } else {
                count=0;
                throw e;
            }

        }
        count = 0;
        System.out.println(count+ " count proxy");
        return result;
    }


}
