package airline.models;

public abstract class FareCalculator {
    abstract public float getPricePerHead(Flight flight, SearchCriteria searchCriteria);
}