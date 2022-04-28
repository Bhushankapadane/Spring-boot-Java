package com.assignment.userservice.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/* Annotate this class with @Aspect and @Component */
@Aspect
@Component
public class LoggingAspect {

	/*
	 * Write loggers for each of the methods of controller, any particular method
	 * will have all the four aspectJ annotation
	 * (@Before, @After, @AfterReturning, @AfterThrowing).
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(LoggingAspect.class);

	@Before("execution(* com.assignment.userservice..*.*(..))")
	public void beforeCreateAdvice(JoinPoint jp) {
		LOGGER.info("Before Advice Invoked", jp.getSignature());
	}

	@After("execution(* com.assignment.userservice..*.*(..))")
	public void afterCreateAdvice(JoinPoint jp) {
		LOGGER.info("After Advice Invoked", jp.getSignature());
	}
	
	@AfterReturning(pointcut = "execution(* com.assignment.userservice..*.*(..))",returning = "retVal")
	public void afterReturning(Object retVal) {
		LOGGER.info("AfterReturning Invoked" + retVal);
	}
	
	@AfterThrowing(pointcut = "execution(* com.assignment.userservice..*.*(..))",throwing = "exp")
	public void afterReturning(Exception exp) {
		LOGGER.info("AfterThrowing Invoked" + exp.getMessage());
	}
}
