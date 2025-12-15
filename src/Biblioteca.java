import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Biblioteca {
    public static String[][] ficheiroParaMatriz(String caminho) throws FileNotFoundException {

        int linhaAtual = 0;

        // Contar o número total de linhas (menos 1 para o cabeçalho)
        int totalLinhas = contarLinhasFicheiro(caminho) - 1;

        // Abrir o ficheiro para descobrir quantas colunas existem
        File ficheiro = new File(caminho);
        Scanner sc = new Scanner(ficheiro);

        // Ler e avançar a primeira linha (cabeçalho)
        String cabecalho = sc.nextLine();

        // Contar as colunas dinamicamente
        int totalColunas = cabecalho.split(";").length;

        // Criar a matriz com o tamanho correto
        String[][] matrizCompleta = new String[totalLinhas][totalColunas];

        // Ler o resto das linhas
        while (sc.hasNextLine()) {
            String linha = sc.nextLine();
            String[] linhaseparada = linha.split(";");

            // Copiar cada valor para a matriz
            for (int coluna = 0; coluna < matrizCompleta[0].length; coluna++) {
                matrizCompleta[linhaAtual][coluna] = linhaseparada[coluna];
            }

            linhaAtual++;
        }

        return matrizCompleta;
    }


    public static void imprimirFicheiro(String caminho) throws FileNotFoundException {

        File ficheiro = new File(caminho);

        Scanner sc = new Scanner(ficheiro);

        while (sc.hasNextLine()) {
            String linha = sc.nextLine();
            System.out.println(linha);
        }
    }

    public static int contarLinhasFicheiro(String caminho) throws FileNotFoundException {

        File ficheiro = new File(caminho);
        Scanner sc = new Scanner(ficheiro);

        int contadorLinhas = 0;

        while (sc.hasNextLine()) {
            contadorLinhas++;
            sc.nextLine();
        }

        return contadorLinhas;

    }

    //Função usada para procurar a existência de determinado animal na matrizAnimais
    public static String[] pedirEConfirmarAnimal(String[][] matrizAnimais) {

        Scanner input = new Scanner(System.in);

        System.out.print("Introduza o ID do animal (ex: A01): ");
        String idProcurado = input.nextLine().trim();

        boolean encontrado = false;
        String nome = "";
        String especie = "";

        // Procurar animal
        for (int i = 0; i < matrizAnimais.length; i++) {
            if (matrizAnimais[i][0].equalsIgnoreCase(idProcurado)) {
                encontrado = true;
                nome = matrizAnimais[i][1];
                especie = matrizAnimais[i][2];
                break;
            }
        }

        // Não encontrado
        if (!encontrado) {
            System.out.println("\nNenhum animal encontrado com o ID: " + idProcurado+"\n");
            return null;
        }

        // Encontrado → devolver dados
        String[] dados = new String[3];
        dados[0] = idProcurado;
        dados[1] = nome;
        dados[2] = especie;

        return dados;
    }
}





