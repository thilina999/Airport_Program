import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class PassengerQueue {

	int first;
	int last;
	private final int maxSize = 20;
	static int i = 0;
    Passenger[] passenger = new Passenger[maxSize];
    Passenger p;
	static int pLength = 1;
    static int x = 0;
    static int passengNum = 0;
    ArrayList<Passenger> passengerQueue;
    
    public void add(Passenger ps) {
        if (passengNum < passenger.length) {
            passenger[passengNum++] = ps;
        } else {
            System.out.println("list is full");
        }

    }	
    public boolean validNum(int num) {
        PassengerQueue passengerq = new PassengerQueue();
        if (num >= 0 & num < passengerq.maxSize) {
            return true;
        }
        return false;
    }
	public void remove(int num){

        if (validNum(num)) {
            if (passenger[num] != null) {
                passenger[num] = null;
            } else {
                System.err.println(" Empty");
            }
        } else {
            System.err.println("Invalid number");
        }
    
	}

	public void display(){

        int count = 1;
        for (int i = 0; i < passenger.length; i++) {
            if (passenger[i] != null) {
                p = passenger[i];
                
                System.out.println("Passenger No: " + count +"\n Name: "+ p.getFirstName() +" "+ p.getSurname()+
                		"\n Seconds in Queue : "+ p.getSecondsInQueue());
                
                System.out.println("\n");
                count++;
            }

        }

    
	}
	public int getMaxSize(){

        int i = 0;
        int count = 0;
        while (passenger[i] != null) {
            count++;
            i++;
        }
        return count;
    
	}
	
	public boolean isEmpty(){

        for (int i = 0; i < passenger.length; i++) {
            if (passenger[i] == null) {
                return true;
            }

        }
        return false;
    
	}

	public boolean isFull(){

        int count = 0;
        for (int i = 0; i < passenger.length; i++) {
            if (passenger[i] != null) {
                count++;

            }
        }
        if (count == maxSize) {
            return false;
        }
        return true;
    
    }


	public void storeInfo(){

        try {
            File file = new File("passenger.txt");
            file.createNewFile();
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);

            for (int x = 0; x < passenger.length; x++) {
                if (passenger[x] != null) {
                    bw.write(  passenger[x].getFirstName() +"%"+ passenger[x].getSurname() 
                    		+"%"+ passenger[x].getSecondsInQueue());
                    bw.newLine();
                }
            }
            bw.flush();
            bw.newLine();
            fw.close();
            System.out.println("Saved information to text file");

        } catch (IOException e) {
            System.err.println(e);
        }

    
	}
	
	public void load(){

        BufferedReader br = null;
        String[] stringLines;
        Passenger psngr = null;
        List<String> list;
        String line;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream("passenger.txt")));

            while ((line = br.readLine()) != null) {
                stringLines = new String[3];
                String[] data = line.split("%");
                String fname = data[0];
                String lname = data[1];
                int q = Integer.parseInt(data[2]);
                psngr = new Passenger(fname, lname, q);
                if(i<passenger.length){
                passenger[i++] = psngr;
                }else{
                    i=0;
                }
              }
          } catch (FileNotFoundException ex) {
         } catch (IOException ex) {
        }
    
        System.out.println("Load - successfull ");
	}		
		int count = 0;
	    static ArrayList<Passenger> simulation;

	public void runSimulation() {
        removeFile();
        simulation = new ArrayList<>();
        ArrayList<String> nameList =loadIntoArray();
        while (!nameList.isEmpty()) {

            int count = passengerCount();
            int size = nameList.size();

            if (size >= count) {
                ArrayList<String> joiningNameList = joinList(count, nameList);
                passengerQueue = loadPassengers(joiningNameList, simulation);
                makeReport(passengerQueue);
            }
        
        }
        reportCont();
        simulation = null;
    }

   private ArrayList<String> loadIntoArray() {
       BufferedReader br = null;
       ArrayList<String> namelist = new ArrayList<>();

       String line;
       try {
           br = new BufferedReader(new InputStreamReader(new FileInputStream("passengers.dat")));

           while ((line = br.readLine()) != null) {
               namelist.add(line);
           }
 
       } catch (FileNotFoundException e) {
 
       } catch (IOException e) {

       }
       return namelist;
   }
   private int passengerCount() {

       int diceNum = 1 + (int) (Math.random() * 6);
       return diceNum;
   }
   static int c = 0;


   private ArrayList<String> joinList(int count, ArrayList<String> namelist) {
       ArrayList<String> jList = new ArrayList<>();

       for (int i = 0; i < count; i++) {
           if (i < namelist.size()) {
               String name = namelist.get(i);
               jList.add(name);
               namelist.remove(i);
           }

       }

       return jList;
   }
    public int delay() {
       int d1 = 1 + (int) (Math.random() * 6);
       int d2 = 1 + (int) (Math.random() * 6);
       int d3 = 1 + (int) (Math.random() * 6);

       return d1 + d2 + d3;
   }
  

   public ArrayList<Passenger> loadPassengers(ArrayList<String> joiningNameList, ArrayList<Passenger> simulation) {
       String[] names;
       ArrayList<Passenger> simulationArray = new ArrayList<>();
       for (int i = 0; i < joiningNameList.size(); i++) {

           String namefromlist = joiningNameList.get(i);
           names = namefromlist.split(" ");
           String fName = names[0];
           String lname = names[1];
           int delaytime = delay();
          
           Passenger pessenger = new Passenger(fName, lname, delaytime);
           simulationArray.add(pessenger);
           simulation.add(pessenger);
       }

       return simulationArray;
   }
   private void removeFile() {
       FileWriter fw = null;
       try {
           File file = new File("report.dat");
           file.delete();
           file.createNewFile();
           fw = new FileWriter(file, true);
           BufferedWriter bw = new BufferedWriter(fw);
           bw.write("   Report\n");
           bw.flush();
           bw.newLine();
           fw.close();
       } catch (IOException ex) {
           Logger.getLogger(PassengerQueue.class.getName()).log(Level.SEVERE, null, ex);
       }

   }
   public void makeReport(ArrayList<Passenger> passengerQueue) {
       try {
           File file = new File("report.dat");

           FileWriter fw = new FileWriter(file, true);

           BufferedWriter bw = new BufferedWriter(fw);

           for (int x = 0; x < passengerQueue.size(); x++) {
               p = passengerQueue.get(x);

               bw.write("First Name : " + p.getFirstName() + "\t|Last Name : " +
               p.getSurname() + "\t|Delay Time : " + p.getSecondsInQueue());

               bw.newLine();
           }

           bw.flush();
           bw.newLine();
           fw.close();

       } catch (IOException ex) {
           System.err.println(ex);
       }
   }

   private void reportCont() {
       int max, min;
       double average = 0;
       int count = 0;
       int total = 0;
       Passenger p;
       max = simulation.get(0).getSecondsInQueue();
       min = simulation.get(0).getSecondsInQueue();
       for (int i = 0; i < simulation.size(); i++) {
           count++;
           p = simulation.get(i);
           total += p.getSecondsInQueue();

           if (max < p.getSecondsInQueue()) {
               max = p.getSecondsInQueue();
           }
           if (min > p.getSecondsInQueue()) {
               min = p.getSecondsInQueue();
           }
       }
       average = total / count;

       try {
           File file = new File("report.dat");

           FileWriter fw = new FileWriter(file, true);

           BufferedWriter bw = new BufferedWriter(fw);

           for (int x = 0; x < passengerQueue.size(); x++) {
               p = passengerQueue.get(x);

               bw.write("\n\nMaximum waiting time is : " + max + "\n\n"+ 
            		   "Minimum waiting time is : " + min + 
            		   "\n\n Average waiting time is : " + average+
            		   "\n\nThe maximum length of the queue attained : "+count);

               bw.newLine();
           }

           bw.flush();
           bw.newLine();
           fw.close();

       } catch (IOException ex) {
           System.err.println(ex);
       }
   }

}
