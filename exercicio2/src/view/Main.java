/*
Quatro pessoas caminham, cada uma em um corredor diferente. Os 4 corredores terminam em uma única porta. Apenas 1 pessoa pode cruzar a porta, por vez. Considere que cada corredor tem 200m. e cada pessoa anda de 4 a 6 m/s. Cada pessoa leva de 1 a 2 segundos para abrir e cruzar a porta. Faça uma aplicação em java que simule essa situação.
 */

package view;

import java.util.concurrent.Semaphore;

import controller.PessoaThread;

public class Main {
	public static void main(String[] args) {
		Semaphore semaforo = new Semaphore(1);
		
		for (int i = 1; i <= 4; i++) {
			PessoaThread t = new PessoaThread(semaforo, i);
			t.start();
		}
		

	}

}
