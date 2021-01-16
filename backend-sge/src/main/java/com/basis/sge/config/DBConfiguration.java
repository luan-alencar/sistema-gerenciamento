package com.basis.sge.config;

import com.basis.sge.servico.DBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.text.ParseException;

@Profile("test")
@Configuration
public class DBConfiguration {

    @Autowired
    private DBService dbService;

    public boolean instantiateDatabase() throws ParseException {
        dbService.instantiateDatabase();
        return true;
    }

}
