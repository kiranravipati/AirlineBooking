package airline.model;

public class SearchCriteria {
    private String source;
    private String destination;

    SearchCriteria(String source, String destination) {
        this.destination = destination;
        this.source = source;
    }

}
