/*
Você foi contratado para automatizar um treino de Fórmula 1. As regras estabelecidas pela direção da provas são simples: “No máximo 5 carros das 7 escuderias[equipe] (Cada escuderia tem 2 carros diferentes, portanto, 14 carros no total) presentes podem entrar na pista simultaneamente, mas apenas um carro de cada equipe. O segundo carro deve ficar à espera, caso um companheiro de equipe já esteja na pista. Cada piloto deve dar 3 voltas na pista. O tempo de cada volta deverá ser exibido e a volta mais rápida de cada piloto deve ser armazenada para, ao final, exibir o grid de largada, ordenado do menor tempo para o maior.”
*/

package view;

import java.util.concurrent.Semaphore;
import controller.CarroThread;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Semaphore semaforoPista = new Semaphore(5);
        Semaphore[] semaforoEscudeira = new Semaphore[7];
        CarroThread[] carroEquipe = new CarroThread[14];
        double[] vetorTempo = new double[14];
        int[] vetorId = new int[14];

        // Inicializa os semáforos para as escuderias
        for (int i = 0; i < 7; i++) {
            semaforoEscudeira[i] = new Semaphore(1);  // Só um carro por escuderia na pista
        }

        // Inicializa os carros e as threads
        int numCarros = 0;
        System.out.println("A CORRIDA COMEÇOU!");
        for (int numEscudeira = 0; numEscudeira < 7; numEscudeira++) { // Loop das escuderias
            for (int carro = 0; carro < 2; carro++) {  // Cada escuderia tem 2 carros
                carroEquipe[numCarros] = new CarroThread(numCarros + 1, numEscudeira + 1, semaforoPista, semaforoEscudeira[numEscudeira]);
                carroEquipe[numCarros].start();
                numCarros++;  // Incrementa o contador de carros
            }
        }

        // Aguarda todas as threads terminarem
        for (CarroThread carro : carroEquipe) {
            carro.join();
        }

        // Coleta os tempos e IDs dos carros
        for (int i = 0; i < 14; i++) {
            vetorTempo[i] = carroEquipe[i].getMelhorTempo();
            vetorId[i] = carroEquipe[i].getCarroId();
        }

        // Ordena os carros pelo tempo de volta mais rápido (Bubble Sort)
        double aux;
        for (int i = 0; i < 14; i++) {
            for (int j = i + 1; j < 14; j++) {
                if (vetorTempo[j] < vetorTempo[i]) {  // Ordena do menor tempo para o maior
                    aux = vetorTempo[i];
                    vetorTempo[i] = vetorTempo[j];
                    vetorTempo[j] = aux;
                    aux = vetorId[i];
                    vetorId[i] = vetorId[j];
                    vetorId[j] = (int) aux;
                }
            }
        }

        // Exibe o grid de largada
        System.out.println("\nCOLOCAÇÃO:");
        for (int i = 0; i < 14; i++) {
            System.out.println((i + 1) + "º - Carro #" + vetorId[i] + " (" + String.format("%.3f", vetorTempo[i]) + "s)");
        }
    }
}
