package blackjack;

import java.util.ArrayList;

public class Hand {
	private ArrayList<Card> cards;

	/**
	 * Creates an empty hand.
	 */
	public Hand() {
		cards = new ArrayList<Card>();
	}

	/**
	 * Adds a card to the hand.
	 * 
	 * @param c
	 *            card to be added
	 */
	public void add(Card c) {
		cards.add(c);
	}

	/**
	 * Removes a specific card from the hand.
	 * 
	 * @param c
	 *            card to be removed
	 * @return true if the card is removed.
	 */
	public boolean remove(Card c) {
		return cards.remove(c);
	}

	/**
	 * Deletes all cards from the hand.
	 */
	public void clear() {
		cards.clear();
	}

	/**
	 * Converts the hand into a Deck.
	 * 
	 * @return the hand in the form of a Deck
	 */
	public Deck toDeck() {
		Deck temp = new Deck();
		for (Card c : cards) {
			temp.addToTop(c);
		}
		return temp;
	}

	public Card cardAt(int index) {
		return cards.get(index);
	}

	/**
	 * 
	 */
	public int getValue() {
		int counter = 0;
		for (Card c : cards) {
			counter += c.getValue();
		}
		if (counter > 21) { // if busted, try to make aces 1 instead of 11
			counter = 0;
			for (Card c : cards) {
				if (c.getValue() != 11) {
					counter += c.getValue();
				} else {
					counter += 1;
				}
			}
		}
		return counter;
	}

	/**
	 * @return true if busted
	 */
	public boolean checkBust() {
		if (this.getValue() <= 21) {
			return false;
		} else {
			return true;
		}
	}
}
