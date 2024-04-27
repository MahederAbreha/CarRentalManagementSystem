package edu.miu.cs489.apsd.carrentalsystem.advice;

import edu.miu.cs489.apsd.carrentalsystem.exception.RentalException;
import edu.miu.cs489.apsd.carrentalsystem.exception.ReservationException;
import edu.miu.cs489.apsd.carrentalsystem.exception.UserException;
import edu.miu.cs489.apsd.carrentalsystem.exception.VehicleException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class Advice {
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(UserException.class)
    public Map<String, String> handle(UserException e) {
        return Map.of("error", e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handle(MethodArgumentNotValidException e) {
        Map<String,String> errorMap = new HashMap<>();
        e.getBindingResult().getFieldErrors().forEach(error ->
        {
            errorMap.put(error.getField(),error.getDefaultMessage());
        });
        return errorMap;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(ReservationException.class)
    public Map<String, String> handle(ReservationException e) {
        return Map.of("error", e.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(VehicleException.class)
    public Map<String, String> handle(VehicleException e) {
        return Map.of("error", e.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RentalException.class)
    public Map<String, String> handle(RentalException e) {
        return Map.of("error", e.getMessage());
    }

}
