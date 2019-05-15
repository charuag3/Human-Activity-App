package a00267320.ait.com.androidacc;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;


public class SensorData {
	private Long id;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss.SSS")
	private Date createTime;
	private Double xCoor;
	private Double yCoor;
	private Double zCoor;
	private Double xAxis;
	private Double yAxis;
	private Double zAxis;

	public SensorData() {
		this.xAxis=0d;
		this.yAxis=0d;
		this.zAxis=0d;
		this.xCoor=0d;
		this.yCoor=0d;
		this.zCoor=0d;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Double getxCoor() {
		return xCoor;
	}
	public void setxCoor(Double xCoor) {
		if(xCoor!=null)
		this.xCoor = xCoor;
	}
	public Double getyCoor() {
		return yCoor;
	}
	public void setyCoor(Double yCoor) {
		if(yCoor!=null)
		this.yCoor = yCoor;
	}
	public Double getzCoor() {
		return zCoor;
	}
	public void setzCoor(Double zCoor) {
		if(zCoor!=null)
		this.zCoor = zCoor;
	}
	public Double getxAxis() {
		return xAxis;
	}
	public void setxAxis(Double xAxis) {
		if(xAxis!=null)
		this.xAxis = xAxis;
	}
	public Double getyAxis() {
		return yAxis;
	}
	public void setyAxis(Double yAxis) {
		if(yAxis!=null)
		this.yAxis = yAxis;
	}
	public Double getzAxis() {
		return zAxis;
	}
	public void setzAxis(Double zAxis) {
		if(zAxis!=null)
		this.zAxis = zAxis;
	}
	@Override
	public String toString() {
		return "SensorData [id=" + id + ", createTime=" + createTime + ", xCoor=" + xCoor + ", yCoor=" + yCoor
				+ ", zCoor=" + zCoor + ", xAxis=" + xAxis + ", yAxis=" + yAxis + ", zAxis=" + zAxis + "]";
	}
}
