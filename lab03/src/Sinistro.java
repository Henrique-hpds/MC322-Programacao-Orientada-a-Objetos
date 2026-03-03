import java.util.Date;
import java.util.Random;

class Sinistro {
    final private int id;
    private String data;
    private String endereco;
    private Seguradora seguradora;
    private Veiculo veiculo;
    private Cliente cliente;

    /* gera um inteiro id a partir de um número pseudo-aleatório e dos hashs de data e endereço */
    private int generateId(){

        Date data_atual = new Date();
        Random rand = new Random();

        int denominador = rand.nextInt(100);

        while(denominador == 0)
            denominador = rand.nextInt(100);      
        
        int retorno = (this.data.hashCode() + data_atual.hashCode() + this.endereco.hashCode()) / denominador;
        
        if (retorno < 0)
            retorno *= -1;

        return retorno;
    }

    public Sinistro(String data, String endereco, Seguradora seguradora, Veiculo veiculo, Cliente cliente){
        this.data = data;
        this.endereco = endereco;
        this.seguradora = seguradora;
        this.veiculo = veiculo;
        this.cliente = cliente;
        this.id = generateId();
    }

    public int getId(){
        return id;
    }

    public String getData(){
        return data;
    }
    
    public String getEndereco(){
        return endereco;
    }

    public Seguradora getSeguradora(){
        return seguradora;
    }

    public Veiculo getVeiculo(){
        return veiculo;
    }

    public Cliente getCliente(){
        return cliente;
    }

    public void setData(String data){
        this.data = data;
    }

    public void setEndereco(String endereco){
        this.endereco = endereco;
    }

    public void setSeguradora(Seguradora seguradora){
        this.seguradora = seguradora;
    }

    public void setVeiculo(Veiculo veiculo){
        this.veiculo = veiculo;
    }

    public void setCliente(Cliente cliente){
        this.cliente = cliente;
    }

    public String toString() {

        String retornoCliente = "";

        if (cliente instanceof ClientePF) 
            retornoCliente += ((ClientePF)cliente).getCpf();
        else if (cliente instanceof ClientePJ)
            retornoCliente += ((ClientePJ)cliente).getCnpj();

        return "\nSinistro ID " + id + "\nData: " + data + "\nEndereco: " + endereco + "\nSeguradora: " + seguradora.getNome() + "\nVeiculo: " + veiculo.getPlaca() + "\nCliente: " + retornoCliente + "\n---------------------------------------";
    }

}
