package app.diario.transferencia.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validacao {
    private static Pattern cpfPontuadoPattern = Pattern.compile("^[0-9]{3}[\\.][0-9]{3}[\\.][0-9]{3}[\\-][0-9]{2}$");
    private static Pattern cpfNaoPontuadoPattern = Pattern.compile("^[0-9]{11}$");
    
    private static Matcher cpfPontuadoMatcher;
    private static Matcher cpfNaoPontuadoMatcher;
    
    public static boolean validaCpf(String cpf){
        cpfPontuadoMatcher = cpfPontuadoPattern.matcher(cpf);
        cpfNaoPontuadoMatcher = cpfNaoPontuadoPattern.matcher(cpf);
        
        if(cpfPontuadoMatcher.matches()){
            return true;
        } else if (cpfNaoPontuadoMatcher.matches()){
            return true;
        } else {
            return false;
        }
    }
}
