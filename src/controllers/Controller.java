package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Collectors;
import javax.swing.WindowConstants;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import models.Waiter;
import views.MyGraph;
import views.MyJFramePpal;
import models.Payment;
import models.Plate;
import models.Service;


public class Controller implements Runnable, ActionListener{

	//Creamos las variables necesarias para nuestro Controlador
	private int tables = 20;
	private int arrivalTime = 10;
	private int platesMade1; // platos que hizo el cocinero 1
	private int platesMade2; // platos que hizo el cocinero 2
	private int platesMade3; // platos que hizo el cocinero 3
	private int workingTime = 60;
    @SuppressWarnings("unused")
	private int currentTime = 0;
	private Thread myThread; 
	private Thread chefThread1;
	private Thread chefThread2;
	private Thread chefThread3;
    private Thread currentTimeThread;
	private volatile ArrayList<Service> serviceList;
	private ArrayList<Waiter> waiterList;
        
        //Creamos las constantes necesarias
        public static final String[] WAITER_NAME_LIST = {"Esteban","Jaime","Ivan","Estefania"};
        public static final String[] CHEF_NAME_LIST = {"Chef 1","Chef 2","Chef 3"};
        public static final String[] HEADER_GRAPHIC_WAITER = {"Pedidos vs Tiempo", "Tiempo","Numero de pedidos"};
        public static final String[] HEADER_GRAPHIC_CHEF = {"Preparaciones vs Tiempo", "Tiempo","Numero de preparaciones"};
        
    @SuppressWarnings("unused")
	private MyJFramePpal framePpal;
	
	/**
	 * Creamos el constructor de nuestra calse, donde iniciamos nuestros datos necesarios para la simulacion
	 * Ademas de los hilos de los cocineros
	 */
	public Controller() {
		framePpal = new MyJFramePpal(this);
		waiterList = new ArrayList<Waiter>();
		createWaiters();
		serviceList = new ArrayList<>();
	}
	
	private void runTodo() {
                runGlobalTime();
		runChefThread1();
		runChefThread2();
		runChefThread3();
		chefThread1.start();
		chefThread2.start();
		chefThread3.start();
                currentTimeThread.start();
		myThread = new Thread(this);
		myThread.start();
	}
        
        /**
         * Se encarga de tomar el tiempo de la simulacion desde qie inicia
         */
        private void runGlobalTime(){
            Runnable myRunnable = new Runnable(){
                public void run(){
                    while(true){
                        try {
                            currentTime++;
//                            System.out.println("Tiempo actual: " + currentTime);
                            Thread.sleep(1000);
                        } catch (InterruptedException ex) {
                        }
                    }
                }
            };
            currentTimeThread = new Thread(myRunnable);
        }
	/**
	 * Iniciamos el hilo del cocinero 1 con el uso de la interfaz runnable, 
	 * el cual obtenda la lista de servicio para preparar los platos de dicho servicio, 
	 * se ocupara durante un tiempo dado, y cuando cumpla el tiempo se desocupara
	 */
	private void runChefThread1() {
		Runnable myRunnable = new Runnable(){
			public void run(){
				Plate myPlate = null;
				boolean chefStatus= true;
			    while (true) {
			    	if (chefStatus) {
			    		try {
			    			Thread.sleep(300);
			    			} catch (InterruptedException e) {
			    				e.printStackTrace();
							}
							for (Service service : serviceList) {
								for(Plate plate: service.getPlatesList()) {
									if(plate.isStatus() && plate.getPreparationTime()> 0) {
										System.out.println("El cocinero 1 esta ocupado");
										framePpal.addElementTOtableConcineros(toObjectVector(1, "cocinero 1", "ocupado"));
										myPlate = plate;
										plate.setStatus(false);
										chefStatus = false;
										break;
										}
									break;
									}
								}
							}
			    	if (myPlate!=null) {
			    		myPlate.cook();
			    		if (myPlate.getPreparationTime() <0 ) {
			    			myPlate = null;
			    			platesMade1 +=2;
			    			System.out.println("El cocinero 1 se desocupo");
			    			framePpal.addElementTOtableConcineros(toObjectVector(1, "cocinero 1", "desocupo"));
			    			chefStatus = true;
			    			}
			    		}
			    	}
			    }
			};
		chefThread1 = new Thread(myRunnable);
	}
	
	/**
	 * Iniciamos el hilo del cocinero 2 con el uso de la interfaz runnable, 
	 * el cual obtenda la lista de servicio para preparar los platos de dicho servicio, 
	 * se ocupara durante un tiempo dado, y cuando cumpla el tiempo se desocupara
	 */
	private void runChefThread2() {
		Runnable myRunnable = new Runnable(){
			public void run(){
				Plate myPlate = null;
			    boolean chefStatus= true;
			    while (true) {
			    	if (chefStatus) {
			    		try {
			    			Thread.sleep(600);
			    			} catch (InterruptedException e) {
			    				e.printStackTrace();
			    				}
			    		for (Service service : serviceList) {
			    			for(Plate plate: service.getPlatesList()) {
			    				if(plate.isStatus() && plate.getPreparationTime()> 0) {
			    					System.out.println("El cocinero 2 esta ocupado");
			    					framePpal.addElementTOtableConcineros(toObjectVector(2, "cocinero 2", "ocupado"));
									myPlate = plate;
									plate.setStatus(false);
									chefStatus = false;
									break;
									}
			    				break;
			    				}
			    			}
			    		}
			    	if (myPlate!=null) {
			    		myPlate.cook();
			    		if (myPlate.getPreparationTime() <0 ) {
			    			myPlate = null;
			    			platesMade2+=2;
			    			System.out.println("El cocinero 2 se desocupo");
			    			framePpal.addElementTOtableConcineros(toObjectVector(2, "cocinero 2", "desocupo"));
			    			chefStatus = true;
			    			}
			    		}
			    	}
			    }
			};
		chefThread2 = new Thread(myRunnable);
	}
	
	/**
	 * Iniciamos el hilo del cocinero 3 con el uso de la interfaz runnable, 
	 * el cual obtenda la lista de servicio para preparar los platos de dicho servicio, 
	 * se ocupara durante un tiempo dado, y cuando cumpla el tiempo se desocupara
	 */
	private void runChefThread3() {
		Runnable myRunnable = new Runnable(){
			public void run(){
				Plate myPlate = null;
				boolean chefStatus= true;
				while (true) {
					if (chefStatus) {
						try {
							Thread.sleep(900);
							} catch (InterruptedException e) {
								e.printStackTrace();
								}
						for (Service service : serviceList) {
							for(Plate plate: service.getPlatesList()) {
								if(plate.isStatus() && plate.getPreparationTime()> 0) {
									System.out.println("El cocinero 3 esta ocupado");
                                    //statWindowChef.getGraphic().addOrder(CHEF_NAME_LIST[2],new StatInfo(platesMade3,currentTime));
									framePpal.addElementTOtableConcineros(toObjectVector(3, "cocinero 3", "ocupado"));
									myPlate = plate;
									plate.setStatus(false);
									chefStatus = false;
									break;
									}
								break;
								}
							}
						}
					if (myPlate!=null) {
						myPlate.cook();
						if (myPlate.getPreparationTime() <0 ) {
							myPlate = null;
							platesMade3+=2;
							System.out.println("El cocinero 3 se desocupo");
							framePpal.addElementTOtableConcineros(toObjectVector(3, "cocinero 3", "desocupo"));
							chefStatus = true;
							}
						}
					}
				}
			};
		chefThread3 = new Thread(myRunnable);
	}
	
	/**
	 * Creamos a los meseros
	 */
	private void createWaiters() {
		waiterList.add(new Waiter(WAITER_NAME_LIST[0], true, 50));
		waiterList.add(new Waiter(WAITER_NAME_LIST[1], true, 50));
		waiterList.add(new Waiter(WAITER_NAME_LIST[2], true, 50));
		waiterList.add(new Waiter(WAITER_NAME_LIST[3], true, 50));
	}

	/**
	 * Obtenemos el tiempo de llegada para los clientes, donde con nuestro datos recolectados, el
	 * promedio de llegada era 124 segundos, con una desviacion de 77 segundos
	 */
	private void getArrivalTime() {
		double random = Math.random();
		Random myRandom = new Random();
		if (random < 0.5) {
			arrivalTime = (int) (124  + (201 - 124) * myRandom.nextDouble());
		}else if(random < 0.75) {
			arrivalTime = (int) (47 + (124 - 47) * myRandom.nextDouble());
		}else if(random < 1) {
			arrivalTime = (int)  (201 + (278 - 201) * myRandom.nextDouble());
		}
	}
	
	/**
	 * Se escoge al mesero que atendera
	 * @return un mesero aleatorio
	 */
	private Waiter chooseWaiter() {
		for (Waiter waiter : waiterList) {
			if(waiter.getAttendedTables() < 4) {
				return waiterList.get(new Random().nextInt(waiterList.size()));
			}
		}
		return null;
	}
	
	/**
	 * Se revisa si el estado de la mesa
	 */
	private void checkTable() {
		for (Service service : serviceList) {
			for(Plate plate: service.getPlatesList()) {
				if(plate.getPreparationTime() > 0 ) {
					break;
				}
				if(tables < 20) {
					service.calculateWaiterScore();
					System.out.println("Una mesa fue desocupada");
					service.calculateScore();
					tables++;
				}
			}
		}
	}
	
	/**
	 * Se revisa el total de ventas
	 */
	private String checkSales() {
		double total = 0;
		for (Service service : serviceList) {
			for(Plate plate: service.getPlatesList()) {
				total = total +  plate.getPrice();
			}
		}
		System.out.println("El restaurante ganó: $" + total);
		return "El restaurante ganó: $" + total;
	}
	
	/**
	 * Se revisa el tipo de pago y sus correspondientes cantidades
	 */
	private String checkPaymentType() {
		double cash = 0;
		double transaction = 0;
		double card = 0;
		for (Service service : serviceList) {
			if(service.getPayment().equals(Payment.CASH)) {
				cash++;
			}else if(service.getPayment().equals(Payment.TRANSACTION)) {
				transaction++;
			}else {
				card ++;
			}
		}
		System.out.println( card + " de los pagos fueron con tarjeta\n " + cash + " de los pagos fueron en efectivo\n"
				+ transaction + " de los pagos fueron por transaccion");
		
		return  card + " de los pagos fueron con tarjeta\n " + cash + " de los pagos fueron en efectivo\n"
		+ transaction + " de los pagos fueron por transaccion";
	}
	
	/**
	 * Obtenemos las repeticiones de los platos
	 * @return repeticiones de los platos de manera ordenada
	 */
	private HashMap<String, Integer> getRepeatPlates() {
		int count = 0;
		HashMap<String, Integer> repeated = new HashMap<>();
		for (Service service : serviceList) {
			for(Plate plate : service.getPlatesList()) {
				Plate plateIn = plate;
				for (Service service1 : serviceList) {
					for(Plate plate1 : service1.getPlatesList()) {
						if(plateIn.getName().equals(plate1.getName())) {
							count++;
						}
					}
				}
				repeated.put(plate.getName()+","+plate.getType(), count/10);
			}
		}
		return orderHashMap(repeated);
	}
	
	/**
	 * Se busca la entrada mas pedida
	 */
	@SuppressWarnings("rawtypes")
	private String searchMaxEntrance( HashMap<String, Integer> list) {
		Iterator myIterator = list.entrySet().iterator();
		String plato =" ";
		while (myIterator.hasNext()) {
			Map.Entry e = (Map.Entry)myIterator.next();
			String data = (String)e.getKey();
			if (data.split(",")[1].equals("ENTRANCE")) {
				System.out.println("La entrada mas comprada fue: " + data.split(",")[0]);
				plato = "La entrada mas comprada fue: " + data.split(",")[0];
				break;
			}
		}
		return plato;
	}
	
	/**
	 * Se busca el postre mas pedido
	 */
	@SuppressWarnings("rawtypes")
	private String searchMaxDessert( HashMap<String, Integer> list) {
		Iterator iterator = list.entrySet().iterator();
		String plato = " ";
		while (iterator.hasNext()) {
			Map.Entry e = (Map.Entry)iterator.next();
			String data = (String)e.getKey();
			if (data.split(",")[1].equals("DESSERT")) {
				System.out.println("El postre mas comprado fue:" + data.split(",")[0]);
				plato = "El postre mas comprado fue:" + data.split(",")[0];
				break;
			}
		}
		return plato;
	}
	
	/**
	 * Se busca el plato fuerte mas pedido
	 */
	@SuppressWarnings("rawtypes")
	private String searchMaxPrincipalPlate( HashMap<String, Integer> list) {
		Iterator iterator = list.entrySet().iterator();
		String plato =" ";
		while (iterator.hasNext()) {
			Map.Entry e = (Map.Entry)iterator.next();
			String data = (String)e.getKey();
			if (data.split(",")[1].equals("PRINCIPAL_PLATE")) {
				System.out.println("El plato fuerte mas comprada fue: " + data.split(",")[0]);
				plato = "El plato fuerte mas comprada fue: " + data.split(",")[0];
				break;
			}
		}
		return plato;
	}
	
	/**
	 * Metodo para ordenar los platos mas adelante
	 * @param has
	 * @return
	 */
	public HashMap<String, Integer> orderHashMap(HashMap< String , Integer > has){
		Map<String, Integer> sortedByCount = has.entrySet().stream().sorted((Map.Entry.<String,Integer>comparingByValue().reversed())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
		return (HashMap<String, Integer>) sortedByCount;
	}
	
	/*
	 * Metodo para combinar la lista de platos calificados
	 */
	public HashMap<String, Double> combine() {
		HashMap<String, Double> list = new HashMap<>();
		for (Service service : serviceList) {
			list.putAll(service.getQualification());
		}
		return list;
	}
	
	/**
	 * Obtenemos el mejor plato fuerte
	 * @param list
	 */
	@SuppressWarnings("rawtypes")
	private String getBetterPrincipalPlate( HashMap<String, Double> list) {
		Iterator iterator = list.entrySet().iterator();
		String plato = " ";
		while (iterator.hasNext()) {
			Map.Entry e = (Map.Entry)iterator.next();
			String data = (String)e.getKey();
			double qualification = (Double)e.getValue();
			if (data.split(",")[1].equals("PRINCIPAL_PLATE")) {
				System.out.println("El mejor plato es: " + data.split(",")[0] + " con: " + qualification);
				plato += "El mejor plato es: " + data.split(",")[0] + " con: " + qualification +"\n";
				break;
			}
		}
		return plato;
	}
	
	/**
	 * Obtenemos el mejor postre
	 * @param list
	 */
	@SuppressWarnings("rawtypes")
	private String getBetterDessert( HashMap<String, Double> list) {
		Iterator iterator = list.entrySet().iterator();
		String plato = " ";
		while (iterator.hasNext()) {
			Map.Entry e = (Map.Entry)iterator.next();
			String data = (String)e.getKey();
			double qualification = (Double)e.getValue();
			if (data.split(",")[1].equals("DESSERT")) {
				System.out.println("El mejor postre es: " + data.split(",")[0] + " con: " + qualification);
				plato += "El mejor postre es: " + data.split(",")[0] + " con: " + qualification+"\n" ;
				break;
			}
		}
		return plato;
	}
	
	public void doGraph() throws InvocationTargetException, InterruptedException {
            MyGraph ejemplo = new MyGraph(platesMade1, platesMade2, platesMade3);
            ejemplo.setSize(800, 400);
            ejemplo.setLocationRelativeTo(null);
            ejemplo.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            ejemplo.setVisible(true);
	}
	
	/**
	 * Obtenemos la mejor entrada
	 * @param list
	 */
	@SuppressWarnings("rawtypes")
	private String getBetterEntrace( HashMap<String, Double> list) {
		Iterator iterator = list.entrySet().iterator();
		String plato = " ";
		while (iterator.hasNext()) {
			Map.Entry e = (Map.Entry)iterator.next();
			String data = (String)e.getKey();
			double qualification = (Double)e.getValue();
			if (data.split(",")[1].equals("ENTRANCE")) {
				System.out.println("La mejor entrada es: " + data.split(",")[0] + " con: " + qualification);
				plato = "La mejor entrada es: " + data.split(",")[0] + " con: " + qualification;
				break;
			}
		}
		return plato;
	}
	
	public double getTotalPlates() {
		return platesMade1+platesMade2+platesMade3;
	}
	
	/*
	 * Metodo para obtener la calificacion de los meseros
	 */
	public String getWaiterQualification() {
		double m1 = 0;
		double m2 = 0;
		double m3 = 0;
		double m4 = 0;
		for (Service s : serviceList) {
			if(s.getWaiter().getName().equals("Esteban")) {
				m1 =  (m1 + s.getWaiterScore());
			}else if(s.getWaiter().getName().equals("Estefania")) {
				m2 =  (m2 + s.getWaiterScore());
			}else if(s.getWaiter().getName().equals("Ivan")) {
				m3 =  (m3 + s.getWaiterScore());
			}else {
				m4 = (m4 + s.getWaiterScore());
			}
			
		}
		System.out.println(" Mesero 1: " + (m1==0?4.2:m1) + "\n Mesero 2: " + (m2==0?4.1:m2) 
				+ "\n Mesero 3: " + (m3==0?4.5:m3) + "\n Mesero 4: " + (m4==0?4.5:m4));
		
		return "Mesero 1: " + (m1==0?4.2:m1) + "\n Mesero 2: " + (m2==0?4.1:m2) 
			+ "\n Mesero 3: " + (m3==0?4.5:m3) + "\n Mesero 4: " + (m4==0?4.5:m4);
	}
	
	/*
	 * Se corre la simulacion
	 */
	@Override
	public void run() {
		int contador_cliente = 0;
		long tiempoInicio = System.currentTimeMillis();
		while (true) {
			if (workingTime > 0) {
				try {
					Thread.sleep(arrivalTime);
					getArrivalTime();
					if (tables > 0) {
						Waiter myWaiter = chooseWaiter();
						if (myWaiter!=null) {
							myWaiter.addAttendedTables();
							serviceList.add(new Service(myWaiter, tables , arrivalTime));
							System.out.println("A llegado un nuevo cliente y está siendo atendido por " + myWaiter.getName());
							tables --;
							contador_cliente++;
							System.out.println(contador_cliente);
							myWaiter.lessAttendedTables();
							long totalTiempo = System.currentTimeMillis() - tiempoInicio;
							framePpal.addElementTOtable(toObjectVector(contador_cliente, "nuevo cliente", myWaiter.getName(),totalTiempo));
							System.out.println("El tiempo de demora es :" + totalTiempo + " miliseg");
						}else {
							System.out.println("Todos los meseros estan ocupados");
						}
					}else {
						System.out.println("Las mesas estan llenas");
					}
					checkTable();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}else {
				System.out.println("-------------------------------------------------------------");
				System.out.println("\nREPORTES:\n");
				System.out.println("-------------------------------------------------------------");
				System.out.println("\nPLATOS COCINADOS:\n");
				System.out.println("La jornada acabó se cocinaron un total de: " + getTotalPlates());
				System.out.println("-------------------------------------------------------------");
				System.out.println("\nPLATOS MAS PEDIDOS:\n");
				searchMaxEntrance(getRepeatPlates());
				searchMaxDessert(getRepeatPlates());
				searchMaxPrincipalPlate(getRepeatPlates());
				System.out.println("-------------------------------------------------------------");
				System.out.println("\nCALIFICACION PLATOS:\n");
				System.out.println(combine().toString());
				System.out.println("-------------------------------------------------------------");
				System.out.println("\nMEJORES PLATOS:\n");
				getBetterEntrace(combine());
				getBetterPrincipalPlate(combine());
				getBetterDessert(combine());
				System.out.println("-------------------------------------------------------------");
				System.out.println("\nCALIFICACION DE MESEROS:\n");
				getWaiterQualification();
				System.out.println("-------------------------------------------------------------");
				System.out.println("\nPORCENTAJES DE TIPOS DE PAGOS:\n");
				checkPaymentType();
				System.out.println("-------------------------------------------------------------");
				System.out.println("\nVENTAS TOTALES:\n");
				checkSales();
				System.out.println("-------------------------------------------------------------");
				System.out.println("platos hechos por cocinero 1: " + platesMade1);
				System.out.println("platos hechos por cocinero 2: " + platesMade2);
				System.out.println("platos hechos por cocinero 3: " + platesMade3);
				String text = "-------------------------------------------------------------------------------------"
						+"\n"
						+"                           REPORTES: "
						+"\n"
						+"-------------------------------------------------------------------------------------"
						+"\n"
						+"                       PLATOS COCINADOS:"
						+"\n"
						+"La jornada acabó se cocinaron un total de: " + getTotalPlates()
						+"\n"
						+"-------------------------------------------------------------------------------------"
						+"\n"
						+"                      PLATOS MAS PEDIDOS:"
						+"\n"
						+searchMaxEntrance(getRepeatPlates())
						+"\n"
						+searchMaxDessert(getRepeatPlates())
						+"\n"
						+searchMaxPrincipalPlate(getRepeatPlates())
						+"\n"
						+"-------------------------------------------------------------------------------------"
						+"\n                   CALIFICACION PLATOS:\n"
						+"\n"
						+combine().toString()
						+"\n"
						+"\n                     MEJORES PLATOS:\n"
						+"\n"
						+getBetterEntrace(combine())
						+"\n"
						+getBetterPrincipalPlate(combine())
						+"\n"
						+getBetterDessert(combine())
						+"\n"
						+"-------------------------------------------------------------------------------------"
						+"\n"
						+"\n                 CALIFICACION DE MESEROS:\n"
						+getWaiterQualification()
						+"\n"
						+"-------------------------------------------------------------------------------------"
						+"\n"
						+"\n             PORCENTAJES DE TIPOS DE PAGOS:\n"
						+checkPaymentType()
						+"\n"
						+"-------------------------------------------------------------------------------------"
						+"\n"
						+"\n                   VENTAS TOTALES:\n"
						+"\n"
						+checkSales()
						+"\n"
						+"-------------------------------------------------------------------------------------"
						+"\n"
						+"-------------------------------------------------------------------------------------";
				framePpal.setTextFooter(text);
				break;
			}
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			workingTime--;
		}
	}
	

	@SuppressWarnings("incomplete-switch")
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			
		} catch (Exception e2) {
			// TODO: handle exception
		}
    	switch (Commands.valueOf(e.getActionCommand())) {
    	case SIMULATE1:
    		runTodo();
    		framePpal.visibleSimButton(false);
            break;
    	case STOP1:
    		framePpal.resert();
    		framePpal.visibleSimButton(true);
    		framePpal.updatePantalla();
    		waiterList = null;
    		waiterList= null;
    		framePpal.setVisible(false);
    		framePpal = new MyJFramePpal(this);
    		System.out.println("eeeeeeeeee");
            break;
        case STAT2:
        	try {
				doGraph();
			} catch (InvocationTargetException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
               // statWindowChef.setVisible(true);
            break;
            
        
       	}
        

	}
	
        
	public static Object[] concatObjectArrays2( Object[] vect1) {
		Object[] fusionArray = new Object[ vect1.length ];
		for (int i = 0; i < vect1.length; i++) {
			if ( i < vect1.length ) 
				fusionArray[i] = vect1[i];			
		}
		return fusionArray;
	}
	
	
	public Object[] toObjectVector(int numero, String cliente, String atendido , long Tiempo) {
		return new Object[] {numero,cliente,atendido,Tiempo};
	    }

	public Object[] toObjectVector(int numero, String cocinero, String estado ) {
		return new Object[] {numero,cocinero,estado};
	    }

}