public class KMPPatternMatching {

    static long iteracoes = 0;
    static long instrucoes = 0;
    static long tempo = 0;

    public static int KMP(String s1, String s2) {

        iteracoes = 0;
        instrucoes = 0;

        long inicio = System.nanoTime();

        int tamanhoS1 = s1.length();
        int tamanhoS2 = s2.length();

        instrucoes += 2;

        int[] lps = new int[tamanhoS2];

        instrucoes++;

        int pos = 0;
        int i = 1;

        instrucoes += 2;

        while (i < tamanhoS2) {

            iteracoes++;

            if (s2.charAt(i) == s2.charAt(pos)) {

                pos++;
                lps[i] = pos;
                i++;

                instrucoes += 3;
            }
            else {

                if (pos != 0) {

                    pos = lps[pos - 1];

                    instrucoes++;
                }
                else {

                    lps[i] = 0;
                    i++;

                    instrucoes += 2;
                }
            }
        }

        i = 0;
        int j = 0;
        instrucoes += 2;

        while (i < tamanhoS1) {

            iteracoes++;

            if (s2.charAt(j) == s1.charAt(i)) {
                i++;
                j++;
                instrucoes += 2;
            }

            if (j == tamanhoS2) {
                tempo = System.nanoTime() - inicio;
                return i - j;
            }
            else if (i < tamanhoS1 && s2.charAt(j) != s1.charAt(i)) {
                if (j != 0) {
                    j = lps[j - 1];
                    instrucoes++;
                }
                else {
                    i++;
                    instrucoes++;
                }
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
            System.out.println("Sem ocorrencia");
        else
            System.out.println("Posicao: " + posicao);

        System.out.println("Iteracoes: " + iteracoes);
        System.out.println("Instrucoes: " + instrucoes);
        System.out.println("Tempo: " + tempo + "ns\n");
    }

    public static void main(String[] args) {

        String s1 = "ABCDCBDCBDACBDABDCBADF";
        String s2 = "ADF";

        resultado(1, s1, s2, KMP(s1, s2));

        s1 = "ADFACDBDACBDABDCBADFABCDCBDBDCB";
        s2 = "ADFAB";
        resultado(2, s1, s2, KMP(s1, s2));

        s1 = "AACAACAAAB";
        s2 = "ACB";
        resultado(3, s1, s2, KMP(s1, s2));

        s1 = "ADF";
        s2 = "BBBBBBBBBBBBBBBB";
        resultado(4, s1, s2, KMP(s1, s2));

        char ABC[] = {'A', 'B', 'C'};
        StringBuilder bs3 = new StringBuilder();
        StringBuilder bs4 = new StringBuilder();

        for (int i = 0; i < 50000; i++) {
            if (i < 50000) {
                int pos = (int) (Math.random() * 3);
                bs3.append(ABC[pos]);
                instrucoes += 2;
            }

            if (i < 10) {
                int pos = (int) (Math.random() * 3);
                bs4.append(ABC[pos]);
                instrucoes += 2;
            }
        }
        String s3 = bs3.toString();
        String s4 = bs4.toString();

        resultado(5, s3, s4, KMP(s3, s4));
    }
}
