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
	// private Listing listingId;
	private Date date;
	private boolean available;
	private BigDecimal price;
	private BigDecimal adjustedPrice;
	private int minimumNights;
	private int maximumNights;

	// TODO: Regenerate constructor once Listing is defined
	public Calendar(int id, Date date, boolean available, BigDecimal price, BigDecimal adjustedPrice, int minimumNights,
			int maximumNights) {
		super();
		this.id = id;
		this.date = date;
		this.available = available;
		this.price = price;
		this.adjustedPrice = adjustedPrice;
		this.minimumNights = minimumNights;
		this.maximumNights = maximumNights;
	}

	// TODO: Regenerate getter/setter after Listing is defined
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	// TODO: Regenerate toString once Listing is defined
	@Override
	public String toString() {
		return "Calendar [id=" + id + ", date=" + date + ", available=" + available + ", price=" + price
				+ ", adjustedPrice=" + adjustedPrice + ", minimumNights=" + minimumNights + ", maximumNights="
				+ maximumNights + "]";
	}

}
