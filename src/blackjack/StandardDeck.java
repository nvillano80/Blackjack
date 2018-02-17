package blackjack;

public class StandardDeck extends Deck {
	public StandardDeck() {
		super();
		for (int i = 0; i < 4; i++) { // add aces
			super.addToTop(new Card(11));
		}
		for (int i = 2; i <= 10; i++) { // create and add cards 2-10
			for (int j = 0; j < 4; j++) {
				super.addToTop(new Card(i));
			}
		}
		for (int i = 0; i < 12; i++) { // add face cards
			super.addToTop(new Card(10));
		}
	}
}
