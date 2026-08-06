package utils;

public class ValidacaoUtil {

    private ValidacaoUtil() {}

    public static void validarNome(String nome){
        if(nome == null || nome.isEmpty()){
            throw new IllegalArgumentException("Nome precisa ser preenchido.");
        }
    }

    public static void validarCPF(String cpf){
        if(cpf == null || cpf.isEmpty()){
            throw new IllegalArgumentException("CPF precisa ser preenchido.");
        }

        if(cpf.length() != 11){
            throw new IllegalArgumentException("CPF precisa contar somente 11 caracteres.");
        }
    }
}
