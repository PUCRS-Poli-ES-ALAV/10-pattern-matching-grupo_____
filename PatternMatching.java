/*

# Problemas de Pattern Matching

## O problema


Dadas duas strings, s1 e s2, verificar a posição da primeira ocorrência de se s2 em s1, se existir.

Assim, se s1 = "ABCDCBDCBDACBDABDCBADF" e s1 = "ADF" o retorno seria 19.



## Enunciado 1

1. Faça um algortimo que resolva o problema acima.
   1. teste-o para strings de diversos tamanhos, até strings grandes (ambas as strings >500.000 caracteres). Conte o número de iterações e de instruções.
   1. qual a complexidade, no pior caso?

*/

public class PatternMatching {

    static long iteracoes = 0;
    static long tempo = 0;

    public static int patternMatch(String s1, String s2) {

        iteracoes = 0;
        tempo = 0;

        long inicio = System.nanoTime();
        int tamanhoS1 = s1.length();
        int tamanhoS2 = s2.length();

        if (tamanhoS2 == 0) {
            tempo = System.nanoTime() - inicio;
            return 0;
        }

        if (tamanhoS2 > tamanhoS1) {
            tempo = System.nanoTime() - inicio;
            return -1;
        }

        for (int i = 0; i <= tamanhoS1 - tamanhoS2; i++) {

            int j = 0;

            while (j < tamanhoS2) {
                iteracoes++;
                if (s1.charAt(i + j) != s2.charAt(j))
                    break;
                j++;
            }

            if (j == tamanhoS2) {
                tempo = System.nanoTime() - inicio;
                return i;
            }
        }

        tempo = System.nanoTime() - inicio;
        return -1;
    }

    public static void resultado(int caso, String s1, String s2, int posicao) {

        System.out.println("Caso " + caso);
        System.out.println("Tamanho s1: " + s1.length());
        System.out.println("Tamanho s2: " + s2.length());
        if (posicao == -1)
            System.out.println("String sem ocorrência existente");
        else
            System.out.println("Posição: " + posicao);
        System.out.println("Iterações: " + iteracoes);
        System.out.println("Tempo: " + tempo + "ns\n");
    }

    public static void main(String[] args) {

        String s1 = "ABCDCBDCBDACBDABDCBADF";
        String s2 = "ADF";
        resultado(1, s1, s2, patternMatch(s1, s2));

        s1 = "ADFACDBDACBDABDCBADFABCDCBDBDCB";
        s2 = "ADFAB";
        resultado(2, s1, s2, patternMatch(s1, s2));

        s1 = "AACAACAAAB";
        s2 = "ACB";
        resultado(3, s1, s2, patternMatch(s1, s2));

        s1 = "ADF";
        s2 = "BBBBBBBBBBBBBBBB";
        resultado(4, s1, s2, patternMatch(s1, s2));

    }
}