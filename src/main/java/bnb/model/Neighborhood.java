package bnb.model;

public class Neighborhood {
    protected String neighborhood;
    protected String neighborhoodGroup;

    public Neighborhood(String neighborhood, String neighborhoodGroup) {
        this.neighborhood = neighborhood;
        this.neighborhoodGroup = neighborhoodGroup;
    }

    public Neighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getNeighborhoodGroup() {
        return neighborhoodGroup;
    }

    public void setNeighborhoodGroup(String neighborhoodGroup) {
        this.neighborhoodGroup = neighborhoodGroup;
    }
}
