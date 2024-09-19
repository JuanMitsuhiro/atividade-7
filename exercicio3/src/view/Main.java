/*
Um banco deve controlar Saques e Depósitos. O sistema pode permitir um Saque e um Depósito Simultâneos, mas nunca 2 Saques ou 2 Depósitos Simultâneos. Para calcular a transação (Saque ou Depósito), o método deve receber o código da conta, o saldo da conta e o valor a ser transacionado. Deve-se montar um sistema que considera 20 transações simultâneas enviadas ao sistema (aleatoriamente, essas transações podem ser qualquer uma das opções) e tratar todas as transações. Como são transações aleatórias, podem ser 20 saques e 0 depósitos ou 19 saques e 1 depósito ou 18 saques e 2 depósitos ou .... ou 1 saque e 19 depósitos ou 0 saque e 20 depósitos. 
 */

package view;

import java.util.concurrent.Semaphore;

import controller.DepositoSaque;

public class Main {
	public static void main(String[] args) {
		Semaphore semaforoSaque = new Semaphore(1);
		Semaphore semaforoDeposito = new Semaphore(1);
		int codigo;
		int saldo;
		int valor;
		
		for(int i = 1; i <= 20; i++) {
			codigo = (int)(Math.random()*10000) + 10000;
			saldo = (int)(Math.random()*9501) + 500;
			valor = (int)(Math.random()*5000) + 20;
			DepositoSaque thread = new DepositoSaque(codigo, saldo, valor, semaforoSaque, semaforoDeposito, i);
			thread.start();
		}
		
		
	}

}
