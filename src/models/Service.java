package models;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Service {

	//Creamos las variables para dar el servicio
	private int quantityPeople;
	private int numberTable;
	private int atentionTime;
	public double waiterPoints;
	private Waiter waiter;
	private Payment payment;
	private ArrayList<Plate> entraceMenu = new  ArrayList<>();
	private ArrayList<Plate> principalPlateMenu= new  ArrayList<>();
	private ArrayList<Plate> dessertMenu= new  ArrayList<>();
	private ArrayList<Plate> platesList = new ArrayList<>();
	private HashMap<String, Double> qualification = new HashMap<String, Double>();
	
	/**
	 * Creamos el constructor del servicio
	 * @param mesero: mesero que brinda la atencion
	 * @param mesa: numero de la mesa que esta siendo atendida
	 * @param atencionTiempo: el tiempo que se demora atendiendo
	 */
	public Service(Waiter mesero, int mesa , int atencionTiempo) {
		calcularPeople();
		this.waiter = mesero;
		this.atentionTime = atencionTiempo;
		this.numberTable = mesa;
		calculatePayment();
		createEntraceMenu();
		createPrincipalPlateMenu();
		createDessertMenu();
		fillPlates();
	}
	
	/**
	 * Obetemos el mesero que presta el servicio
	 * @return mesero
	 */
	public Waiter getWaiter() {
		return waiter;
	}

	/**
	 * Obtenemos la cantidad de personas a la que se les da el servicio
	 * @return cantidad de personas
	 */
	public int getQuantityPeople() {
		return quantityPeople;
	}

	/**
	 * Obtenemos el tipo de pago con el que se cancela el servicio
	 * @return tipo de pago
	 */
	public Payment getPayment() {
		return payment;
	}

	/**
	 * Obtenemos el numero de mesa que se le presto el servicio
	 * @return numero de mesa
	 */
	public int getTableNumber() {
		return numberTable;
	}

	/**
	 * Obtenemos la lista de platos
	 * @return lista de platos
	 */
	public ArrayList<Plate> getPlatesList() {
		return platesList;
	}
	
	/**
	 * Obtenemos la calificacion
	 * @return calificacion
	 */
	public HashMap<String, Double> getQualification() {
		return qualification;
	}
	
	/**
	 * Obtenemos el menu de entradas
	 * @return menu de entradas
	 */
	public ArrayList<Plate> getEntraceMenu() {
		return entraceMenu;
	}
	
	/**
	 * Obtenemos el menu de platos fuertes
	 * @return menu de platos fuertes
	 */
	public ArrayList<Plate> getPrincipalPlateMenu() {
		return principalPlateMenu;
	}
	
	/**
	 * Obtenemos el menu de postres
	 * @return menu de postres
	 */
	public ArrayList<Plate> getDessertMenu() {
		return dessertMenu;
	}
	
	/**
	 * Obtenemos el tiempo de antencion
	 * @return tiempo de antencion
	 */
	public int getAtentionTime() {
		return atentionTime;
	}
	
	/**
	 * Obtenemos la puntacion del mesero dando servicio
	 * @return puntacion del mesero
	 */
	public double getWaiterScore() {
		return waiterPoints;
	}
	
	/**
	 * Con este metodo vamos llenando los platos en un pedido donde se 
	 * escogeran de manera aleatoria de la lista de cada categoria de platos
	 */
	private void fillPlates() {
		for (int i = 0; i < quantityPeople; i++) {
			platesList.add(principalPlateMenu.get(new Random().nextInt(principalPlateMenu.size())));
			if (wantDessert()) {
				int can = getQuantityDessert();
				for (int j = 0; j < can; j++) {
					 platesList.add(dessertMenu.get(new Random().nextInt(dessertMenu.size())));
				}
			}
			if (wantEntrace()) {
				platesList.add(entraceMenu.get(new Random().nextInt(entraceMenu.size())));
			}
		}
	}
	
	/**
	 * Validamos si dentro del pedido se va a querer entrada o no
	 * @return retorna si quiere entrada (50%) o no (50%)
	 */
	private boolean wantEntrace() {
		 double random = Math.random();
		 if (random < 0.5) {
			return true;
		}else {
			return false;
		}
	}

	/**
	 * Calculamos la cantidad de postres que se pediran, de manera aleatorio
	 * @return 1(80%) o 2 (20%)
	 */
	private int getQuantityDessert() {
		 double random = Math.random();
		 if (random < 0.8) {
			return 1;
		}else {
			return 2;
		}
	}

	/**
	 * Se verifica si se quier pedir o no postre
	 * @return si (70%), no (30%)
	 */
	private boolean wantDessert() {
		 double random = Math.random();
		 if (random < 0.7) {
			return false;
		}else {
			return true;
		}
	}
	
	/**
	 * Creamos la lista de entradas con sus respectivos parametros
	 */
	private void createEntraceMenu() {
		String[] list1 =  {"Lechuga", "pepino", "Aguacate"};
		entraceMenu.add(new Plate("Ensalada de sal", calculateTime(), list1, Type.ENTRANCE, Difficulty.LOW, 12000));
		String[] list2 =  {"Fresa", "Papaya", "Mango", "Banano"};
		entraceMenu.add(new Plate("Ensalada dulce", calculateTime(), list2, Type.ENTRANCE, Difficulty.HIGH, 10000));
		String[] list3 =  {"Nachos", "Queso"};
		entraceMenu.add(new Plate("Nachos con queso", calculateTime(), list3, Type.ENTRANCE, Difficulty.LOW, 8000));
		String[] list4 =  {"Agua", "Maiz"};
		entraceMenu.add(new Plate("Sopa de Maiz", calculateTime(), list4, Type.ENTRANCE, Difficulty.HIGH, 6000));
		String[] list5 =  {"Pan", "Salsa de ajo"};
		entraceMenu.add(new Plate("Pan de ajo", calculateTime(), list5, Type.ENTRANCE, Difficulty.LOW, 5000));
	}

	/**
	 * Creamos la lista de los platos fuertes, con sus respectivos parametros
	 */
	private void createPrincipalPlateMenu() {
		String[] list1 =  {"Carne", "Aceite"};
		principalPlateMenu.add(new Plate("Carne a la plancha", calculateTime(), list1, Type.PRINCIPAL_PLATE, Difficulty.HIGH, 12000));
		String[] list2 =  {"Trucha", "Aceite"};
		principalPlateMenu.add(new Plate("Trucha a la plancha", calculateTime(), list2, Type.PRINCIPAL_PLATE, Difficulty.LOW, 60000));
		String[] list3 =  {"Lomo de Cerdo", "Aceite"};
		principalPlateMenu.add(new Plate("Lomo de cerdo a la plancha", calculateTime(), list3, Type.PRINCIPAL_PLATE, Difficulty.MEDIUM, 23000));
		String[] list4 =  {"Carne", "Queso", "Pan"};
		principalPlateMenu.add(new Plate("Hamburguesa", calculateTime(), list4, Type.PRINCIPAL_PLATE, Difficulty.MEDIUM, 22000));
		String[] list5 =  {"Arroz", "Pollo"};
		principalPlateMenu.add(new Plate("Arroz con pollo", calculateTime(), list5, Type.PRINCIPAL_PLATE, Difficulty.MEDIUM, 18000));
		String[] list6 =  {"Pasta", "Carne", "Pollo", "Queso"};
		principalPlateMenu.add(new Plate("Lasagna mixta", calculateTime(), list6, Type.PRINCIPAL_PLATE, Difficulty.LOW, 2200));
		String[] list7 =  {"Fideos", "Salsa Bolognesa"};
		principalPlateMenu.add(new Plate("Pasta Bolognesa", calculateTime(), list7, Type.PRINCIPAL_PLATE, Difficulty.LOW, 22000));
		String[] list8 =  {"Mojarra", "Aceite"};
		principalPlateMenu.add(new Plate("Mojarra Frita", calculateTime(), list8, Type.PRINCIPAL_PLATE, Difficulty.LOW, 21000));
		String[] list9 =  {"Costillistas", "Salsa BBQ"};
		principalPlateMenu.add(new Plate("Costillitas BBQ", calculateTime(), list9, Type.PRINCIPAL_PLATE, Difficulty.LOW, 22000));
		String[] list10 =  {"Arroz", "Verduras varias"};
		principalPlateMenu.add(new Plate("Arroz Chino", calculateTime(), list10, Type.PRINCIPAL_PLATE, Difficulty.HIGH, 23000));
	}
	
	/**
	 * Creamos la lista de postres con sus respectivos parametros
	 */
	private void createDessertMenu() {
		String[] list1 =  {"Cafe", "Crema de leche", "Gelatina sin sabor"};
		dessertMenu.add(new Plate("Postre de cafe", calculateTime(), list1, Type.DESSERT, Difficulty.HIGH, 12000));
		String[] list2 =  {"Mora", "Crema de leche", "Gelatina sin sabor"};
		dessertMenu.add(new Plate("Postre de mora", calculateTime(), list2, Type.DESSERT, Difficulty.LOW, 10000));
		String[] list3 =  {"Fresa", "Crema de leche", "Gelatina sin sabor"};
		dessertMenu.add(new Plate("Postre de fresa", calculateTime(), list3, Type.DESSERT, Difficulty.LOW, 8000));
		String[] list4 =  {"Lulo", "Crema de leche", "Gelatina sin sabor"};
		dessertMenu.add(new Plate("Postre de lulo", calculateTime(), list4, Type.DESSERT, Difficulty.LOW, 8000));
		String[] list5 =  {"Durazno", "Crema de leche", "Gelatina sin sabor"};
		dessertMenu.add(new Plate("Postre de durazno", calculateTime(), list5, Type.DESSERT, Difficulty.LOW, 8000));
		String[] list6 =  {"Arroz", "Leche"};
		dessertMenu.add(new Plate("Arroz con leche", calculateTime(), list6, Type.DESSERT, Difficulty.MEDIUM, 8500));
		String[] list7 =   {"Masa", "Queso", "leche"};
		dessertMenu.add(new Plate("Cheseecake", calculateTime(), list7, Type.DESSERT, Difficulty.LOW, 12000));
		String[] list8 =  {"Masa", "Leche"};
		dessertMenu.add(new Plate("Mantecada", calculateTime(), list8, Type.DESSERT, Difficulty.LOW, 8000));
	}

	/**
	 * Generamos el tiempo  del servicio de manera aleatoria, donde el tiempo promedio de servicio
	 * es de 783 segundos con una desviacion de 99 segundos
	 * @return tiempo del servicio
	 */
	private double calculateTime() {
		DecimalFormat f = new DecimalFormat("##.00");
		double time = 0;
		Random r = new Random();
		double random = Math.random();
		 if (random < 0.5) {
			 time = 783 + (882 - 783) * r.nextDouble();
		}else if(random < 0.75){
			time = 684 + (783 - 648) * r.nextDouble();
		}else {
			time = 882 + (981 - 882) * r.nextDouble();
		}
		 time = Double.parseDouble(f.format(time));
		return time;
	}
	
	/**
	 * Generamos la puntacion del mesero que presta el servicio
	 */
	public void calculateWaiterScore() {
		Random r = new Random();
		double random = Math.random();
		 if (random < 0.8) {
			 waiterPoints = 4 + (5 - 4) * r.nextDouble();
		}else if(random <0.9){
			waiterPoints = 3 + (4 - 3) * r.nextDouble();
		}else {
			waiterPoints = 2 + (3 - 2) * r.nextDouble();
		}
	}
	
	/**
	 * generamos el tipo de pago de manera aleatoria donde se plantea
	 * 40% en efectivo, 30% para transaccion y 30% para tarjeta
	 */
	private void calculatePayment() {
		double random = Math.random();
		if (random <= 0.4) {
			 this.payment = Payment.CASH;
		}else if(random > 0.4 && random < 0.7) {
			this.payment = Payment.TRANSACTION;
		}else {
			this.payment = Payment.CARD;
		}
	}
	
	//En la toma de datos obtuvimos que el 44% 1 persona, 28% 2 personas
	//8% 3 personas, 10% 4 personas y 10% 5 personas
	private void calcularPeople() {
		 double random = Math.random();
		if (random < 0.44) {
			this.quantityPeople = 1;
		}else if (random < 0.28) {
			this.quantityPeople = 2;
		}else if (random < 0.08) {
			this.quantityPeople = 3;	
		}else if(random < 0.10){
			this.quantityPeople = 4;
		}else {
			this.quantityPeople = 5;
		}
	}

	/**
	 * Se genera la puntuacion del plato
	 */
	public void calculateScore() {
		DecimalFormat f = new DecimalFormat("##.00");
		for (Plate plate : platesList) {
			double calificacion = 0.1;
			if (!plate.isError()) {
				calificacion= normalQualification();
			}else {
				calificacion =  errorQualification();
			}
			qualification.put(plate.getName()+","+plate.getType(), Double.parseDouble(f.format(calificacion)));
		}
	}
	
	/**
	 * Generamos el puntaje de error
	 * @return puntaje de error
	 */
	private double errorQualification() {
		 double random = Math.random();
		 Random r = new Random();
		 if (random > 0.09 && random <= 0.1) {
			return  1 + (2 - 1) * r.nextDouble();
		}else if(random > 0.08 && random <=0.09) {
			return  2 + (3 - 2) * r.nextDouble();
		}else if(random > 0.05 && random <=0.08) {
			return  3 + (4 - 3) * r.nextDouble();
		}
		return 0;
	}

	/**
	 * Generamos una calificacion de un plato sin errores
	 * @return puintacion de un plato sin errores
	 */
	private double normalQualification() {
		 double random = Math.random();
		 Random r = new Random();
		 if (random < 0.7) {
			return  4 + (5 - 4) * r.nextDouble();
		}else if(random < 0.9) {
			return  3 + (4 - 3) * r.nextDouble();
		}else if(random < 1) {
			return  1 + (3 - 1) * r.nextDouble();
		}
		return 0;
	}
}
