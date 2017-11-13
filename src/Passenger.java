
public class Passenger {
	String firstName;
	String surname;
	int secondsInQueue;

    public Passenger() {
    }

    public Passenger(String firstName, String surname, int secondsInQueue) {
        this.firstName = firstName;
        this.surname = surname;
        this.secondsInQueue = secondsInQueue;
    }
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public int getSecondsInQueue() {
		return secondsInQueue;
	}
	public void setSecondsInQueue(int secondsInQueue) {
		this.secondsInQueue = secondsInQueue;
	}
}
