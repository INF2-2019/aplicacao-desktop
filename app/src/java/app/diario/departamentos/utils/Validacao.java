package app.diario.departamentos.utils;

import app.diario.departamentos.model.Departamento;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Validacao {
    private static Pattern nomePattern = Pattern.compile("^{1,255}$");
    
    public static boolean validaNome(String nome){
        Matcher m = nomePattern.matcher(nome);
        return !m.matches();
    }
    
    public static boolean validaNome(Departamento depto){
        return validaNome(depto.getNome());
    }
}
