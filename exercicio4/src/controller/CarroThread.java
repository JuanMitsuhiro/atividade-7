package controller;

import java.util.concurrent.Semaphore;

public class CarroThread extends Thread{
	private int qtdaCarro;
	private int escudeira;
	private Semaphore semaforoPista;
	private Semaphore semaforoEscudeira;
	private double melhorTempo;
	private int idCarro;
	
	//++numCarros, ++numEscudeira, semaforoPista, semaforoEscudeira[numEscudeira]
	public CarroThread(int qtdaCarro, int escudeira, Semaphore semaforoPista, Semaphore semaforoEscudeira) {
		this.qtdaCarro = qtdaCarro;
		this.escudeira = escudeira;
		this.semaforoPista = semaforoPista;
		this.semaforoEscudeira = semaforoEscudeira;
	}
	
	@Override
	public void run() {
		idCarro = (int)threadId();
		try {
			semaforoEscudeira.acquire();
			semaforoPista.acquire();
			corridaVoltas();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			semaforoPista.release();
			semaforoEscudeira.release();
		}
	}
	
	private void corridaVoltas() {
		System.out.println("O carro #" + idCarro + " entrou na pista!");
		int min = 20000;
		int max = 35000;
		double volta;
		melhorTempo = max;
		for(int i = 1; i <= 3; i++) {	
			volta = (Math.random()*(max-min+1))+min;
			try {
				sleep((int)volta);
				volta /= 1000;
				System.out.println("O " + idCarro +" carro ("+ qtdaCarro+"º carro) da Equipe " + escudeira + " finalizou a " + i + " ª volta em " + String.format("%.3f", volta) + "s");
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (volta < melhorTempo) {
				melhorTempo = volta;
			}
		}
	}
	
	public double getMelhorTempo() {
		return melhorTempo;
	}
	
	public int getCarroId() {
		return idCarro;
	}

}
