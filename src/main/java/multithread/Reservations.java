package multithread;

public class Reservations {
    public static int seats;

    public Reservations(int seats){
        Reservations.seats = seats;
    }

    public static int freeSeats(){
        return seats;
    }
}
