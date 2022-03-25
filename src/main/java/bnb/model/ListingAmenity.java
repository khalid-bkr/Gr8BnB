package bnb.model;

public class ListingAmenity {
	protected int id;
	protected Listing listing;
	protected Amenity amenity;


	public ListingAmenity(int id, Listing listing, Amenity amenity) {
		this.id = id;
		this.listing = listing;
		this.amenity = amenity;
	}


	public ListingAmenity(Listing listing, Amenity amenity) {
		this.listing = listing;
		this.amenity = amenity;
	}


	public ListingAmenity(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Listing getListing() {
		return listing;
	}

	public void setListing(Listing listing) {
		this.listing = listing;
	}

	public Amenity getAmenity() {
		return amenity;
	}

	public void setAmenity(Amenity amenity) {
		this.amenity = amenity;
	}
}
