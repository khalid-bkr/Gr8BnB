package bnb.model;

import java.math.BigDecimal;
import java.sql.Date;

/**
 * @author Maidi Wang
 *
 */
public class Calendar {
	private int id;
	// TODO: Uncomment this once Listing is defined
	 private Listing listing;
	private Date date;
	private boolean available;
	private BigDecimal price;
	private BigDecimal adjustedPrice;
	private int minimumNights;
	private int maximumNights;
	
	
	public Calendar(int id, Listing listing, Date date, boolean available, BigDecimal price, BigDecimal adjustedPrice,
			int minimumNights, int maximumNights) {
		super();
		this.id = id;
		this.listing = listing;
		this.date = date;
		this.available = available;
		this.price = price;
		this.adjustedPrice = adjustedPrice;
		this.minimumNights = minimumNights;
		this.maximumNights = maximumNights;
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


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public boolean isAvailable() {
		return available;
	}


	public void setAvailable(boolean available) {
		this.available = available;
	}


	public BigDecimal getPrice() {
		return price;
	}


	public void setPrice(BigDecimal price) {
		this.price = price;
	}


	public BigDecimal getAdjustedPrice() {
		return adjustedPrice;
	}


	public void setAdjustedPrice(BigDecimal adjustedPrice) {
		this.adjustedPrice = adjustedPrice;
	}


	public int getMinimumNights() {
		return minimumNights;
	}


	public void setMinimumNights(int minimumNights) {
		this.minimumNights = minimumNights;
	}


	public int getMaximumNights() {
		return maximumNights;
	}


	public void setMaximumNights(int maximumNights) {
		this.maximumNights = maximumNights;
	}


	@Override
	public String toString() {
		return "Calendar [id=" + id + ", listing=" + listing + ", date=" + date + ", available=" + available
				+ ", price=" + price + ", adjustedPrice=" + adjustedPrice + ", minimumNights=" + minimumNights
				+ ", maximumNights=" + maximumNights + "]";
	}

}
