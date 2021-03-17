package controller;

import java.util.concurrent.Semaphore;

public class ThreadsCompras extends Thread {
	private int cliente;
	private Semaphore semaforo;
	
	public ThreadsCompras(int cliente, Semaphore semaforo){
		this.cliente = cliente;
		this.semaforo = semaforo;
	}
	
	@Override
	public void run(){
		int contadorIngresso = 100;
		int tempoTotal = (int)((Math.random()*1951)+50);
		int tempoCompraTotal = (int)((Math.random()*2001)+1000);
		int compraIngresso = (int)((Math.random()*3)+1);
		LoginSite(tempoTotal);
		if(tempoTotal > 1000){
			System.out.println("Time out! Tempo esgotado! Ki malz, cliente #" + cliente);
		} else {
			System.out.println("Logando");
			FazerPedido(tempoCompraTotal);
			if(tempoCompraTotal > 2500){
				System.out.println("Time out! Tempo esgotado! Ki malz, cliente #" + cliente);
			} else {
				System.out.println("Compra aceita");
				try {
					semaforo.acquire();
					contadorIngresso -= compraIngresso;
					ValidacaoCompra(compraIngresso, contadorIngresso);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					semaforo.release();
				}
				}
			}
		}

	private void LoginSite(int tempoTotal) {
		int tempoCalculo = 0;
		int tempo = 100;
		System.out.println("Cliente #" + cliente + " realizando login");
		while (tempoCalculo < tempoTotal){
			tempoCalculo += tempo;
			try {
				sleep(tempo);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void FazerPedido(int tempoCompraTotal) {
		int tempoCompraCalculo = 0;
		int tempo = 100;
		System.out.println("Cliente #" + cliente + " fazendo pedido");
		while (tempoCompraCalculo < tempoCompraTotal){
			tempoCompraCalculo += tempo;
			try {
				sleep(tempo);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void ValidacaoCompra(int compraIngresso, int contadorIngresso) {
		System.out.println("verificando se há ingressos suficientes para Cliente #" + cliente);
		if(compraIngresso < contadorIngresso){
			System.out.println("Compra realizada! Cliente #" + cliente + " comprou " + compraIngresso);
			System.out.println("Restam " + contadorIngresso);
			} else {
			System.out.println("Ki malz, ingressos esgotados!");
			}
	}
}
