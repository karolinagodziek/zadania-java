package core;

public class CoreOfGame {

	private static CoreOfGame instance;
	private double cash;
        
	private CoreOfGame() {
		
	}
	
	public static CoreOfGame getInstance() {
		if (instance == null)
			instance = new CoreOfGame();
		return instance;
	}
	
	public void substractCash(double cashToSub) {
		cash -= cashToSub;
	}
	
	public void addCash(double cashToAdd) {
		cash += cashToAdd;
	}
	
	public void setCash(double money) {
		this.cash = money;
	}
	
	public double getCash() {
		return cash;
	}
	
}
