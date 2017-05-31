package br.ufrn.imd.rollercoaster.model;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockLista {
	
	private List<Object> objectsBloqueados; //Lista de objetos bloqueados
	private final Lock lock = new ReentrantLock(); //Garante exclusão mútua em quase todos métodos
	
	public LockLista() {
		objectsBloqueados = new ArrayList<>();
	}
	
	/**
	 * Bloqueia o objeto passado
	 * @param object
	 */
	public void bloquear(Object object) {
		try {
			lock.lock();
			objectsBloqueados.add(object);
			lock.unlock();
			
			synchronized (object) {
				object.wait();
			}
	
		} catch (InterruptedException e) {
			e.printStackTrace();
		} 
	} 
	
	/**
	 * Desbloqueia todos os objetos da lista de bloqueio
	 */
	public void desploquearTodos() {
		lock.lock();
		try {	
			for (Object object : objectsBloqueados) {
				synchronized (object) {
					object.notify();
				}
			}
			objectsBloqueados.clear();
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			lock.unlock();
		}

	} 
	
	/**
	 * Desbloqueia os n primeiros objetos da lista
	 * @param n
	 */
	public void desploquear(int n) {
		lock.lock();
		for (int i = 0; i < n; i++) {
			desploquear();
		}
		lock.unlock();
	}
	
	/**
	 * @return Retorna o tamanho da lista
	 */
	public int getTamanhoLista(){
//		lock.lock();
		int tamanho = 0;
		try {
			tamanho = objectsBloqueados.size();
		} finally{
//			lock.unlock();
		}
		return tamanho;
	}

	/**
	 * Desbloqueia o primeiro objeto da lista
	 */
	public void desploquear() {
//		lock.lock();

		try {
			if(!objectsBloqueados.isEmpty())
				synchronized (objectsBloqueados.get(0)) {
					objectsBloqueados.get(0).notify();
					objectsBloqueados.remove(0);
				}
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		} /*finally{
			lock.unlock();
		}*/
	}

}
