package com.annichiniflorencia.ClinicaOdontologicaAnnichiniFlorencia.login;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/")
    public String home() {
        return " <br>\n" +
                "<h1>Bienvenidos a la Clinica Odontologica Annichini</h1>" +
                "<a class=nav-link href=/index.html>Ingresar</a>";

    }


}
