package com.shopproject.technicalTest.exception;
//execpciones que corren en tiempo real
public class NotFoundException extends RuntimeException{
    public NotFoundException(String msm){
        super(msm);
    }
}
