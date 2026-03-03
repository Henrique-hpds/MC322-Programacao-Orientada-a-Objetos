import java.util.Date;

public class ClientePJ extends Cliente {
    private final String cnpj;
    private Date dataFundacao;
    
    public ClientePJ(String nome, String endereco, String cnpj, Date dataFundacao){

        super(nome, endereco);
        this.cnpj = cnpj;
        this.dataFundacao = dataFundacao;
    }

    public String getCnpj(){
        return cnpj;
    }

    public Date getDataFundacao(){
        return dataFundacao;
    }

    public void setDataFundacao(Date dataFundacao){
        this.dataFundacao = dataFundacao;
    }

    public boolean validarCNPJ(String cnpj){
        String cnpjFormatado;

        // deixa só os números
        cnpjFormatado = cnpj.replaceAll("-", "");
        cnpjFormatado = cnpjFormatado.replaceAll("\\.", "");
        cnpjFormatado = cnpjFormatado.replaceAll("/", "");

        if (cnpjFormatado.length() != 14)
            return false;

        int[] pesos1 = {5,4,3,2,9,8,7,6,5,4,3,2};
        int[] pesos2 = {6,5,4,3,2,9,8,7,6,5,4,3,2};

        int soma = 0;

        // Dígitos finais dados pelo usuário
        int penultimoDigito = Character.getNumericValue(cnpjFormatado.charAt(12));
        int ultimoDigito = Character.getNumericValue(cnpjFormatado.charAt(13));

        for (int i = 0; i < cnpjFormatado.length() - 2; i++)
            soma += Character.getNumericValue(cnpjFormatado.charAt(i)) * pesos1[i];
        
        // verifica se o penúltimo dígito informado bate com o calculado
        if (soma % 11 < 2) {
            if (penultimoDigito != 0) 
                return false;
        } else{
            if (penultimoDigito != 11 - soma % 11)
                return false;
        }

        soma = 0;

        for (int i = 0; i < cnpjFormatado.length() - 1; i++)
            soma += Character.getNumericValue(cnpjFormatado.charAt(i)) * pesos2[i];

        // verifica se o último dígito informado bate com o calculado
        if (soma % 11 < 2) {
            if (ultimoDigito != 0) 
                return false;
        } else{
            if (ultimoDigito != 11 - soma % 11)
                return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return super.toString() + "\n\nCNPJ: " + cnpj + "\nDataFundacao: " + dataFundacao + "\n---------------------------------------";
    }
}