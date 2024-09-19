package controller;

import java.util.concurrent.Semaphore;

public class CruzamentoThread extends Thread{
	private String sentido;
	private Semaphore semaforo;
	private int idCarro;
	
	public CruzamentoThread(Semaphore semaforo, String sentido) {
		this.semaforo = semaforo;
		this.sentido = sentido;
	}
	
	@Override
	public void run() {
		idCarro = (int)threadId();
		try {
			semaforo.acquire();
			passagem();
			finaliza();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			semaforo.release();
		}
		
	}
	
	private void passagem() {
		try {
			System.out.println("O carro #" + idCarro + " está fazendo o cruzamento sentido " + sentido);
			sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void finaliza() {
		System.out.println("O carro #" + idCarro + " finalizou a passagem");
	}

}
