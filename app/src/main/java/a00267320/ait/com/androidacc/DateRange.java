package a00267320.ait.com.androidacc;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class DateRange {
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss.SSS")
	private Date minDate;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss.SSS")
	private Date maxDate;
	public Date getMinDate() {
		return minDate;
	}
	public void setMinDate(Date minDate) {
		this.minDate = minDate;
	}
	public Date getMaxDate() {
		return maxDate;
	}
	public void setMaxDate(Date maxDate) {
		this.maxDate = maxDate;
	}
	@Override
	public String toString() {
		return "DateRange [minDate=" + minDate + ", maxDate=" + maxDate + "]";
	}
}