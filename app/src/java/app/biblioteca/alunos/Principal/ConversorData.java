package app.biblioteca.alunos.Principal;

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Usuário
 */
public class ConversorData{

    public static LocalDate converter(Date dataConverter){
        return dataConverter.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

}