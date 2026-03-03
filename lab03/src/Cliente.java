import java.util.ArrayList;
import java.util.List;

class Cliente {
    
    private String nome;
    private String endereco;
    private List <Veiculo> listaVeiculos = new ArrayList<Veiculo>();

    public Cliente(String nome, String endereco){
        
        this.nome = nome;
        this.endereco = endereco;
    }
    
    public String getNome(){
        return nome;
    }

    public String getEndereco(){
        return endereco;
    }
    
    public List<Veiculo> getListaVeiculos(){
        return listaVeiculos;
    }
    
    public void setNome(String nome){
        this.nome = nome;
    }
    
    public void setEndereco(String endereco){
        this.endereco = endereco;
    }
    
    public void setListaVeiculos(List<Veiculo> listaVeiculos){
        this.listaVeiculos = listaVeiculos;
    }
    
    public boolean addVeiculo(Veiculo veiculo){
        return this.listaVeiculos.add(veiculo);
    }

    public String toString(){
        String lista = "\n";
        int i = 1;

        for (Veiculo atual : listaVeiculos) {
            lista += "\tVeiculo " + i++ + ": " + atual.getPlaca();
        }

        return "Cliente " + nome + "\n\nEndereco: " + endereco + "\n\nListaVeiculos: " + lista;
    }
    
}