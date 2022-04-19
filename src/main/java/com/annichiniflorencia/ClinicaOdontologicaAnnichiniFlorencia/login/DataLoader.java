package com.annichiniflorencia.ClinicaOdontologicaAnnichiniFlorencia.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    IUserRepository iUserRepository;

    public DataLoader(IUserRepository iUserRepository) {
        this.iUserRepository = iUserRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password1 = passwordEncoder.encode("password1");
        String password2 = passwordEncoder.encode("password2");

        iUserRepository.save(new AppUser("Flor", "flor", "flor@gmail.com", password1, AppUsuarioRoles.ROLE_ADMIN));
        iUserRepository.save(new AppUser("Morita", "morita", "morita@gmail.com", password2, AppUsuarioRoles.ROLE_USER));


    }
}
