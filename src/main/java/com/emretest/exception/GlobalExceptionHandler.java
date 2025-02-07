package com.emretest.exception;

import com.emretest.dto.ApiError;
import com.emretest.model.ResultModel;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.*;

@ControllerAdvice
public class GlobalExceptionHandler {

    private final MeterRegistry meterRegistry;

    public GlobalExceptionHandler(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }

    private void addMapValue(String value,List<String> errors) {
        errors.add(value);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResultModel<ApiError>> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        meterRegistry.counter("requests.errors").increment();
        List<String> errors = new ArrayList<>();

        for (ObjectError error : exception.getBindingResult().getAllErrors()) {
            String errorMessage = error.getDefaultMessage();
            addMapValue(errorMessage, errors);
        }
        return ResponseEntity.badRequest().body(addApiError(errors));
    }

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ResultModel<ApiError>> handleBaseException(BaseException baseException) {
        meterRegistry.counter("requests.errors").increment();
        List<String> errors = new ArrayList<>();
        errors.add(baseException.getMessage());
        return ResponseEntity.badRequest().body(addApiError(errors));
    }

    @ExceptionHandler(Exception.class)
    public  void handleException(Exception exception) {
        meterRegistry.counter("requests.errors").increment();
        System.out.println(exception.getMessage());
    }

    private ResultModel<ApiError> addApiError(List<String> errors) {
        ApiError apiError = new ApiError();
        apiError.setId(UUID.randomUUID().toString());
        apiError.setTimestamp(new Date());
        apiError.setErrors(errors);
        ResultModel<ApiError> resultModel= new ResultModel<>();
        resultModel.setError(apiError);
        return  resultModel;

    }
}
