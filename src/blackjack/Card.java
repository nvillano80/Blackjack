package blackjack;

public class Card {
	private int value;

	public Card(int inValue) {
		value = inValue;
	}

	/**
	 * @return the integer value of the card (face cards as 10, aces as 11)
	 */
	public int getValue() {
		return value;
	}
}
