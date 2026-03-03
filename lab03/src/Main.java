import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Main {

    @SuppressWarnings("resource")
    public static String leitura(){
        Scanner scan = new Scanner(System.in);
        return scan.nextLine();
    }

    @SuppressWarnings("resource")
    public static String[] leitura(int limiteSplit){
        Scanner scan = new Scanner(System.in);
        return scan.nextLine().split(" ", limiteSplit);
    }

    public static Date stringToDate(String dataString){
        SimpleDateFormat formatar = new SimpleDateFormat("dd/MM/yyyy");
        formatar.setLenient(false);

        Date data = null;

        try {
            data = formatar.parse(dataString);
        } catch (ParseException excecao) {
            excecao.printStackTrace();
        }

        return data;
    }

    public static Cliente lerCliente(String tipo){
        String nome, endereco;

        if (tipo.equals("PF")) {

            String educacao, genero, classeEconomica, cpf;
            Date dataLicensa, dataNascimento;
            
            System.out.println("Cadastre um Cliente (Pessoa Física)\n");
            System.out.print("Nome: ");
            nome = leitura();
            System.out.print("Endereco: ");
            endereco = leitura();
            System.out.print("Data da Licensa(Formato dd/mm/aaaa): ");
            dataLicensa = stringToDate(leitura());
            System.out.print("Educacao: ");
            educacao = leitura();
            System.out.print("Genero: ");
            genero = leitura();
            System.out.print("Classe Econômica: ");
            classeEconomica = leitura();
            
            while(true){
                System.out.print("CPF(Formato XXX.XXX.XXX-XX): ");
                cpf = leitura();
                ClientePF dummyPF = new ClientePF("", "", new Date(), "", "", "", cpf, new Date());

                if (dummyPF.validarCPF(cpf))
                    break;
                System.out.println("CPF Invalido! Digite Novamente.");  
            }


            System.out.print("Data de Nascimento(Formato dd/mm/aaaa): ");
            dataNascimento = stringToDate(leitura());
            System.out.println();

            return new ClientePF(nome, endereco, dataLicensa, educacao, genero, classeEconomica, cpf, dataNascimento);
        }else{
            String cnpj;
            Date dataFundacao;

            System.out.println("Cadastre um Cliente (Pessoa Jurídica)\n");
            System.out.print("Nome: ");
            nome = leitura();
            System.out.print("Endereco: ");
            endereco = leitura();

            while(true){
                System.out.print("CNPJ(Formato XX.XXX.XXX/XXXX-XX): ");
                cnpj = leitura();
                ClientePJ dummyPJ = new ClientePJ("", "", cnpj, new Date());
                if (dummyPJ.validarCNPJ(cnpj))
                    break;
                System.out.println("CNPJ Invalido! Digite Novamente.");  
            }

            System.out.print("Data de Fundacao(Formato dd/mm/aaaa): ");
            dataFundacao = stringToDate(leitura());
            System.out.println();

            return new ClientePJ(nome, endereco, cnpj, dataFundacao);
        }
    }

    public static Seguradora lerSeguradora(){
        String nome, telefone, email, endereco;
        
        System.out.println("Cadastre a Seguradora\n");
        System.out.print("Nome: ");
        nome = leitura();
        System.out.print("Telefone: ");
        telefone = leitura();
        System.out.print("Email: ");
        email = leitura();
        System.out.print("Endereco: ");
        endereco = leitura();
        System.out.println();

        return new Seguradora(nome, telefone, email, endereco);
    }

    public static Veiculo lerVeiculo(){
        String placa, marca, modelo;
        int anoFabricacao;

        System.out.println("Cadastre o Veiculo\n");
        System.out.print("Placa: ");
        placa = leitura();
        System.out.print("Marca: ");
        marca = leitura();
        System.out.print("Modelo: ");
        modelo = leitura();
        System.out.print("Ano de Fabricacao: ");
        anoFabricacao = Integer.parseInt(leitura());
        System.out.println();

        return new Veiculo(placa, marca, modelo, anoFabricacao);
    }

    public static Sinistro lerSinistro(Seguradora seguradora, Veiculo veiculo, Cliente cliente){
        String data, endereco;

        System.out.println("Cadastre o Sinistro\n");
        System.out.print("Data: ");
        data = leitura();
        System.out.print("Endereco: ");
        endereco = leitura();

        return new Sinistro(data, endereco, seguradora, veiculo, cliente);

    }

    public static void main(String[] args){
        
        System.out.println("\n------------------Sistema de Cadastro-----------------------\n");

        Seguradora seguradora = lerSeguradora();

        ClientePJ pj1 = (ClientePJ)lerCliente("PJ");
        ClientePF pf1 = (ClientePF)lerCliente("PF");
        ClientePJ pj2 = (ClientePJ)lerCliente("PJ");

        pj1.addVeiculo(lerVeiculo());
        pf1.addVeiculo(lerVeiculo());
        pj2.addVeiculo(lerVeiculo());

        seguradora.gerarSinistro(lerSinistro(seguradora, pf1.getListaVeiculos().get(0), pf1));

        seguradora.cadastrarCliente(pj1);
        seguradora.cadastrarCliente(pf1);
        seguradora.cadastrarCliente(pj2);
        seguradora.removerCliente(pj2.getCnpj());

        seguradora.listarClientes("ALL");
        seguradora.visualizarSinistro(pf1.getCpf());
        seguradora.listarSinistros();

        System.out.println(seguradora);
        System.out.println(pj1);
        System.out.println(pf1);
        System.out.println(pf1.getListaVeiculos().get(0));
        System.out.println(seguradora.getListaSinistros().get(0));

        
    }

}