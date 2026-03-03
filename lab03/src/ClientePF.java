import java.util.Date;

public class ClientePF extends Cliente {
    private final String cpf;
    private String genero;
    private Date dataLicensa;
    private String educacao;
    private Date dataNascimento;
    private String classeEconomica;

    public ClientePF(String nome, String endereco, Date dataLicensa, String educacao, String genero, String classeEconomica, String cpf, Date dataNascimento){
        super(nome, endereco);
        
        this.cpf = cpf;
        this.genero = genero;
        this.dataLicensa = dataLicensa;
        this.educacao = educacao;
        this.dataNascimento = dataNascimento;
        this.classeEconomica = classeEconomica;
    }

    public String getCpf(){
        return cpf;
    }

    public String getGenero(){
        return genero;
    }

    public Date getDataLicensa(){
        return dataLicensa;
    }

    public String getEducacao(){
        return educacao;
    }

    public Date getDataNascimento(){
        return dataNascimento;
    }

    public String getClasseEconomica(){
        return classeEconomica;
    }

    public void setGenero(String genero){
        this.genero = genero;
    }

    public void setDataLicensa(Date dataLicensa){
        this.dataLicensa = dataLicensa;
    }

    public void setEducacao(String educacao){
        this.educacao = educacao;
    }

    public void setDataNascimento(Date dataNascimento){
        this.dataNascimento = dataNascimento;
    }

    public void setClasseEconomica(String ClasseEconomica){
        this.classeEconomica = ClasseEconomica;
    }

    public boolean validarCPF(String cpf){

        String cpfFormatado;

        // deixa só os números
        cpfFormatado = cpf.replaceAll("-", "");
        cpfFormatado = cpfFormatado.replaceAll("\\.", "");


        if (cpfFormatado.length() != 11)
            return false;


        char anterior = cpfFormatado.charAt(0);

        // verifica se todos os digitos são iguais
        for (int i = 0; i < 11; i++) {
            
            if (anterior != cpfFormatado.charAt(i)) 
                break;
            
            if (i == cpfFormatado.length() - 1)
                return false;

        }

        
        int multiplicador = 10, soma = 0;

        // Dígitos finais dados pelo usuário
        int penultimoDigito = Character.getNumericValue(cpfFormatado.charAt(9));
        int ultimoDigito = Character.getNumericValue(cpfFormatado.charAt(10));

        // soma da multiplicação de cada um dos 9 primeiros termos por um multiplicador que começa no 10 e decresce 1 unidade a cada passo
        for (int i = 0; i < 9; i++)
            soma += Character.getNumericValue(cpfFormatado.charAt(i)) * multiplicador--;


        int penultimoCorreto, ultimoCorreto;

        // verifica se o penúltimo dígito informado bate com o calculado
        if (soma % 11 < 2) 
            penultimoCorreto = 0;
        else penultimoCorreto = 11 - (soma % 11);

        if (penultimoCorreto != penultimoDigito)
            return false;


        soma = 0;
        multiplicador = 11;

        // soma da multiplicação de cada um dos 9 primeiros termos + penúltimo dígito por um multiplicador que começa no 11 e decresce 1 unidade a cada passo
        for (int i = 0; i < 10; i++)
            soma += Character.getNumericValue(cpfFormatado.charAt(i)) * multiplicador--;


        // verifica se o último dígito informado bate com o calculado
        if (soma % 11 < 2) 
            ultimoCorreto = 0;
        else ultimoCorreto = 11 - (soma % 11);

        if (ultimoCorreto != ultimoDigito)
            return false;

        return true;
    }

    @Override
    public String toString() {
        return super.toString() + "\n\nCPF: " + cpf + "\nGenero: " + genero + "\nDataLicensa: " + dataLicensa + "\nEducacao: " + educacao + "\nDataNascimento: " + dataNascimento + "\nClasseEconomica: " + classeEconomica + "\n---------------------------------------";
    }

    
}
