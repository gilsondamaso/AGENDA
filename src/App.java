import java.util.Date;
import java.util.Scanner;

import classes.Contato;
import classesDAO.ContatoDAO;

public class App {
    public static void main(String[] args) throws Exception {
        Contato cont = new Contato();
        ContatoDAO contatodao = new ContatoDAO();
        Scanner sc = new Scanner(System.in);
        int opc;
        do {
            
            System.out.println("SISTEMA DE CADASTRO");
            System.out.println("--------------------");
            System.out.println("1 - CADASTRAR NA CONTATO");
            System.out.println("2 - LISTAR CONTATOS");
            System.out.println("3 - ALTERAR CONTATO");
            System.out.println("4 - REMOVER CONTATO");
            System.out.println("0 - SAIR");
            opc = sc.nextInt();
            
            switch (opc) {
                case 1:
                    System.out.println("---------------------");
                    System.out.println("INFORME SEU NOME: ");
                    sc.nextLine();
                    cont.setNome(sc.nextLine());
                    System.out.println("INFORME SUA IDADE: ");
                    cont.setIdade(sc.nextInt());
                    cont.setDataCadastro( new Date ());
                    contatodao.Salvar(cont);                                        
                    break;
                case 2:
                    System.out.println("Lista de Contatos: ");
                    for (Contato c : contatodao.listarContatos()) {                    
                        System.out.print("Id: " + c.getId());
                        System.out.print(" Contato: " + c.getNome());
                        System.out.print(" Idade: " + c.getIdade());                        
                        System.out.println(" Data Cadastro: " + c.getDataCadastro());
                    }
                    break;
                case 3:  
                    System.out.println("Lista de Contatos: ");
                    for (Contato c : contatodao.listarContatos()) {                        
                        System.out.println("Id: " + c.getId() + " - Nome: " + c.getNome());                        
                    }                  
                    System.out.println("INFORME O ID DO CONTATO QUE DESEJA ALTERAR?");
                    int contAlt = sc.nextInt();
                    System.out.println("INFORME O NOME: ");
                    sc.nextLine();
                    cont.setNome(sc.nextLine());
                    System.out.println("INFORME NOVA IDADE: ");
                    cont.setIdade(sc.nextInt());
                    cont.setDataCadastro(new Date());
                    cont.setId(contAlt);
                    contatodao.alterarContato(cont);
                    break;
                case 4:
                    System.out.println("Lista de Contatos: ");
                    for (Contato c : contatodao.listarContatos()) {
                        
                        System.out.println("Id: " + c.getId() + " - Nome: " + c.getNome());                        
                    }                    
                    System.out.println("INFORME O ID DO CONTATO QUE DESEJA REMOVER?");
                    int contRem = sc.nextInt();
                    contatodao.removerContato(contRem);                    
                    break;
                default:                   
                    System.out.println("Escolha uma opção válida!!");
                    break;
            }
                
        } while (opc != 0);
        sc.close();
    }
}
