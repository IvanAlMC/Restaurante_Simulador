package models;

public class Waiter {

	/*
	 * Creamos nuestras variables para el mesero
	 */
	private String name;
	private boolean status = true;
	private int paymentTime;
	private int attendedTables = 0;
	
	/**
	 * Creamos el constructor de los meseros
	 * @param name: nombre del mesero
	 * @param estado: estado del mesero
	 * @param tiempoPago: tiempo de pago del mesero
	 */
	public Waiter(String name, boolean estado, int tiempoPago) {
		this.name = name;
		this.status = estado;
		this.paymentTime = tiempoPago;
	}
	
	/**
	 * Obtenemos el nombre del mesero
	 * @return nombre del mesero
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Validamos el estado del mesero
	 * @return estado del mesero
	 */
	public boolean verifyStatus() {
		return status;
	}
	
	/**
	 * Cambiamos el estado del mesero
	 */
	public void changeStatus() {
		status = !status;
	}
	
	/**
	 * Obtenemos el tiempo de pago
	 * @return tiempo de pago
	 */
	public int getPaymentTime() {
		return paymentTime;
	}
	
	/**aumentamos las mesas atendidas
	 * 
	 */
	public void addAttendedTables() {
		this.attendedTables ++ ;
	}
	
	/**
	 * disminuimos las mesas atendidas
	 */
	public void lessAttendedTables() {
		this.attendedTables -- ;
	}
	
	/**
	 * obtenemos las mesas atendidas
	 * @return cantidad de mesas atendidas
	 */
	public int getAttendedTables() {
		return attendedTables;
	}
}
