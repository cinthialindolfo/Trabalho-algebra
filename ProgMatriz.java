import java.util.Scanner;

public class ProgMatriz {      //Dados necessários à realização do calculo de matriz inversa por escalonamento
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite a ordem da Matriz A:");
        int n = sc.nextInt();
        System.out.println();
        System.out.println("Montando a Matriz A (" + n + "x" + n +")\n");

        double elementoPivo, elementoOutro;
        double matriz [][] = new double[n][n];
        double mIdentidade[][] = new double[n][n];

        //Laço para a criação da Matriz A e da Matriz Identidade
        for(int i = 0; i<n; i++){
            for(int j =0; j<n; j++){
                int linha = i+1;          //i incrementado para a criação de linhas
                int coluna = j+1;         //j incrementado para a criacão de colunas
                System.out.println("Defina os valores de a(" + linha + "," + coluna + "):");
                matriz[i][j] = sc.nextInt();
                mIdentidade[i][j] = 0;     //Na Matriz Identidade, quando o valor de i é diferente do de j, na posição a(ij), então recebe o valor 0 (zero). Isso está expresso na condição a seguir.

                if(i==j){  //Nesta condição, se o elemento i for igual ao elemento j (de a(ij)), a posicão correspondete, na matriz Identidade, deverá receber o valor 1 (um).
                    mIdentidade[i][j] = 1;
                }
            }
        }
        System.out.println();

        //Para a exibição da Matriz A
        System.out.println ("Matriz A");
        //Laço que dispõe os elementos em linhas e colunas dentro da matriz de acordo com o tamanho dos arrays.
        for(int i=0;i< matriz.length;i++){
            for(int j=0;j< matriz.length;j++){
                System.out.print("[" + matriz[i][j] + "]"); //Matriz A
            }
            System.out.println();

        }

        System.out.println();
        System.out.println("O determinante da Matriz A " + Laplace.laplace(mIdentidade);
        System.out.println();

        double oDeterminante = Laplace.laplace(matriz);

        //Cálculo da matriz inversa por escalonamento
        if(oDeterminante != 0){
            for(int i=0; i<n; i++){
                elementoPivo = matriz[i][i];

                //Laço que converte o pivô para 1, realizando a operação da divisão pelo mesmo valor do pivô.
                for(int k = 0; k<n; k++){        //k é o elemento a(ij) que deve ter o valor convertido em 1 (um) em cada linha i da matriz, formando uma escada de valores 1 (um).
                    matriz[i][k] = matriz[i][k]/elementoPivo;  // elementos k da linha i divididos pelo valor do pivô.
                    mIdentidade[i][k] = mIdentidade[i][k]/elementoPivo;  //elementos k da coluna j divididos pelo valor do pivô.
                }

                //Laço que zera os outros elementos da coluna?
                for(int j =0; j<n; j++){
                    if(i!=j){   //Se a condição é verdadeira, então, a(ij) não está na diagonal principal, o que significa que os elementos devem ser zerados.
                        elementoOutro = matriz[j][i]; //Leva em conta a posição j (coluna) de cada linha i, identificando os elementos que devem ser zerados.

                        for(int k = 0; k<n; k++){
                            matriz[j][k] = matriz[j][k] - elementoOutro * matriz[i][k]; //cada elemento k da coluna j subtraído do resultado da multiplicação do elemento que não é o pivô pelos elementos k da linha i.
                            mIdentidade[j][k] = mIdentidade[j][k] - elementoOutro * mIdentidade[i][k]; //cada elemento k da coluna j subtraído do resultado da multiplicação do elemento que não é o pivô pelos elementos k da linha i.
                        }
                    }
                }
            }

            //Cálculo de matriz inversa por escalonamento
            for(int i=0;i< matriz.length;i++){     //Laço que projeta a matriz de acordo com o comprimento de linha i.
                for(int j=0;j< matriz.length;j++){   //Laço que projeta a matriz de acordo com o comprimento de coluna j.
                }
            }
            System.out.println ("Matriz Inversa");

            for(int i=0;i<matriz.length;i++){       //Laço que projeta a matriz de acordo com o comprimento de linha i.
                for(int j=0;j<matriz.length;j++){      //Laço que projeta a matriz de acordo com o comprimento de coluna j.
                    System.out.print ("["+ mIdentidade[i][j] +"]");    //Exibição da Matriz Inversa da Matriz A.
                }
                System.out.println();
            }
        }else{
            System.out.println("Não é possível obter matriz inversa quando o determinante da matriz A é zero.");
        }
        sc.close();
    }
}