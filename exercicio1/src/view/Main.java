/*
Fazer uma aplicação, console, que gerencie a figura abaixo: Para tal, usar uma variável sentido, que será alterado pela Thread que controla cada carro com a movimentação do carro. Quando a Thread tiver a possibilidade de ser executada, ela deve imprimir em console o sentido que o carro está passando. Só pode passar um carro por vez no cruzamento. Usar threadId() ou getId() para identificar os carros.
*/

package view;

import java.util.concurrent.Semaphore;

import controller.CruzamentoThread;

public class Main {
	public static void main(String[] args) {
		Semaphore semaforo = new Semaphore(1);
		String[] sentido = {"Norte", "Sul", "Leste", "Oeste"};
		
		
		for (int i = 0; i < 4; i++) {
			CruzamentoThread t = new CruzamentoThread(semaforo, sentido[i]);
			t.start();
		}
		
		
		
	}

}
