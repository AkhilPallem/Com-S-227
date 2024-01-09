package hw1;

public class CameraBattery {

	/*
	 * The three capital letter are constant variables that never change they
	 * represent the number of charger settings, the charge rate, and the camera
	 * power consumption
	 */
	public static final int NUM_CHARGER_SETTINGS = 4;
	public static final double CHARGE_RATE = 2.0;
	public static final double DEFAULT_CAMERA_POWER_CONSUMPTION = 1.0;

	// battery_Charge represents the batteries charge
	private double battery_Charge;

	// battery_Capacity represents the total batteries capacity
	private double battery_Capacity;

	// The chargerSetting represents the number of which the charger setting is on
	private int chargerSetting;

	// connected represents if the battery is connected or not. 1 for connected and
	// 0 for not connected
	private int connected;

	// totalDrain represents the totalDrain of the battery
	private double totalDrain;

	// cameraPower represents how much power the camera has
	private double cameraPower;

	// camera_charge represents the cameras charge starting at 0
	private double camera_charge = 0;

	public CameraBattery(double batteryStartingCharge, double batteryCapacity) {
		battery_Charge = Math.min(batteryStartingCharge, batteryCapacity);
		totalDrain = 0;
		cameraPower = DEFAULT_CAMERA_POWER_CONSUMPTION;
		battery_Capacity = batteryCapacity;
		chargerSetting = 0;
		connected = 0;
	}

	/*
	 * increments the charger setting. If already at max setting it goes back to
	 * zero
	 */
	public void buttonPress() {
		chargerSetting = (chargerSetting + 1) % NUM_CHARGER_SETTINGS;
	}

	/*
	 * charges the battery connected to the camera for the time given. Charging is
	 * not supposed to increase the capacity
	 */
	public double cameraCharge(double minutes) {
		double charge = CHARGE_RATE * minutes * connected;
		double chargeMin = Math.min(charge, battery_Capacity - battery_Charge);
		battery_Charge = Math.min((battery_Charge + chargeMin), battery_Capacity);
		camera_charge = battery_Charge * connected;
		return chargeMin;
	}

	/*
	 * Drains the battery connected to the camera for the given time. This cannot
	 * exceed the amount of charge contained in the battery Assuming its connected
	 * to the battery
	 */
	public double drain(double minutes) {
		double drain = cameraPower * minutes * connected;
		double drainMin = Math.min(drain, battery_Charge);
		battery_Charge = Math.max(battery_Charge - drainMin, 0);
		camera_charge = battery_Charge * connected;
		totalDrain += drainMin;
		return drainMin;

	}

	/*
	 * Charges battery connected to the external charger for the given time. Cannot
	 * exceed the capacity once again
	 */
	public double externalCharge(double minutes) {
		double charge = CHARGE_RATE * chargerSetting * minutes * connected;
		double charging = Math.min(charge, battery_Capacity - battery_Charge);
		battery_Charge = Math.min(battery_Charge + charging, battery_Capacity);
		return charging;
	}

	/*
	 * Resets the battery monitor system
	 */
	public void resetBatteryMonitor() {
		totalDrain = 0;
	}

	/*
	 * gets battery capacity
	 */
	public double getBatteryCapacity() {
		return battery_Capacity;

	}

	/*
	 * battery current charge
	 */
	public double getBatteryCharge() {
		return battery_Charge;
	}

	/*
	 * gets the charge of the camera battery
	 */
	public double getCameraCharge() {
		return camera_charge;
	}

	/*
	 * gets power consumption of camera
	 */
	public double getCameraPowerConsumption() {
		return cameraPower;
	}

	/*
	 * get the external charging setting
	 */
	public int getChargerSetting() {
		return chargerSetting;
	}

	/*
	 * gets the amount total drained
	 */
	public double getTotalDrain() {
		return totalDrain;
	}

	/*
	 * Move the battery to the camera to the external as needed to represent the
	 * move
	 */
	public void moveBatteryExternal() {
		connected = 1;

	}

	/*
	 * moves the battery
	 */
	public void moveBatteryCamera() {
		connected = 1;

	}

	/*
	 * removes battery from camera
	 */
	public void removeBattery() {
		connected = 0;

	}

	/*
	 * sets the power consumption of the camera
	 */
	public void setCameraPowerConsumption(double cameraPowerConsumption) {
		this.cameraPower = cameraPowerConsumption;
	}

}