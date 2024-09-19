package controller;

import java.util.concurrent.Semaphore;

public class PessoaThread extends Thread{
	private Semaphore semaforo;
	private int num;
	
	public PessoaThread(Semaphore semaforo, int num) {
		this.semaforo = semaforo;
		this.num = num;
	}
	
	@Override
	public void run() {
		
		andar(4, 6, 200);
		System.out.println("Pessoa " + num + " andou 200m e chegou no fim do corredor");
		try {
			semaforo.acquire();
			entrarPorta(1000, 2000);
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		} finally {
			semaforo.release();
		}
		
	}
	
	public void andar(int min, int max, int percurso) {
		int total = 0;
		while(total < percurso){
			try {
				sleep(1000);
				System.out.println("Pessoa " + num + " andou " + total + "m");
			} catch (Exception e) {
				e.getStackTrace();
			}
			total += (int)(Math.random()*max - min + 1) + min;
		}
			
	}
	
	public void entrarPorta(int min, int max) {
		int tempo = (int)(Math.random()*max - min +1) + max;
		try {
			sleep(tempo);
			System.out.println("Pessoa " + num + " abriu e passou pela porta");
		} catch (Exception e) {
			e.getStackTrace();
		}
	}

}
