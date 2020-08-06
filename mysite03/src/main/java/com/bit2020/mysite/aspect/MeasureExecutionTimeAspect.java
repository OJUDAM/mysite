package com.bit2020.mysite.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class MeasureExecutionTimeAspect {
	
	@Around("execution(* *..*.repository.*.*(..)) || execution(* *..*.controller.*.*(..)) || execution(* *..*.service.*.*(..))")
	public Object aroundAspect(ProceedingJoinPoint pjp) throws Throwable {
		StopWatch sw = new StopWatch();
		
		//before
		sw.start();
		
		Object result = pjp.proceed();
		
		//after
		sw.stop();
		Long totalTime = sw.getTotalTimeMillis();
		
		String className = pjp.getTarget().getClass().getName();
		String methodName = pjp.getSignature().getName();
		String tasj = className +"." + methodName;
		
		System.out.println("[Execution Time]["+tasj+"]" + totalTime + "mills");
		
		return result;
	}
}
