package com.emretest.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Aspect
@Component
public class DataSourceAspect {

    @Before("@annotation(org.springframework.transaction.annotation.Transactional) && execution(* com.emretest.repositories.*.*(..))")
    public void switchDataSource(JoinPoint joinPoint) {
        // @Transactional(readOnly = true) olan sorgular için readDataSource'a yönlendirilecek
        Transactional transactional = ((MethodSignature) joinPoint.getSignature()).getMethod().getAnnotation(Transactional.class);
        if (transactional != null && transactional.readOnly()) {
            System.out.println("Read DataSource: {}");
            DataSourceContextHolder.setDataSourceType("readDataSource");
        } else {
            DataSourceContextHolder.setDataSourceType("writeDataSource");
        }
    }

    @After("@annotation(org.springframework.transaction.annotation.Transactional) && execution(* com.emretest.repositories.*.*(..))")
    public void clearDataSource() {
        DataSourceContextHolder.clearDataSourceType();
    }
}