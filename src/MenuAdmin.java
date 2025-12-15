import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.function.ToDoubleBiFunction;

public class MenuAdmin {

    public static void menuAdmin(String[][] matrizAnimais, String[][] matrizClientes, String[][] matrizInteracoes) throws FileNotFoundException {

        Scanner input = new Scanner(System.in);

        //Declarar variáveis
        int opcao;

        do {

            System.out.println("===== MENU ADMIN CODE SAVANNA =====");
            System.out.println("1. Listar conteúdo dos ficheiros");
            System.out.println("2. Estatísticas gerais de interações");
            System.out.println("3. Receita total por tipo de interação");
            System.out.println("4. Animal mais popular");
            System.out.println("5. Top 3 espécies com mais apadrinhamentos");
            System.out.println("6. Listar padrinhos de um animal");
            System.out.println("7. Espetáculo mais rentável");
            System.out.println("8. Ranking de animais em perigo de extinção");
            System.out.println("9. Estatísticas por habitat");
            System.out.println("0. Sair");

            System.out.print("Opção: ");
            opcao = input.nextInt();

            switch (opcao) {

                case 1: // Listar conteúdo dos ficheiros
                    listarConteudo();
                    break;

                case 2: // Estatísticas gerais de interações
                    System.out.println("\n");
                    estatisticasGerais(matrizInteracoes);
                    System.out.println("\n");
                    break;

                case 3: //  Receita total por tipo de interação
                    System.out.println("\n");
                    receitaTotal(matrizInteracoes);
                    System.out.println("\n");
                    break;

                case 4: //Animal mais popular
                    System.out.println("\n");
                    animalMaisPopular(matrizAnimais, matrizInteracoes);
                    System.out.println("\n");
                    break;

                case 5: //Top 3 espécies com mais apadrinhamentos
                    System.out.println("\n");
                    top3(matrizAnimais, matrizInteracoes);
                    System.out.println("\n");
                    break;

                case 6: //Listar padrinhos de um animal
                    System.out.println("\n");
                    listaDePadrinhos(matrizAnimais, matrizClientes, matrizInteracoes);
                    System.out.println("\n");
                    break;

                case 7: //Espetáculo mais rentável
                    System.out.println("\n");
                    espetaculoMaisRentável(matrizInteracoes, matrizAnimais);
                    System.out.println("\n");
                    break;

                case 8: //Ranking de animais em perigo de extinção
                    System.out.println("\n");
                    animaisEmExtincao(matrizAnimais, matrizInteracoes);
                    System.out.println("\n");
                    break;

                case 9: //Estatísticas por habitat
                    System.out.println("\n");
                    estatisticasPorHabitat(matrizAnimais, matrizInteracoes);
                    System.out.println("\n");
                    break;

                case 0: //Sair
                    System.out.println("\nSair");
                    break;

                default:
                    System.out.println("\nOpção Inválida");
                    break;
            }

        } while (opcao != 0);
    }

    public static void listarConteudo() throws FileNotFoundException {
        Scanner input = new Scanner(System.in);

        //Declarar variáveis
        int opcao;

        do {

            System.out.println("\n\n ===== LISTAR CONTEÚDO DOS FICHEIROS =====");
            System.out.println("1. Animais");
            System.out.println("2. Clientes");
            System.out.println("3. Interações");
            System.out.println("0. Voltar");

            System.out.print("Opção: ");
            opcao = input.nextInt();

            switch (opcao) {

                case 1: // Animais
                    System.out.println("\n************** Animais **************");
                    System.out.println();
                    imprimirFicheiroAnimais();
                    break;

                case 2: // Clientes
                    System.out.println("\n************** Clientes **************");
                    System.out.println();
                    imprimirFicheiroClientes();
                    break;

                case 3: // Interações
                    System.out.println("\n************** Interações **************");
                    System.out.println();
                    imprimirFicheiroInteracoes();
                    break;

                case 0: // Voltar
                    break;

                default:
                    System.out.println("\nOpção Inválida");
                    break;
            }

        } while (opcao != 0);
    }

    public static void imprimirFicheiroAnimais() throws FileNotFoundException {/// imprimir o conteúdo completo do ficheiro animais.csv na consola
        Biblioteca.imprimirFicheiro("TrabalhoIndividual/animais.csv");
    }

    public static void imprimirFicheiroClientes() throws FileNotFoundException {/// imprimir o conteúdo completo do ficheiro clientes.csv na consola
        Biblioteca.imprimirFicheiro("TrabalhoIndividual/clientes.csv");
    }

    public static void imprimirFicheiroInteracoes() throws FileNotFoundException {/// imprimir o conteúdo completo do ficheiro interacoes.csv na consola
        Biblioteca.imprimirFicheiro("TrabalhoIndividual/interacoes.csv");
    }


    public static void estatisticasGerais(String[][] matrizInteracoes) {//contagem de todas as interações e por interação

        //Declarar variáveis
        int contadorVisitas = 0;
        int contadorEspetaculos = 0;
        int contadorAlimentacao = 0;
        int contadorApadrinhamento = 0;
        int contadorTotal = matrizInteracoes.length;

        for (int linha = 0; linha < matrizInteracoes.length; linha++) {

            if (matrizInteracoes[linha][2].equals("VISITA")) {
                contadorVisitas++;
            } else if (matrizInteracoes[linha][2].equals("ESPETACULO")) {
                contadorEspetaculos++;
            } else if (matrizInteracoes[linha][2].equals("ALIMENTACAO")) {
                contadorAlimentacao++;
            } else if (matrizInteracoes[linha][2].equals("APADRINHAMENTO")) {
                contadorApadrinhamento++;
            }
        }
        System.out.println("===== ESTATÍSTICAS GERAIS=====");
        System.out.println("Total de interações: " + contadorTotal);
        System.out.println("VISITA: " + contadorVisitas);
        System.out.println("ESPETACULO: " + contadorEspetaculos);
        System.out.println("ALIMENTACAO: " + contadorAlimentacao);
        System.out.println("APADRINHAMENTO: " + contadorApadrinhamento);
        System.out.println("==================================");
    }

    public static void receitaTotal(String[][] matrizInteracoes) {

        //Declarar variáveis
        double somaVisita = 0;
        double somaEspetaculo = 0;
        double somaAlimentacao = 0;
        double somaApadrinhamento = 0;
        double somaTotal = 0;

        for (int linha = 0; linha < matrizInteracoes.length; linha++) {

            if (matrizInteracoes[linha][2].equals("VISITA")) {
                somaVisita += Double.parseDouble(matrizInteracoes[linha][5]);
            } else if (matrizInteracoes[linha][2].equals("ESPETACULO")) {
                somaEspetaculo += Double.parseDouble(matrizInteracoes[linha][5]);
            } else if (matrizInteracoes[linha][2].equals("ALIMENTACAO")) {
                somaAlimentacao += Double.parseDouble(matrizInteracoes[linha][5]);
            } else if (matrizInteracoes[linha][2].equals("APADRINHAMENTO")) {
                somaApadrinhamento += Double.parseDouble(matrizInteracoes[linha][5]);
            }
            somaTotal += Double.parseDouble(matrizInteracoes[linha][5]);
        }
        System.out.println("===== RECEITA TOTAL POR INTERAÇÃO=====");
        System.out.println("VISITA: " + somaVisita + " €");
        System.out.println("ESPETACULO: " + somaEspetaculo + " €");
        System.out.println("ALIMENTACAO: " + somaAlimentacao + " €");
        System.out.println("APADRINHAMENTO: " + somaApadrinhamento + " €");
        System.out.println("RECEITA TOTAL: " + somaTotal + " €");
        System.out.println("==================================");
    }

    public static void animalMaisPopular(String[][] matrizAnimais, String[][] matrizInteracoes) {

        //Criar arrays para guardar IDs dos animais e respetivo número de interações
        String[] idsAnimais = new String[matrizAnimais.length];
        int[] contagemInteracoes = new int[matrizAnimais.length];

        //Inicializar arrays com IDs dos animais
        for (int i = 0; i < matrizAnimais.length; i++) {
            idsAnimais[i] = matrizAnimais[i][0]; // coluna 0 = idAnimal
            contagemInteracoes[i] = 0;            // começa em 0
        }

        //Contar o número de interações de cada animal
        for (int i = 0; i < matrizInteracoes.length; i++) {
            String idInteracao = matrizInteracoes[i][3]; // coluna 3 = idAnimal

            // Procurar o índice correspondente ao animal
            for (int j = 0; j < idsAnimais.length; j++) {
                if (idInteracao.equals(idsAnimais[j])) {
                    contagemInteracoes[j]++;
                    break;
                }
            }
        }

        //Determinar o animal com mais interações
        int indiceMax = 0;
        int maxInteracoes = contagemInteracoes[0];

        for (int i = 1; i < contagemInteracoes.length; i++) {
            if (contagemInteracoes[i] > maxInteracoes) {
                maxInteracoes = contagemInteracoes[i];
                indiceMax = i;
            }
        }

        //Obter informações do animal com mais interações
        String idMaisInteracoes = idsAnimais[indiceMax];
        String nome = "";
        String especie = "";
        String habitat = "";

        for (int i = 0; i < matrizAnimais.length; i++) {
            if (matrizAnimais[i][0].equals(idMaisInteracoes)) {
                nome = matrizAnimais[i][1];
                especie = matrizAnimais[i][2];
                habitat = matrizAnimais[i][3];
                break;
            }
        }

        //Imprimir resultado
        System.out.println("\n=== ANIMAL MAIS POPULAR ===");
        System.out.println("Nome: " + nome);
        System.out.println("Espécie: " + especie);
        System.out.println("Habitat: " + habitat);
        System.out.println("Número total de interações: " + maxInteracoes);
        System.out.println("==================================");
    }

    public static void top3(String[][] matrizAnimais, String[][] matrizInteracoes) {

        //Criar arrays auxiliares para guardar espécies únicas
        String[] especies = new String[matrizAnimais.length];
        int numEspecies = 0;

        // Criar lista de espécies únicas
        for (int i = 0; i < matrizAnimais.length; i++) {
            String especieAtual = matrizAnimais[i][2];
            boolean existe = false;

            for (int j = 0; j < numEspecies; j++) {
                if (especies[j].equals(especieAtual)) {
                    existe = true;
                    break;
                }
            }

            if (!existe) {
                especies[numEspecies] = especieAtual;
                numEspecies++;
            }
        }

        //Arrays para contagem de apadrinhamentos e valor total
        int[] contagem = new int[numEspecies];
        double[] totalValor = new double[numEspecies];

        // Inicializar
        for (int i = 0; i < numEspecies; i++) {
            contagem[i] = 0;
            totalValor[i] = 0;
        }

        //Para cada interação APADRINHAMENTO, somar para a espécie correspondente
        for (int i = 0; i < matrizInteracoes.length; i++) {
            if (matrizInteracoes[i][2].equals("APADRINHAMENTO")) {

                String idAnimal = matrizInteracoes[i][3];
                double valorPago = Double.parseDouble(matrizInteracoes[i][5]);

                // Descobrir a espécie desse animal
                String especieDoAnimal = "";
                for (int a = 0; a < matrizAnimais.length; a++) {
                    if (matrizAnimais[a][0].equals(idAnimal)) {
                        especieDoAnimal = matrizAnimais[a][2];
                        break;
                    }
                }

                // Somar na espécie correta
                for (int e = 0; e < numEspecies; e++) {
                    if (especies[e].equals(especieDoAnimal)) {
                        contagem[e]++;
                        totalValor[e] += valorPago;
                        break;
                    }
                }
            }
        }

        //Ordenar por valor total (ou contagem) decrescente — Bubble Sort
        for (int i = 0; i < numEspecies - 1; i++) {
            for (int j = 0; j < numEspecies - i - 1; j++) {
                if (contagem[j] < contagem[j + 1]) {

                    // Trocar contagem
                    int tempC = contagem[j];
                    contagem[j] = contagem[j + 1];
                    contagem[j + 1] = tempC;

                    // Trocar valor total
                    double tempV = totalValor[j];
                    totalValor[j] = totalValor[j + 1];
                    totalValor[j + 1] = tempV;

                    // Trocar espécie
                    String tempS = especies[j];
                    especies[j] = especies[j + 1];
                    especies[j + 1] = tempS;
                }
            }
        }

        //Imprimir apenas Top 3
        System.out.println("\n=== TOP 3 ESPÉCIES MAIS APADRINHADAS ===\n");

        int limite = Math.min(3, numEspecies);

        for (int i = 0; i < limite; i++) {
            System.out.println((i + 1) + ") " + especies[i]);
            System.out.println("Nº de apadrinhamentos: " + contagem[i]);
            System.out.println("Valor mensal total : "+ totalValor[i]+ " €");
            System.out.println("\n");
        }
    }

    public static void listaDePadrinhos(String[][] matrizAnimais, String[][] matrizClientes, String[][] matrizInteracoes) {

        String[] dadosAnimal = Biblioteca.pedirEConfirmarAnimal(matrizAnimais);

        if (dadosAnimal == null) {
            return;
        }

        String idAnimal = dadosAnimal[0];
        String nomeAnimal = dadosAnimal[1];
        String especieAnimal = dadosAnimal[2];

        System.out.println("\n=== Padrinhos do animal " + nomeAnimal + " (" + especieAnimal + ") ===\n");

        boolean encontrouAlgum = false;

        //Procurar todos os apadrinhamentos do animal
        for (int i = 0; i < matrizInteracoes.length; i++) {

            String tipo = matrizInteracoes[i][2];
            String idAnimalInter = matrizInteracoes[i][3];

            if (tipo.equals("APADRINHAMENTO") &&
                    idAnimalInter.equals(idAnimal)) {

                encontrouAlgum = true;

                String idCliente = matrizInteracoes[i][1];
                String nomePlano = matrizInteracoes[i][4];
                double valorPago = Double.parseDouble(matrizInteracoes[i][5]);

                String nomeCliente = "";
                String emailCliente = "";

                //Obter nome e email do cliente
                for (int c = 0; c < matrizClientes.length; c++) {
                    if (matrizClientes[c][0].equals(idCliente)) {
                        nomeCliente = matrizClientes[c][1];
                        emailCliente = matrizClientes[c][2];
                        break;
                    }
                }

                //Imprimir resultado
                System.out.println("Padrinho: " + nomeCliente + " (" + emailCliente + ")");
                System.out.println("Plano   : " + nomePlano);
                System.out.println("Valor   : " + valorPago + " €/mês\n");
            }
        }

        if (!encontrouAlgum) {
            System.out.println("Nenhum apadrinhamento encontrado para este animal.");
        }
    }

    public static void espetaculoMaisRentável(String[][] matrizInteracoes, String[][] matrizAnimais) {

        //Declarar variáveis para a soma de valorPago
        double somaMarchaPinguins = 0;
        double somaGorilas = 0;
        double somaAraras = 0;
        double somaGolfinhos = 0;
        double somaTotalRentavel = 0;

        // Guardar idAnimal de cada espetáculo (para identificar o animal principal na matriz Animais)
        String animalMarchaPinguins = "";
        String animalGorilas = "";
        String animalAraras = "";
        String animalGolfinhos = "";

        // Percorrer matriz de interações apenas para eventos qualificados como espetaculo
        for (int linha = 0; linha < matrizInteracoes.length; linha++) {
            if (matrizInteracoes[linha][2].equals("ESPETACULO")) {
                String nomeEvento = matrizInteracoes[linha][4];

                // Acumular valores por evento
                if (nomeEvento.equals("Marcha dos Pinguins Bit")) {
                    somaMarchaPinguins += Double.parseDouble(matrizInteracoes[linha][5]);
                    animalMarchaPinguins = matrizInteracoes[linha][3];
                } else if (nomeEvento.equals("Espetaculo dos Gorilas Zé")) {
                    somaGorilas += Double.parseDouble(matrizInteracoes[linha][5]);
                    animalGorilas = matrizInteracoes[linha][3];
                } else if (nomeEvento.equals("Araras Ruby em Concerto")) {
                    somaAraras += Double.parseDouble(matrizInteracoes[linha][5]);
                    animalAraras = matrizInteracoes[linha][3];
                } else if (nomeEvento.equals("Show dos Golfinhos Cloud")) {
                    somaGolfinhos += Double.parseDouble(matrizInteracoes[linha][5]);
                    animalGolfinhos = matrizInteracoes[linha][3];
                }

                somaTotalRentavel += Double.parseDouble(matrizInteracoes[linha][5]);
            }
        }

        //Identificar nome e espécie de cada animal usando matrizAnimais
        String nomeMarchaPinguins = "", especieMarchaPinguins = "";
        String nomeGorilas = "", especieGorilas = "";
        String nomeAraras = "", especieAraras = "";
        String nomeGolfinhos = "", especieGolfinhos = "";

        for (int i = 0; i < matrizAnimais.length; i++) {
            String id = matrizAnimais[i][0];
            if (id.equals(animalMarchaPinguins)) {
                nomeMarchaPinguins = matrizAnimais[i][1];
                especieMarchaPinguins = matrizAnimais[i][2];
            } else if (id.equals(animalGorilas)) {
                nomeGorilas = matrizAnimais[i][1];
                especieGorilas = matrizAnimais[i][2];
            } else if (id.equals(animalAraras)) {
                nomeAraras = matrizAnimais[i][1];
                especieAraras = matrizAnimais[i][2];
            } else if (id.equals(animalGolfinhos)) {
                nomeGolfinhos = matrizAnimais[i][1];
                especieGolfinhos = matrizAnimais[i][2];
            }
        }

        System.out.println("===== RECEITA POR ESPETÁCULO =====");
        System.out.println("Marcha dos Pinguins Bit : " + somaMarchaPinguins + " €");
        System.out.println("  → Animal principal: " + nomeMarchaPinguins + " (" + especieMarchaPinguins + ")\n");
        System.out.println("Espetáculo dos Gorilas Zé : " + somaGorilas + " €");
        System.out.println("  → Animal principal: " + nomeGorilas + " (" + especieGorilas + ")\n");
        System.out.println("Araras Ruby em Concerto : " + somaAraras + " €");
        System.out.println("  → Animal principal: " + nomeAraras + " (" + especieAraras + ")\n");
        System.out.println("Show dos Golfinhos Cloud : " + somaGolfinhos + " €");
        System.out.println("  → Animal principal: " + nomeGolfinhos + " (" + especieGolfinhos + ")\n");
        System.out.println("----------------------------------");
        System.out.println("Receita Total (ESPETÁCULOS): " + somaTotalRentavel + " €");
        System.out.println("==================================");
    }

    public static void animaisEmExtincao(String[][] matrizAnimais, String[][] matrizInteracoes) {


        //Declarar variáveis
        int numEmPerigo = 0;


        // Contar quantos animais estão em perigo na matrizAnimais
        for (int i = 0; i < matrizAnimais.length; i++) {
            if (matrizAnimais[i][5].equals("SIM")) {
                numEmPerigo++;
            }
        }

        //Criar arrays para guardar os dados dos animais em perigo
        String[] nomes = new String[numEmPerigo];
        String[] especies = new String[numEmPerigo];
        String[] ids = new String[numEmPerigo];
        int[] numInteracoes = new int[numEmPerigo];
        double[] receitaTotal = new double[numEmPerigo];

        int indice = 0;

        //Preencher dados dos animais em perigo
        for (int i = 0; i < matrizAnimais.length; i++) {
            if (matrizAnimais[i][5].equals("SIM")) {
                ids[indice] = matrizAnimais[i][0];       // idAnimal
                nomes[indice] = matrizAnimais[i][1];     // nome
                especies[indice] = matrizAnimais[i][2];  // espécie
                indice++;
            }
        }

        //Contar interações e somar receitas
        for (int i = 0; i < numEmPerigo; i++) {
            String idAtual = ids[i];
            int contador = 0;
            double soma = 0;

            for (int j = 0; j < matrizInteracoes.length; j++) {
                if (matrizInteracoes[j][3].equals(idAtual)) {
                    contador++;
                    soma += Double.parseDouble(matrizInteracoes[j][5]);
                }
            }

            numInteracoes[i] = contador;
            receitaTotal[i] = soma;
        }

        //Ordenar por receita total (decrescente) usando bubble sort simples
        for (int i = 0; i < numEmPerigo - 1; i++) {
            for (int j = 0; j < numEmPerigo - i - 1; j++) {
                if (receitaTotal[j] < receitaTotal[j + 1]) {

                    // Trocar valores de receita
                    double tempR = receitaTotal[j];
                    receitaTotal[j] = receitaTotal[j + 1];
                    receitaTotal[j + 1] = tempR;

                    // Trocar número de interações
                    int tempI = numInteracoes[j];
                    numInteracoes[j] = numInteracoes[j + 1];
                    numInteracoes[j + 1] = tempI;

                    // Trocar nome, espécie e id
                    String tempN = nomes[j];
                    nomes[j] = nomes[j + 1];
                    nomes[j + 1] = tempN;

                    String tempE = especies[j];
                    especies[j] = especies[j + 1];
                    especies[j + 1] = tempE;

                    String tempId = ids[j];
                    ids[j] = ids[j + 1];
                    ids[j + 1] = tempId;
                }
            }
        }

        //Imprimir relatório
        System.out.println("\n=== ANIMAIS EM PERIGO DE EXTINÇÃO ===\n");
        for (int i = 0; i < numEmPerigo; i++) {
            System.out.println("Animal: " + nomes[i] + " (" + especies[i] + ")");
            System.out.println("ID: " + ids[i]);
            System.out.println("Nº de interações: " + numInteracoes[i]);
            System.out.println("Receita total: "+ receitaTotal[i] +" €");
            System.out.println("----------------------------------------");
        }

    }

    public static void estatisticasPorHabitat(String[][] matrizAnimais, String[][] matrizInteracoes) {

        // Criar array para guardar habitats únicos
        String[] habitatsUnicos = new String[matrizAnimais.length];

        //Declarar vairáveis
        int contadorHabitats = 0;

        // Encontrar habitats únicos com boolean
        for (int i = 0; i < matrizAnimais.length; i++) {
            String habitatAtual = matrizAnimais[i][3]; // coluna 3 = habitat
            boolean jaExiste = false;

            for (int j = 0; j < contadorHabitats; j++) {
                if (habitatAtual.equals(habitatsUnicos[j])) {
                    jaExiste = true;
                    break;
                }
            }

            if (!jaExiste) {
                habitatsUnicos[contadorHabitats] = habitatAtual;
                contadorHabitats++;
            }
        }
        System.out.println("\n=== ESTATÍSTICAS POR HABITAT ===\n");

        //Para cada habitat, calcular estatísticas
        for (int h = 0; h < contadorHabitats; h++) {
            String habitat = habitatsUnicos[h];

            int numAnimais = 0;
            int numInteracoes = 0;
            double receitaTotal = 0.0;

            //Contar animais no habitat
            String[] idsAnimaisHabitat = new String[matrizAnimais.length];
            int contadorAnimaisHabitat = 0;

            for (int i = 0; i < matrizAnimais.length; i++) {
                if (matrizAnimais[i][3].equals(habitat)) {
                    numAnimais++;
                    idsAnimaisHabitat[contadorAnimaisHabitat] = matrizAnimais[i][0]; // ID do animal
                    contadorAnimaisHabitat++;
                }
            }

            //Contar interações e somar receita
            for (int i = 0; i < matrizInteracoes.length; i++) {
                String idAnimalInteracao = matrizInteracoes[i][3]; // coluna 3 = idAnimal
                String valorPagoStr = matrizInteracoes[i][5];       // coluna 5 = valorPago

                for (int j = 0; j < contadorAnimaisHabitat; j++) {
                    if (idAnimalInteracao.equals(idsAnimaisHabitat[j])) {
                        numInteracoes++;
                        receitaTotal += Double.parseDouble(valorPagoStr);
                        break;
                    }
                }
            }

            //Imprimir resultados formatados
            System.out.println("Habitat: " + habitat);
            System.out.println("Nº de animais : " + numAnimais);
            System.out.println("Nº de interações : " + numInteracoes);
            System.out.println("Receita associada : "+ receitaTotal+ " €");
            System.out.println("----------------------------------------");
            System.out.println();
        }
    }

}


