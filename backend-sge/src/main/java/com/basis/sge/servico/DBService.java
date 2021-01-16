package com.basis.sge.servico;

import com.basis.sge.dominio.Usuario;
import com.basis.sge.repositorio.UsuarioRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class DBService {

    public UsuarioRepositorio userRepositorio;

    public void instantiateDatabase() throws ParseException {

        Usuario user1 = new Usuario(
                1,
                "user1",
                "000.000.000-00",
                "test1@gmail.com",
                "(00) 0-0000-0000",
                "00/00/0000",
                "0000000");

        Usuario user2 = new Usuario(
                2,
                "user2",
                "000.000.000-00",
                "test1@gmail.com",
                "(00) 0-0000-0000",
                "00/00/0000",
                "0000000");

        Usuario user3 = new Usuario(
                3,
                "user3",
                "000.000.000-00",
                "test1@gmail.com",
                "(00) 0-0000-0000",
                "00/00/0000",
                "0000000");

        Usuario user4 = new Usuario(
                4, "user4",
                "000.000.000-00",
                "test1@gmail.com",
                "(00) 0-0000-0000",
                "00/00/0000",
                "0000000");

        Usuario user5 = new Usuario(
                5, "user5",
                "000.000.000-00",
                "test1@gmail.com",
                "(00) 0-0000-0000",
                "00/00/0000",
                "0000000");

        userRepositorio.saveAll(Arrays.asList(user1, user2, user3, user4, user5));
    }
}