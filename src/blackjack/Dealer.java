package blackjack;

public class Dealer {
	private Hand hand;
	private final boolean standSoft17;
	private int balance;

	/**
	 * Creates a new dealer.
	 * 
	 * @param inStandSoft17
	 *            True if the dealer hits on soft 17. False if the dealer stands
	 *            on soft 17.
	 */
	public Dealer(boolean inStandSoft17) {
		standSoft17 = inStandSoft17;
		hand = new Hand();
	}

	/**
	 * Adds a card to the dealer's hand.
	 * 
	 * @param card
	 *            to add
	 */
	public void receive(Card card) {
		hand.add(card);
	}

	/**
	 * @return if the dealer stands on soft 17.
	 */
	public boolean isStandSoft17() {
		return standSoft17;
	}

	public Card getShowing() {
		return hand.cardAt(0);
	}

	/**
	 * @return true if the deal is hitting
	 */
	public boolean decideHit() {
		int handValue = hand.getValue();
		if (handValue < 17 || (handValue == 17 && !standSoft17)) {
			return true;
		}
		return false;
	}

	public Hand getHand() {
		return hand;
	}

	/**
	 * @return the balance
	 */
	public int getBalance() {
		return balance;
	}

	/**
	 * @param balance
	 *            the balance to set
	 */
	public void setBalance(int balance) {
		this.balance = balance;
	}

	/**
	 * Changes the balance by a certain amount.
	 * 
	 * @param by
	 *            the amount to change by. Positive will result in an increase.
	 *            Negative will result in a decrease.
	 */
	public void changeBalance(int by) {
		this.balance += by;
	}
	
	public Deck clearHand() {
		Deck temp = hand.toDeck();
		hand.clear();
		return temp;
	}
}
