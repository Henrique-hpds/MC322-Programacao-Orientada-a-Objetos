import java.util.ArrayList;
import java.util.List;

class Seguradora {
    private String nome;
    private String telefone;
    private String email;
    private String endereco;
    private List <Sinistro> listaSinistros = new ArrayList<Sinistro>();
    private List <Cliente> listaClientes = new ArrayList<Cliente>();

    public Seguradora(String nome, String telefone, String email, String endereco){
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
    }
    
    public String getNome(){
        return nome;
    }

    public String getTelefone(){
        return telefone;
    }

    public String getEmail(){
        return email;
    }

    public String getEndereco(){
        return endereco;
    }

    public List <Sinistro> getListaSinistros(){
        return listaSinistros;
    }

    public List <Cliente> getListaClientes(){
        return listaClientes;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public void setTelefone(String telefone){
        this.telefone = telefone;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setEndereco(String endereco){
        this.endereco = endereco;
    }

    public void setListaSinistros(List <Sinistro> listaSinistros){
        this.listaSinistros = listaSinistros;
    }

    public void setListaClientes(List <Cliente> listaClientes){
        this.listaClientes = listaClientes;
    }

    public boolean cadastrarCliente(Cliente cliente){
        return listaClientes.add(cliente);
    }

    public boolean removerCliente(String cliente){

        // não ficou claro o que cliente é, então estou comparando nome, cpf e cnpj
        for (Cliente atual : listaClientes) {
        
            if (atual.getNome().equals(cliente)) {
                return listaClientes.remove(atual);
        
            }else if (atual instanceof ClientePF) {
        
                if (((ClientePF)atual).getCpf().equals(cliente))
                    return listaClientes.remove(atual);
        
            }else if (atual instanceof ClientePJ){
    
                if (((ClientePJ)atual).getCnpj().equals(cliente))
                    return listaClientes.remove(atual);
            }
        }

        return false;
    }

    public void listarClientes(String tipoCliente){
        String retorno = "\n";
        int i = 1;
        
        if (tipoCliente.equals("PF")){ 
            
            for (Cliente atual : listaClientes) 
                if (atual instanceof ClientePF){
                    retorno += "Cliente " + i++ + ":\n";
                    retorno += "Nome: " + atual.getNome() + "\n";
                    retorno += "CPF: " + ((ClientePF)atual).getCpf() + "\n\n";
                }

        }else if (tipoCliente.equals("PJ")){ 

            for (Cliente atual2 : listaClientes)
                if (atual2 instanceof ClientePJ){
                    retorno += "Cliente " + i++ + ":\n";
                    retorno += "Nome: " + atual2.getNome() + "\n";
                    retorno += "CNPJ: " + ((ClientePJ)atual2).getCnpj() + "\n\n";
                }
                    

        }else if (tipoCliente.equals("ALL")){
            for (Cliente atual3 : listaClientes){
                retorno += "Cliente " + i++ + ":\n";
                retorno += "Nome: " + atual3.getNome() + "\n";

                if (atual3 instanceof ClientePF)
                    retorno += "CPF: " + ((ClientePF)atual3).getCpf() + "\n\n";
                else if (atual3 instanceof ClientePJ)
                    retorno += "CNPJ: " + ((ClientePJ)atual3).getCnpj() + "\n\n";

            }
        }

        if (retorno.equals(""))
            System.out.println("Nâo há clientes dessa categoria no cadastro!");
        else System.out.println(retorno);
    }

    public boolean gerarSinistro(Sinistro sinistro){
        return listaSinistros.add(sinistro);
    }

    public boolean visualizarSinistro(String cliente){

        // não ficou claro o que cliente é, então estou comparando nome, cpf e cnpj
        for (Sinistro atual : listaSinistros) {

            if (atual.getCliente().getNome().equals(cliente)) {
                
                System.out.println(atual);
                return true;
            
            }else if (atual.getCliente() instanceof ClientePF) {
                
                if (((ClientePF)atual.getCliente()).getCpf().equals(cliente)) {
                    System.out.print(atual.toString());
                    return true;
                }

            }else if (atual.getCliente() instanceof ClientePJ) {
                
                if (((ClientePJ)atual.getCliente()).getCnpj().equals(cliente)) {
                    System.out.println(atual);
                    return true;
                }

            }
        }

        return false;
    }

    public void listarSinistros(){
        int i = 1;
        String lista = "\n";

        for (Sinistro atual : listaSinistros) {
            lista += "Sinistro " + i++ + ":";
            lista += "\nID: " + atual.getId();
            lista += "\nData: " + atual.getData();
            lista += "\nSeguradora: " + atual.getSeguradora().getNome();
            lista += "\nVeiculo: " + atual.getVeiculo().getPlaca();
            lista += "\n";

            if (atual.getCliente() instanceof ClientePF)
                lista += "CPF do Cliente: " + ((ClientePF)atual.getCliente()).getCpf();
            else if (atual.getCliente() instanceof ClientePJ)
                lista += "CNPJ do Cliente: " + ((ClientePJ)atual.getCliente()).getCnpj();

            lista += "\n\n";
        }

        System.out.println(lista);

    }

    public String toString() {
        int i = 1;
        String lista = "\n", lista2 = "\n";

        for (Sinistro atual : listaSinistros)
            lista += "\tSinistro " + i++ + ": " + atual.getId() + "\n"; 

        i = 1;

        for (Cliente atual2 : listaClientes) {

            if (atual2 instanceof ClientePF)
                lista2 += "\tCliente " + i++ + ": " + ((ClientePF)atual2).getCpf();
            else if (atual2 instanceof ClientePJ)
                lista2 += "\tCliente " + i++ + ": " + ((ClientePJ)atual2).getCnpj();

            lista2 += "\n";
        }

        return "\nSeguradora " + nome + "\n\nTelefone: " + telefone + "\nEmail: " + email + "\nEndereco: " + endereco + "\n\nListaSinistros: " + lista + "\nListaClientes: " + lista2 + "\n---------------------------------------";
    }

}
