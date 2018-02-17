package blackjack;

import java.util.ArrayList;
import java.util.Random;

public class Deck {
	private Random rand = new Random();
	private ArrayList<Card> cards;

	/**
	 * Creates an empty Deck of cards.
	 */
	public Deck() {
		/**
		 * The cards are ordered from index 0 being the top.
		 */
		cards = new ArrayList<Card>();
	}

	/**
	 * Creates a deck with cards in it.
	 * 
	 * @param numDecks
	 *            the number of standard 52 playing card decks to include in the
	 *            created deck.
	 * @param shuffled
	 *            whether the created deck is shuffled or not
	 */
	public Deck(int numDecks, boolean shuffled) {
		cards = new ArrayList<>();
		for (int i = 0; i < numDecks; i++) {
			addToTop(new StandardDeck());
		}
		if (shuffled) {
			this.shuffle();
		}
	}

	/**
	 * Adds a card to the top of the deck (index=0).
	 * 
	 * @param card
	 *            the card to be added
	 */
	public void addToTop(Card card) {
		cards.add(card);
	}

	/**
	 * Adds a card to the bottom of the deck.
	 * 
	 * @param card
	 *            the card to be added
	 */
	public void addToBottom(Card card) {
		cards.add(card);
	}

	/**
	 * Removes a card from a specific position in the deck.
	 * 
	 * @param at
	 *            the index from which the card is to be removed
	 * @return the removed Card object.
	 */
	public Card removeCard(int at) {
		return (Card) cards.remove(at);
	}

	/**
	 * Removes a card from the top of the deck to be dealt to another player.
	 * 
	 * @return the dealt card
	 */
	public Card dealCard() {
		return removeCard(0);
	}

	/**
	 * Adds a preexisting deck to the top of the deck.
	 */
	public void addToTop(Deck d) {
		for (int i = 0; i < d.cards.size(); i++) {
			cards.add(0,d.cards.get(i));
		}
	}

	/**
	 * Adds a preexisting deck to the bottom of the deck.
	 */
	public void addToBottom(Deck d) {

	}

	/**
	 * Randomizes the order of the cards in the deck.
	 */
	public void shuffle() {
		int size = cards.size();
		ArrayList<Card> temp = new ArrayList<>(size);
		for (int i = 0; i < size; i++) {
			temp.add(cards.get(rand.nextInt(cards.size())));
		}
		cards = temp;
		System.out.println("Shuffling...");
	}

	/**
	 * 
	 */
	public String toString() {

		String txt = "";
		for (int i = 0; i < cards.size(); i++) {
			txt = txt + "\n" + cards.get(i).getValue();
		}
		return txt;
	}

	public int getSize() {
		return cards.size();

	}
	
	public Deck clear() {
		Deck temp = this;
		cards.clear();
		return temp;
	}
}
