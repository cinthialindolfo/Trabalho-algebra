public class Laplace {

    public static double laplace(double[][] matriz) {
        //parte-se do pressuposto que a matriz seja válida
        double determinante = 0;//valor do determinante que vai ser retornado
        //calcula o determinante de matriz de ordem um
        if (matriz.length == 1) {
            determinante = matriz[0][0];
            //calcula o determinante de matriz de ordem dois
        } else if (matriz.length == 2) {
            determinante = matriz[0][0] * matriz[1][1] - matriz[0][1] * matriz[1][0];
            //calcula o determinante de matriz de ordem três usando regra de Sarrus
        } else if (matriz.length == 3) {
            determinante = matriz[0][0] * matriz[1][1] * matriz[2][2]
                    + matriz[0][1] * matriz[1][2] * matriz[2][0]
                    + matriz[0][2] * matriz[1][0] * matriz[2][1]
                    - matriz[0][2] * matriz[1][1] * matriz[2][0]
                    - matriz[0][0] * matriz[1][2] * matriz[2][1]
                    - matriz[0][1] * matriz[1][0] * matriz[2][2];
        } else {
            /* Os termos elemento-pivô, pivô, linha-pivô são usados para fazer
             * referência a linha/coluna (nesse caso, linha 0) que foi usada
             * de base para o cálculo do determinante e também para se referir aos
             * elementos da linha/coluna em questão
             *
             * O termo matriz principalé usado para fazer referência a matriz que
             * vamos calcular o determinante
             *
             * O termo matriz auxilixar é usado para fazer referência as matrizes
             * de ordem reduzida que surgem, em consequência do teorema, para calcular
             * o determinante da matriz principal
             */

            /*
             * Essa matriz auxiliar foi criada porque, para calcular o determinante
             * de uma matriz de ordem 'n', precisaremos calcular 'n' determinantes
             * de matrizes de ordem n-1.
             * Ou seja, essa matriz auxiliar será, a cada ciclo, uma dessas matrizes
             * de ordem n-1
             *
             * Serão 'n' ciclos. A cada ciclo, preencheremos essa matriz auxiliar
             * com os valores da matriz principal, exceto os elementos que pertencem à
             * mesma linha ou à mesma coluna.
             *
             * Para fazer o preenchimento da matriz auxiliar precisaremos percorrer
             * a matriz principal, ou seja, teremos um ciclo duplo. Nesse ciclo iremos
             * ignorar todos os elementos que pertencem a mesma linha ou coluna do
             * elemento-pivô correpondente. Como a matriz auxiliar é de ordem reduzida
             * usaremos duas novas variáveis 'i_aux' e 'j_aux' que representarão,
             * respectivamente, o índice da linha e o índice da coluna da matriz auxiliar
             */
            double[][] aux;
            int i_aux, j_aux, linha, coluna, i;

            //esse loop irá fazer uma varredura coluna-coluna na primeira linha da matriz
            //isso, porque escolhemos a primeira linha como pivô fixo
            for (i = 0; i < matriz.length; i++) {
                /*
                 * Verifica se o elemento-pivô é diferente de zero para poder fazer
                 * todo o calculo.
                 * Caso o elemento seja zero, não há necessidade de fazer o cálculo
                 * afinal todo o produto irá zerar. Também não precisaremos fazer a soma
                 * parcial de zero, já que zero somado não influencia no resultado
                 */
                if (matriz[0][i] != 0) {
                    aux = new double[matriz.length - 1][matriz.length - 1];
                    i_aux = 0;
                    j_aux = 0;
                    /*começa da linha 1, porque a linha zero é o pivo escolhido
                      saiba que, mesmo usando como índices iniciais o valor zero,
                      o teorema funciona perfeitamente
                     */
                    for (linha = 1; linha < matriz.length; linha++) {
                        for (coluna = 0; coluna < matriz.length; coluna++) {
                            /*
                             * pelo teorema de Laplace, não podemos usar elementos
                             * da mesma linha ou coluna do pivo escolhido
                             * Lembre-se que para cada elemento pivo, iremos ignorar
                             * uma coluna diferente. Sabemos qual é essa coluna graças ao
                             * ciclo mais externo, com índice i
                             * É por isso tudo que temos esse 'if'
                             */
                            if (coluna != i) {
                                aux[i_aux][j_aux] = matriz[linha][coluna];
                                j_aux++;
                            }
                        }
                        /*
                         * Após percorrer uma linha inteira da matriz principal,
                         * faz incremento de 'i_aux' para poder ir para preencher a
                         * próxima linha.
                         *
                         * Zera 'j_aux' para poder começar a percorrer os elementos
                         * da linha seguinte
                         *
                         * Lembre-se não podemos usar os índices da matriz principal
                         * porque a ordem dela é superior a ordem da matriz auxiliar
                         */
                        i_aux++;
                        j_aux = 0;
                    }
                    /*
                     * Calcula a parcela do pivô correspondente e soma ao valor que
                     * será retornado.
                     * Observe que, o método 'laplace' é chamado (recursividade) para
                     * calcular o determinante da matriz auxiliar de ordem n-1. Isso vai
                     * ocorrer até chegar na ordem 3, que é um caso básico, onde será usada a Regra de Sarrus,
                     * ocorrerá o desempilhamento e, finalmente, teremos nosso
                     * determinante
                     *
                     * 'matriz[0][i]' é o elemento-pivô da nossa linha-pivô zero
                     *
                     * 'Math.pow(-1, i)' faz parte do teorema, "se a soma do índice da linha
                     * com o índice da coluna for par, mantemos o sinal, senão trocamos o sinal".
                     */
                    determinante += Math.pow(-1, i) * matriz[0][i] * laplace(aux);
                }

            }
        }
        return determinante;
    }
}