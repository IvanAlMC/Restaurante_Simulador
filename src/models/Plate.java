package models;

public class Plate {

	//Creamos las variables de un plato
	private String name;
	private double preparationTime;
	private String[] ingredients;
	private Type type;
	private Difficulty difficulty;
	private boolean error;
	private double price;
	private boolean status = true;
	
	/**
	 * Creamos el contructor de un plato
	 * @param name: nombre del plato
	 * @param preparationTime: tiempo de preparacion del plato
	 * @param ingredients: ingredientes que usa el plato
	 * @param type: tipo de plato (entrada, fuerte, postre)
	 * @param difficulty: difucltad para preparlo (bajo, medio, alto)
	 * @param price: precio del plato
	 */
	public Plate(String name, double preparationTime, String[] ingredients, Type type, Difficulty difficulty , double price) {
		this.name = name;
		this.price = price;
		this.preparationTime = preparationTime;
		this.ingredients = ingredients;
		this.type = type;
		this.difficulty = difficulty;
		this.error = calculateError();
	}

	/**
	 * Este metodo calcula la probabilidad de que ocurra un error en la preparacion del plato
	 * Donde se crea un numero aleatorio entre 0 y 1, y si este numero esta entre el rango dado
	 * para cada uno de los tipos de difucultad sea la que sea, dara como resultado que si ocurrio un error
	 * @return un booleano para saber si hubo un error de preparacion o no.
	 */
	private boolean calculateError() {
		double random = Math.random();
		if (this.difficulty.equals(Difficulty.LOW) && random > 0.05 && random <=0.08) {
			return true;
		}else if (this.difficulty.equals(Difficulty.MEDIUM) && random > 0.08 && random <=0.09) {
			return true;
		}else if (this.difficulty.equals(Difficulty.HIGH) && random > 0.09 && random <= 0.1) {
			return true;
		}
		return false;
	}
	
	/**
	 * Obtiene el tiempo de preparacion
	 * @return tiempo de preparacion
	 */
	public double getPreparationTime() {
		return preparationTime;
	}

	/**
	 * Obtiene los ingredientes
	 * @return lista de ingredientes
	 */
	public String[] getIngredients() {
		return ingredients;
	}

	/**
	 * Obtiene el tipo de plato
	 * @return tipo de plato entre entrada, fuerte o postre
	 */
	public Type getType() {
		return type;
	}

	/**
	 * Obtiene la dificultad de preparacion
	 * @return dificultad de preparacion entre bajo, medio o alto
	 */
	public Difficulty getDifficulty() {
		return difficulty;
	}

	/**
	 * Verifica su hubo error
	 * @return el error
	 */
	public boolean isError() {
		return error;
	}
	
	/**
	 * Obtiene el nombre del plato
	 * @return nombre del plato
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Obtiene el precio del plato
	 * @return precio del plato
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * Obtiene el estado del plato
	 * @return estado del plato
	 */
	public boolean isStatus() {
		return status;
	}

	/**
	 * Se esta cocinando el plato, por lo cula su tiempo de preparacion disminuye
	 */
	public void cook() {
		preparationTime --;
	}
	
	/**
	 * Seear el estado del plato
	 * @param estado
	 */
	public void setStatus(boolean estado) {
		this.status = estado;
	}

	/**
	 *Obtenemos el String de la clase
	 */
	@Override
	public String toString() {
		return "Plate [name=" + name + ", preparationTime=" + preparationTime + ", type=" + type + ", error=" + error
				+ "]";
	}
}