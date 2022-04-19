package com.annichiniflorencia.ClinicaOdontologicaAnnichiniFlorencia.exceptions;

public class BadRequestException extends Exception{

    public BadRequestException (String mensaje) {
        super(mensaje);
    }
}
