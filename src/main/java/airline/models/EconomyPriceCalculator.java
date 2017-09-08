package airline.models;

public class EconomyPriceCalculator {
    private float basePrice;

    public EconomyPriceCalculator(float basePrice) {
        this.basePrice = basePrice;
    }

    public float getBookingPrice(int passengersCount) {
        return basePrice * passengersCount;
    }
}
