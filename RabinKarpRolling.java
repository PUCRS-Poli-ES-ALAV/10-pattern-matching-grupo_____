public class RabinKarpRolling {

    static long iteracoes = 0;
    static long instrucoes = 0;
    static long tempo = 0;

    static int R = 26;
    static int Q = 2147483647;

    public static long hash(String s, int tamanho) {

        long hash = 0;

        for (int i = 0; i < tamanho; i++) {
            iteracoes++;
            hash = (hash * R + s.charAt(i)) % Q;
            instrucoes ++;
        }
        return hash;
    }

    public static int RKR(String s1, String s2) {

        iteracoes = 0;
        instrucoes = 0;

        long inicio = System.nanoTime();

        int tamanhoS1 = s1.length();
        int tamanhoS2 = s2.length();

        long RM = 1;
        
        instrucoes += 3;

        for (int i = 1; i <= tamanhoS2 - 1; i++) {
            RM = (RM * R) % Q;
            instrucoes ++;
        }

        long hash2 = hash(s2, tamanhoS2);
        long hash1 = hash(s1, tamanhoS2);

        instrucoes += 2;

        for (int i = 0; i <= tamanhoS1 - tamanhoS2; i++) {

            iteracoes++;

            if (hash2 == hash1) {
                boolean igual = true;
                instrucoes++;
                for (int j = 0; j < tamanhoS2; j++) {
                    iteracoes++;
                    if (s1.charAt(i + j) != s2.charAt(j)) {
                        igual = false;
                        instrucoes++;
                        break;
                    }
                }

                if (igual) {
                    tempo = System.nanoTime() - inicio;
                    return i;
                }
            }

            if (i < tamanhoS1 - tamanhoS2) {
                hash1 = (hash1 + Q - RM * s1.charAt(i) % Q) % Q;
                hash1 = (hash1 * R + s1.charAt(i + tamanhoS2)) % Q;
                instrucoes += 2;
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

        resultado(1, s1, s2, RKR(s1, s2));

        s1 = "ADFACDBDACBDABDCBADFABCDCBDBDCB";
        s2 = "ADFAB";
        resultado(2, s1, s2, RKR(s1, s2));

        s1 = "AACAACAAAB";
        s2 = "ACB";
        resultado(3, s1, s2, RKR(s1, s2));

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

        resultado(5, s3, s4, RKR(s3, s4));
    }
}
