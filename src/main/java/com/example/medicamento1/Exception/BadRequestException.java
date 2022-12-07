package com.example.medicamento1.Exception;

public class BadRequestException extends Exception{

    public BadRequestException(String message) {
        super(message);//só vai nos trazer uma mensagem de retorno
    }
}
