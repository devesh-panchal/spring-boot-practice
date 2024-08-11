package com.spring.aop.demo.aspect;

import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.spring.aop.demo.Account;

@Component
@Aspect
@Order(2)
public class MyDemoLoggingAspect {

//	@Pointcut("execution(*  com.spring.aop.demo.dao.*.*(..))")
//	private void forDaoPackage() {
//	}
//
//	@Pointcut("execution(* com.spring.aop.demo.dao.*.get*(..))")
//	private void getter() {
//	}
//
//	// create a pointcut for setter methods
//	@Pointcut("execution(* com.spring.aop.demo.dao.*.set*(..))")
//	private void setter() {
//	}
//	
//    // create pointcut: include package ... exclude getter/setter
//    @Pointcut("forDaoPackage() && !(getter() || setter())")
//    private void forDaoPackageNoGetterSetter() {}
//
//	@Before("forDaoPackageNoGetterSetter()")
//	public void beforeAddAccountAdvice() {
//
//		System.out.println("\n=====>>> Executing @before advice on method");
//
//	}
//
//	@After("forDaoPackageNoGetterSetter()")
//	public void afterAddAccountAdvice() {
//
//		System.out.println("\n=====>>> Executing @after advice on method");
//
//	}
	
//	 @Before("com.luv2code.aopdemo.aspect.LuvAopExpressions.forDaoPackageNoGetterSetter()")
//	    public void beforeAddAccountAdvice() {
//	        System.out.println("\n=====>>> Executing @Before advice on method");
//	    }
	
	@Before("com.spring.aop.demo.aspect.LuvAopExpressions.forDaoPackageNoGetterSetter()")
    public void beforeAddAccountAdvice(JoinPoint theJoinPoint) {
        System.out.println("\n=====>>> Executing @Before advice on method");

        // display the method signature
        MethodSignature methodSignature = (MethodSignature) theJoinPoint.getSignature();

        System.out.println("Method: " + methodSignature);

        // display method arguments

        // get args
        Object[] args = theJoinPoint.getArgs();

        // loop thru args
        for (Object tempArg : args) {
            System.out.println(tempArg);

            if (tempArg instanceof Account) {

                // downcast and print Account specific stuff
                Account theAccount = (Account) tempArg;

                System.out.println("account name: " + theAccount.getName());
                System.out.println("account level: " + theAccount.getLevel());
            }
        }
    }
	
    @AfterReturning(
            pointcut = "execution(* com.spring.aop.demo.dao.AccountDAO.findAccounts(..))",
            returning = "result")
    public void afterReturningFindAccountsAdvice(JoinPoint theJoinPoint, List<Account> result) {

        // print out which method we are advising on
        String method = theJoinPoint.getSignature().toShortString();
        System.out.println("\n=====>>> Executing @AfterReturning on method: " + method);

        // print out the results of the method call
        System.out.println("\n=====>>> result is: " + result);
        
        // let's post-process the data ... let's modify it :-)

        // convert the account names to uppercase
        convertAccountNamesToUpperCase(result);

        System.out.println("\n=====>>> result is: " + result);

    }
    
    private void convertAccountNamesToUpperCase(List<Account> result) {

        // loop through accounts

        for (Account tempAccount : result) {

            // get uppercase version of name
            String theUpperName = tempAccount.getName().toUpperCase();

            // update the name on the account
            tempAccount.setName(theUpperName);
        }
    }
    
    @AfterThrowing(
            pointcut = "execution(* com.spring.aop.demo.dao.AccountDAO.findAccounts(..))",
            throwing = "theExc")
    public void afterThrowingFindAccountsAdvice(
            JoinPoint theJoinPoint, Throwable theExc) {

        // print out which method we are advising on
        String method = theJoinPoint.getSignature().toShortString();
        System.out.println("\n=====>>> Executing @AfterThrowing on method: " + method);

        // log the exception
        System.out.println("\n=====>>> The exception is: " + theExc);
    }
    
    @After("execution(* com.spring.aop.demo.dao.AccountDAO.findAccounts(..))")
    public void afterFinallyFindAccountsAdvice(JoinPoint theJoinPoint) {

        // print out which method we are advising on
        String method = theJoinPoint.getSignature().toShortString();
        System.out.println("\n=====>>> Executing @After (finally) on method: " + method);
    }
    
    
    @Around("execution(* com.spring.aop.demo.service.*.getFortune(..))")
    public Object aroundGetFortune(
            ProceedingJoinPoint theProceedingJoinPoint) throws Throwable {

        // print out method we are advising on
        String method = theProceedingJoinPoint.getSignature().toShortString();
        System.out.println("\n=====>>> Executing @Around on method: " + method);

        // get begin timestamp
        long begin = System.currentTimeMillis();

        // now, let's execute the method
        //Object result = theProceedingJoinPoint.proceed();
        
        // now, let's execute the method
        Object result = null;
        
        
        try {
            result = theProceedingJoinPoint.proceed();
        }
        catch (Exception exc) {
            // log the exception
            System.out.println(exc.getMessage());

            // give user a custom message
            result = "Major accident! But no worries, your private AOP helicopter is on the way!";
        }

        // get end timestamp
        long end = System.currentTimeMillis();

        // compute duration and display it
        long duration = end - begin;
        System.out.println("\n=====> Duration: " + duration / 1000.0 + " seconds");

        return result;
    }

}
