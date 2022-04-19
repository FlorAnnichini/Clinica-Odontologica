package com.annichiniflorencia.ClinicaOdontologicaAnnichiniFlorencia.login;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/")
    public String home() {
        return " <div style=\"background: linear-gradient(#B7CADB, #F3E9DD);\" >" +
                "<br>\n" +
                "<h1 style=\"font-family: Arial;\">Bienvenidos a la Clinica Odontologica Annichini</h1>" +
                "<a class=nav-link href=/index.html style=\"font-family: Arial;\">        Ingresar</a>" +
                "</div>";

    }


}
