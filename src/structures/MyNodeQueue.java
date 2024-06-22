package structures;

import models.Plate;

public class MyNodeQueue {
	
	//Creamos las variables necesarios para el Nodo
	Plate plate;
	MyNodeQueue next;
	
	/**
	 * Creamos el construtor del nodo
	 * @param plate: un plato como nodo
	 */
	public MyNodeQueue(Plate plate) {
		super();
		this.plate = plate;
	}

	/**
	 * Obtenemos un nodo plato
	 * @return nodo plato
	 */
	public Plate getPlate() {
		return plate;
	}

	/**
	 * Seteamos un nodo plato
	 * @param plate
	 */
	public void setPlate(Plate plate) {
		this.plate = plate;
	}

	/**
	 * Obtenemos el siguiente nodo
	 * @return
	 */
	public MyNodeQueue getNext() {
		return next;
	}

	/**
	 * Seteamos el siguiente nodo
	 * @param next
	 */
	public void setNext(MyNodeQueue next) {
		this.next = next;
	}
}
