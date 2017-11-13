import java.util.*;

/**
 * 
 */

/**
 * @author 
 *
 */
public class Airport {
	private static final Passenger Passenger = null;
	private static final PassengerQueue PassengerQueue = null;
	public static Scanner sc = new Scanner(System.in);
    static PassengerQueue passengerqueue = new PassengerQueue();
    static Passenger passenger = new Passenger();

	
	public static void main(String[] args){
		
		System.out.println();
		System.out.println("           Airport Program            ");
		System.out.println("====================================");

		mainPage();

        selections(passengerqueue, passenger);
	
	}
	
	public static void mainPage() {
		System.out.println();
		System.out.println("                MENU              ");
		System.out.println("....................................");
		System.out.println("A : Add a passenger to the passengerQueue");
		System.out.println("V : View the passengerQueue");
		System.out.println("D : Delete passenger from passengerQueue");
		System.out.println("S : Store passengerArray data into a plain text file");
		System.out.println("L : Load passenger data back from the file ");
		System.out.println("R : Run the simulation and produce report");
		System.out.println("Q : Quit ");
		
	}
		public static void selections(PassengerQueue pq, Passenger p) {
			
		    System.out.println();
			System.out.print("Enter your selections: ");
			String selection = sc.next().toLowerCase();
			System.out.println();
			
			if (selection.equals("v")) {
				pq.display();
				mainPage();

		        selections(passengerqueue, passenger);
		        
			} else if (selection.equals("a")) {
                if (pq.isFull()) {
                    System.out.print("Please Enter the First name : ");
                    String fname = sc.next();
                    System.out.print("Please Enter the Last name : ");
                    String lname = sc.next();
                   int  squeue = passengerqueue.delay();
                    Passenger ps = new Passenger(fname, lname, squeue);
                    pq.add(ps);                 
                } else {
                    System.err.println("List is full");
                }
                selections(passengerqueue, passenger);
                
			} else if (selection.equals("d")) {
                System.out.print("Enter passenger number : ");
                int num = sc.nextInt();
                pq.remove(num);
        		mainPage();
                selections(passengerqueue, passenger);
                
			} else if (selection.equals("s")) {
				pq.storeInfo();
				mainPage();
		        selections(passengerqueue, passenger);
		        
			} else if (selection.equals("l")) {
				pq.load();
				mainPage();
		        selections(passengerqueue, passenger);
		        
			} else if (selection.equals("r")) {
				System.out.print("Run the simulation and produce report - successfull");
				pq.runSimulation();
				mainPage();
		        selections(passengerqueue, passenger);
		        
			} else if (selection.equals("q")) {
				System.out.println("               ThankYou!               ");
				System.exit(0);
			} else {
				System.out.println("Invaild Input, Check The Main Menu");
				mainPage();
				selections(PassengerQueue , Passenger);
			}
		}

}
