package airline.models;

public class TravelClassInfo {
    private int totalNoOfSeats;
    private int availableSeats;
    private float price;

    public TravelClassInfo(int totalNoOfSeats, int availableSeats, float price) {
        this.totalNoOfSeats = totalNoOfSeats;
        this.availableSeats = availableSeats;
        this.price = price;
    }

    public int getTotalNoOfSeats() {
        return totalNoOfSeats;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public float getPrice() {
        return price;
    }
}
