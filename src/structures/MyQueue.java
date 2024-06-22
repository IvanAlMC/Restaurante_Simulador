package structures;

import models.Plate;

public class MyQueue{
	
	//Creamos las variables necesarias para nuestra cola
	private MyNodeQueue head, end;
	
	
	/**
	 * Anañidos un nodo plato a la cola
	 * @param plate
	 */
	public void push(Plate plate){
		MyNodeQueue nodeWork = new MyNodeQueue(plate);
		if (head == null) {
			head = nodeWork;
			end = nodeWork;
		}else {
			end.next = nodeWork;
		}
		end = nodeWork;
	}

	/**
	 * Quitamos un nodo plato de la cola
	 */
	public void  poll(){
		if (head != null) {
			MyNodeQueue nodeDelete = head;
			head = head.next;
			nodeDelete.next = null;
			if (head == null) {
				end = null;
			}
		}
	}

	/**
	 * Obtenemos el primer nodo plato de la cola
	 * @return primer elemento de la cola
	 */
	public Plate peek(){
		if (head == null) {
			return null;
		}else {
			return head.plate;
		}
	}
  
	/**
	 * Obtenemos la cabecera de la cola
	 * @return cabecera de la cola
	 */
	public MyNodeQueue getHead() {
		return head;
	}
}
