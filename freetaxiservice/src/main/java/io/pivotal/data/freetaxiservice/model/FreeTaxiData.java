/**
 * 
 */
package io.pivotal.data.freetaxiservice.model;

/**
 * @author shuklk2
 *
 */
public class FreeTaxiData {

	private String latitude;
	
	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getDriver_num() {
		return driver_num;
	}

	public void setDriver_num(String driver_num) {
		this.driver_num = driver_num;
	}

	private String longitude;
	
	private String driver_num;
	
}
