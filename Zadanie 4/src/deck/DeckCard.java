package deck;

public class DeckCard {

	private String name;
	private int[] cardValue;

	public DeckCard(String name, int firstValue, int secondValue) {
		this.name = name;
		cardValue = new int[2];
		cardValue[0] = firstValue;
		cardValue[1] = secondValue;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public boolean checkIfAs() {
		if (name.contains("as")) {
			return true;
		} else {
			return false;
		}
	}
	
	public int getValueIfAs() {
		if (name.contains("as")) {
			return cardValue[1];
		} else {
			return cardValue[0];
		}
	}
	
	public void setFirstValue(int value) {
		cardValue[0] = value;
	}

	public int getFirstValue() {
		if (cardValue != null)
			return cardValue[0];
		else
			return 0;
	}
	
	public int getSecondValue() {
		if (cardValue != null)
			return cardValue[1];
		else
			return 0;
	}

}
