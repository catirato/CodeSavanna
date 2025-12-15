import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

public class MenuCliente {

    public static void menuCliente(String[][] matrizAnimais, String[][] matrizClientes, String[][] matrizInteracoes) {

        Scanner input = new Scanner(System.in);

        int opcao;

        do {

            System.out.println("===== MENU CLENTE CODE SAVANNA  =====");
            System.out.println("1. Ver catálogo de animais por habitat");
            System.out.println("2. Ver atividades de um animal (espetáculos e alimentações)");
            System.out.println("3. Simular apadrinhamento de um animal");
            System.out.println("4. Jogo: adivinha a espécie");
            System.out.println("0. Sair");

            System.out.print("Opção: ");
            opcao = input.nextInt();

            switch (opcao) {

                case 1: // Ver catálogo de animais por habitat
                    catalogoAnimais(matrizAnimais);
                    break;

                case 2: // Ver atividades de um animal (espetáculos e alimentações)
                    atividadeAnimais(matrizAnimais, matrizInteracoes);
                    break;

                case 3: //  Simular apadrinhamento de um animal
                    simularApadrinhamento(matrizAnimais, matrizClientes);
                    break;

                case 4: //Jogo: adivinha a espécie
                    jogoAdivinha(matrizAnimais);
                    break;

                case 0: // Sair
                    break;

                default:
                    System.out.println("\nOpção Inválida");
                    break;
            }

        } while (opcao != 0);

    }

    public static void catalogoAnimais(String[][] matrizAnimais) {

        //Criar um array para guardar habitats únicos
        String[] habitatsUnicos = new String[matrizAnimais.length];
        int contadorHabitats = 0;

        //Encontrar todos os habitats únicos
        for (int i = 0; i < matrizAnimais.length; i++) {
            String habitatAtual = matrizAnimais[i][3]; // coluna 3 = habitat
            boolean jaExiste = false;

            // Verificar se o habitat já foi adicionado
            for (int j = 0; j < contadorHabitats; j++) {
                if (habitatAtual.equals(habitatsUnicos[j])) {
                    jaExiste = true;
                    break;
                }
            }

            // Se ainda não existe, adiciona-se
            if (!jaExiste) {
                habitatsUnicos[contadorHabitats] = habitatAtual;
                contadorHabitats++;
            }
        }
        System.out.println("\n=== CATÁLOGO DE ANIMAIS POR HABITAT ===\n");

        //Para cada habitat, listar os animais (nome e espécie)
        for (int h = 0; h < contadorHabitats; h++) {
            String habitat = habitatsUnicos[h];
            System.out.println("*** " + habitat + " ***");

            // Percorrer todos os animais para encontrar os que pertencem a este habitat
            for (int i = 0; i < matrizAnimais.length; i++) {
                if (matrizAnimais[i][3].equals(habitat)) {
                    String nome = matrizAnimais[i][1];    // coluna 1 = nome do animal
                    String especie = matrizAnimais[i][2]; // coluna 2 = espécie
                    System.out.println("- " + nome + " (" + especie + ")");
                }
            }
            System.out.println(); // linha em branco entre habitats
        }
    }

    public static void atividadeAnimais(String[][] matrizAnimais, String[][] matrizInteracoes) {

        //Obter animal usando a função da Biblioteca
        String[] dadosAnimal = Biblioteca.pedirEConfirmarAnimal(matrizAnimais);

        if (dadosAnimal == null) {
            return; // animal não existe
        }

        String idAnimal = dadosAnimal[0];
        String nomeAnimal = dadosAnimal[1];
        String especieAnimal = dadosAnimal[2];

        System.out.println("=== ACTIVIDADES REGISTADAS ===\n");
        System.out.println("\nAtividades do animal " + nomeAnimal + " (" + especieAnimal + "):");

// Arrays simples para armazenar os eventos
        String[] eventosEspetaculos = new String[100];
        int[] contagemEspetaculos = new int[100];
        int contadorEspetaculos = 0;

        String[] eventosAlimentacao = new String[100];
        int[] contagemAlimentacao = new int[100];
        int contadorAlimentacao = 0;

// Percorrer interações e contar eventos
        for (int i = 0; i < matrizInteracoes.length; i++) {

            String idAnimalInter = matrizInteracoes[i][3];
            String tipo = matrizInteracoes[i][2];
            String nomeEvento = matrizInteracoes[i][4];

            if (idAnimalInter.equals(idAnimal)) {

                if (tipo.equals("ESPETACULO")) {

                    boolean existe = false;

                    for (int j = 0; j < contadorEspetaculos; j++) {
                        if (eventosEspetaculos[j].equals(nomeEvento)) {
                            contagemEspetaculos[j]++;
                            existe = true;
                            break;
                        }
                    }

                    if (!existe) {
                        eventosEspetaculos[contadorEspetaculos] = nomeEvento;
                        contagemEspetaculos[contadorEspetaculos] = 1;
                        contadorEspetaculos++;
                    }

                } else if (tipo.equals("ALIMENTACAO")) {

                    boolean existe = false;

                    for (int j = 0; j < contadorAlimentacao; j++) {
                        if (eventosAlimentacao[j].equals(nomeEvento)) {
                            contagemAlimentacao[j]++;
                            existe = true;
                            break;
                        }
                    }

                    if (!existe) {
                        eventosAlimentacao[contadorAlimentacao] = nomeEvento;
                        contagemAlimentacao[contadorAlimentacao] = 1;
                        contadorAlimentacao++;
                    }
                }
            }
        }

        //Imprimir resultados
        System.out.println("\nESPETÁCULOS:");
        if (contadorEspetaculos == 0) {
            System.out.println("- Nenhum espetáculo registado.\n");
        } else {
            for (int i = 0; i < contadorEspetaculos; i++) {
                System.out.println("- " + eventosEspetaculos[i] + " (" + contagemEspetaculos[i] + " vezes)");
            }
        }

        System.out.println("\nALIMENTAÇÕES:");
        if (contadorAlimentacao == 0) {
            System.out.println("- Nenhuma alimentação registada.\n");
        } else {
            for (int i = 0; i < contadorAlimentacao; i++) {
                System.out.println("- " + eventosAlimentacao[i] + " (" + contagemAlimentacao[i] + " vezes)\n");
            }
        }
    }

    public static void simularApadrinhamento(String[][] matrizAnimais, String[][] matrizClientes) {

        Scanner input = new Scanner(System.in);
        System.out.println("\n=== SIMULAR APADRINHAMENTO ===");

        //Pedir dados do cliente
        System.out.print("Nome do cliente: ");
        String nomeCliente = input.nextLine();

        System.out.print("Email do cliente: ");
        String emailCliente = input.nextLine();

        //Mostrar lista de animais disponíveis
        System.out.println("\nANIMAIS DISPONÍVEIS PARA APADRINHAR");
        for (int i = 0; i < matrizAnimais.length; i++) {
            System.out.println(matrizAnimais[i][0] + " - " + matrizAnimais[i][1] +
                    " (" + matrizAnimais[i][2] + ")");
        }

        //Pedir ID do animal
        System.out.print("\nEscolha o ID do animal: ");
        String idEscolhido = input.nextLine();

        //Confirmar se o animal existe
        boolean encontrado = false;
        String nomeAnimal = "";
        String especieAnimal = "";
        String habitat = "";

        for (int i = 0; i < matrizAnimais.length; i++) {
            if (matrizAnimais[i][0].equals(idEscolhido)) {
                encontrado = true;
                nomeAnimal = matrizAnimais[i][1];
                especieAnimal = matrizAnimais[i][2];
                habitat = matrizAnimais[i][3];
                break;
            }
        }

        if (!encontrado) {
            System.out.println("\nO ID do animal introduzido não existe!");
            return;
        }

        // Pedir o valor mensal que pretende pagar
        System.out.print("Valor mensal que pretende pagar (€): ");
        double valorMensal = input.nextDouble();

        //Determinar o tipo de apadrinhamento
        String tipoApadrinhamento = "";
        if (valorMensal <= 25.00) {
            tipoApadrinhamento = "Apadrinhamento Simples";
        } else if (valorMensal <= 50.00) {
            tipoApadrinhamento = "Apadrinhamento Gold";
        } else {
            tipoApadrinhamento = "Apadrinhamento Diamond";
        }

        //Mostrar resumo formatado
        System.out.println("\n=== RESUMO DO APADRINHAMENTO ===");
        System.out.println("Padrinho : " + nomeCliente + " (" + emailCliente + ")");
        System.out.println("Animal : " + nomeAnimal + " (" + especieAnimal + ") - " + habitat);
        System.out.println("Plano : " + tipoApadrinhamento);
        System.out.printf("Valor : " + valorMensal + " €");
        System.out.println("\n");
    }

    public static void jogoAdivinha(String[][] matrizAnimais) {

        Scanner input = new Scanner(System.in);
        Random rd = new Random();

        System.out.println("\n=== MINI-JOGO: ADIVINHA A ESPÉCIE ===");

        // Escolher aleatoriamente um animal
        int totalAnimais = matrizAnimais.length;
        int indiceAleatorio = rd.nextInt(totalAnimais); // entre 0 e totalAnimais-1

        // Obter dados do animal
        String nome = matrizAnimais[indiceAleatorio][1];
        String especie = matrizAnimais[indiceAleatorio][2];
        String habitat = matrizAnimais[indiceAleatorio][3];
        String dieta = matrizAnimais[indiceAleatorio][4];
        String perigo = matrizAnimais[indiceAleatorio][5];

        //Mostrar pistas
        System.out.println("\nForam geradas 3 pistas sobre um animal misterioso! Tens 3 tentativas para acertar");
        System.out.println("PISTA 1: Vive em " + habitat);
        System.out.println("PISTA 2: É " + dieta);
        if (perigo.equals("Sim")) {
            System.out.println("PISTA 3: Está em perigo de extinção!");
        } else {
            System.out.println("PISTA 3: Não está em perigo de extinção.");
        }

        //APENAS 3 TENTATIVAS

        int tentativasMax = 3;
        boolean acertou = false;

        for (int tent = 1; tent <= tentativasMax; tent++) {

            System.out.print("\nTentativa " + tent + ": Qual é a espécie? ");
            String resposta = input.nextLine();

            if (resposta.equals(especie)) {
                acertou = true;
                System.out.println("✔ Correto! A espécie é mesmo " + especie + "!");
                System.out.println("Tentativas usadas: " + tent);
                break; // sai do ciclo
            } else {
                // Só dar dica se ainda houver tentativas
                if (tent < tentativasMax) {
                    System.out.println("✖ Errado! Tenta novamente...");
                }
            }
        }

        // Caso não tenha acertado
        if (!acertou) {
            System.out.println("\nFicaste sem tentativas!");
            System.out.println("A resposta correta era: " + especie);
        }

        System.out.println("\n(O nome do animal do nosso Zoo dessa espécie é: " + nome + ")");
        System.out.println("\nObrigada por jogar!\n");
    }
}
