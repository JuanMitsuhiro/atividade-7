package controller;

import java.util.concurrent.Semaphore;

public class DepositoSaque extends Thread{
	private Semaphore semaforoSaque;
	private Semaphore semaforoDeposito;
	private int codigo;
	private int saldo;
	private int valor;
	private int num;

	public DepositoSaque(int codigo, int saldo, int valor, Semaphore semaforoSaque, Semaphore semaforoDeposito, int num) {
		this.semaforoDeposito = semaforoDeposito;
		this.semaforoSaque = semaforoSaque;
		this.codigo = codigo;
		this.saldo = saldo;
		this.valor = valor;
		this.num = num;
	}
	
	@Override
	public void run() {
		int decisaoTransacao = (int)(Math.random()*2);
		
		if (decisaoTransacao == 0) {
			try {
				semaforoDeposito.acquire();
				deposito();
			} catch (Exception e) {
				e.printStackTrace();
			} finally{
				semaforoDeposito.release();
			}
		} else {
			try {
				semaforoSaque.acquire();
				saque();
			} catch (Exception e) {
				e.printStackTrace();
			} finally{
				semaforoSaque.release();
			}
		}
		
	}
	

	public void deposito() {
		saldo += valor;
		System.out.println("Transação " + num + " - código "+ codigo +"\n - Depósito: " + valor +"\n - Saldo: " + saldo + "\n");
	}
	
	public void saque() {
		if (saldo < valor) {
			System.out.println("Transação " + num + " interrompida: valor do saque (" + valor + ") maior que saldo (" + saldo + ")\n");
			return;
		} 
		
		saldo -= valor;
		System.out.println("Transação " + num + " - código "+ codigo +"\n - Saque: " + valor +"\n - Saldo: " + saldo + "\n");
		
	}
}
