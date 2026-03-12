import java.io.FileNotFoundException;
import java.util.Scanner;

public class MenuLogin {

    public static void menuLogin(String[][] matrizAnimais,String[][] matrizClientes,String[][] matrizInteracoes) throws FileNotFoundException {

        Scanner input = new Scanner(System.in);

        int opcaoLogin;
        String username, password;

        do {

            System.out.println("\n\n ===== MENU LOGIN =====");
            System.out.println("1. ADMIN");
            System.out.println("2. CLIENTE");
            System.out.println("0. SAIR");

            System.out.print("Tipo de Utilizador: ");
            opcaoLogin = input.nextInt();

            switch (opcaoLogin) {

                case 1: // ADMIN

                    System.out.print("\nUsername: ");
                    username = input.next();

                    System.out.print("Password: ");
                    password = input.next();

                    if (username.equals("admin") && password.equals("code")) {
                        System.out.println("Login de ADMIN realizado com sucesso!");// Login válido
                        System.out.println("\n");
                        MenuAdmin.menuAdmin(matrizAnimais,matrizClientes,matrizInteracoes);
                    } else {
                        System.out.println("Credenciais inválidas. Acesso negado.");
                    }

                    break;


                case 2: // CLIENTE
                    System.out.println("Bem-vindo, CLIENTE!");
                    System.out.println("\n");
                    MenuCliente.menuCliente(matrizAnimais,matrizClientes,matrizInteracoes);
                    break;

                case 0: // SAIR
                    imprimirCopyright();
                    break;

                default:
                    System.out.println("\nOpção Inválida");
                    break;
            }

        } while (opcaoLogin != 0);

    }
    //Função para imprimir os créditos com moldura ASCII
    public static void imprimirCopyright() {
        System.out.println("\n╔══════════════════════════════════════════════════════════════════╗");
        System.out.println("║                                                                  ║");
        System.out.println("║                 🦎  Projeto: CODE SAVANNA  🦁                    ║");
        System.out.println("║                                                                  ║");
        System.out.println("║        Desenvolvido por: [CATARINA RATO]                         ║");
        System.out.println("║        © 2025 Todos os direitos reservados.                      ║");
        System.out.println("║                                                                  ║");
        System.out.println("║        Obrigado por utilizar este programa! 🐾                   ║");
        System.out.println("║                                                                  ║");
        System.out.println("╚══════════════════════════════════════════════════════════════════╝");
        System.out.println();
    }

    public static void main(String[] args) throws FileNotFoundException {

        String[][] matrizAnimais= Biblioteca.ficheiroParaMatriz("animais.csv");
        String[][] matrizClientes= Biblioteca.ficheiroParaMatriz("clientes.csv");
        String[][] matrizInteracoes= Biblioteca.ficheiroParaMatriz("interacoes.csv");

        menuLogin(matrizAnimais,matrizClientes,matrizInteracoes);
    }

}
