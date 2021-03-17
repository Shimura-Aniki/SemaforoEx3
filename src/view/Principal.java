package view;

import java.util.concurrent.Semaphore;

import controller.ThreadsCompras;

public class Principal {

	public static void main(String[] args) {
		
		int permissoes = 1;
		Semaphore semaforo = new Semaphore(permissoes);
		
		for(int cliente = 1; cliente < 10; cliente++){
				ThreadsCompras tCliente = new ThreadsCompras(cliente, semaforo);
				tCliente.start();
		} 			
	}

}

