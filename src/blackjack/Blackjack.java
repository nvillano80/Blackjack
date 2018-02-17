package blackjack;

import java.util.Random;

/**
 * A simple text based heads up Blackjack game. The player's goal is to score
 * higher than the dealer without going over 21. The player places any initial
 * bet, then two cards are dealt to each the player and the dealer (the player
 * can only see one of the dealer's cards). The player may only hit or stay. The
 * dealer stands of soft 17. Wins pay 2:1 and blackjack pays 3:2.
 * 
 * @author Nick Villano
 *
 */
public class Blackjack {
	private final int numDecks;
	private Deck deck;
	private Player player;
	private Dealer dealer;
	private int bettingSpot;
	private Random rand = new Random();
	private double targetPenetration;

	private Deck discard;

	public static void main(String[] args) {
		Blackjack game = new Blackjack(6, 100);
		game.play();
	}

	public Blackjack(int inNumDecks, int startingBalance) {
		numDecks = inNumDecks;
		setDeck(new Deck(inNumDecks, false));
		dealer = new Dealer(true);
		player = new Player(startingBalance);
		discard = new Deck();
	}

	public void play() {
		boolean playAgain = true;
		while (playAgain) {
			setupRound();
			playRound();
			playAgain = player.askPlayAgain();
		}
	}

	public void playRound() {
		System.out.println("Player Balance: " + player.getBalance());
		getPlayerBet();
		System.out.println("Player Balance after bet: " + player.getBalance());
		dealCards();
		playerTurn();
		dealerTurn();
		payout();
	}

	private void setupRound() {
		// if starting off with a full deck
		if (((double) deck.getSize()) / 52 == numDecks) {
			targetPenetration = ((4 + rand.nextDouble()) * (((double) numDecks) / 6));
			deck.shuffle();
		} else { // if the deck has been used
			// put the previous hands into the discard pile
			discard.addToTop(player.clearHand());
			discard.addToTop(dealer.clearHand());
			// if the target penetration has been reached
			if (((double) discard.getSize()) / 52 >= targetPenetration) {
				deck.addToTop(discard.clear()); // merge the discard pile into
												// the deck
				deck.shuffle(); // shuffle the now full deck
			}
		}
	}

	private void getPlayerBet() {
		int bet = player.decideBet();
		player.withdraw(bet);
		setBettingSpot(bet);
	}

	private void dealCards() {
		player.receive(deck.dealCard());
		player.receive(deck.dealCard());
		dealer.receive(deck.dealCard());
		dealer.receive(deck.dealCard());
	}

	/**
	 * 
	 */
	private void playerTurn() {
		System.out.println("Dealer Showing: " + dealer.getShowing().getValue());
		boolean playing = true;
		boolean busted = false;
		while (playing && !busted) {
			System.out.println("Player Hand: " + player.getHand().getValue());
			//hit, split, double
			String decision = player.makeMove();
			if (decision.equals("hit")) {
				player.receive(deck.dealCard());
				System.out.println("Hit");
				System.out.println("Player Hand: " + player.getHand().getValue());
			} else if (decision.equals("split")) {
				
			} else {
				playing = false;
			}
			busted = player.getHand().checkBust();
		}
		if (busted) {
			System.out.println("Player Busted!");
		}
		System.out.println("Player Hand: " + player.getHand().getValue());
	}

	private void dealerTurn() {
		if (player.getHand().getValue() < 22) {
			boolean hitting = true;
			boolean busted = false;
			while (hitting && !busted) {
				hitting = dealer.decideHit();
				if (hitting) {
					dealer.receive(deck.dealCard());
					System.out.println("Dealer Hits\nDealer Hand: " + dealer.getHand().getValue());
				}
				busted = dealer.getHand().checkBust();
			}
			if (busted) {
				System.out.println("Dealer Busted!");
			} else {
				System.out.println("Dealer Hand: " + dealer.getHand().getValue());
			}
		}
	}

	private void payout() {
		if (player.getHand().checkBust()) {
			playerLost();
		} else if (player.getHand().toDeck().getSize() == 2 && player.getHand().getValue() == 21
				&& dealer.getHand().getValue() != 21) {
			playerBlackjack();
		} else if (dealer.getHand().checkBust()) {
			playerWon();
		} else if (player.getHand().getValue() > dealer.getHand().getValue()) {
			playerWon();
		} else if (player.getHand().getValue() < dealer.getHand().getValue()) {
			playerLost();
		} else {
			tie();
		}
		System.out.println("Current Balance: " + player.getBalance());
	}

	/**
	 * 
	 */
	private void playerBlackjack() {
		double payout = ((double) bettingSpot) * (3 / 2);
		player.deposit((int) payout, (int) (payout * 100 % 100));
	}

	private void playerLost() {
		System.out.println("You Lost.");
		dealer.changeBalance(bettingSpot);
		bettingSpot = 0;
	}

	private void playerWon() {
		System.out.println("You Won!");
		player.deposit(2 * bettingSpot, 0);
		dealer.changeBalance(-bettingSpot);
		bettingSpot = 0;
	}

	private void tie() {
		System.out.println("Tie");
		player.deposit(bettingSpot, 0);
		bettingSpot = 0;
	}

	/**
	 * @return the number of decks being used in the game
	 */
	public int getNumDecks() {
		return numDecks;
	}

	/**
	 * @return the deck
	 */
	public Deck getDeck() {
		return deck;
	}

	/**
	 * @param deck
	 *            the deck to set
	 */
	private void setDeck(Deck deck) {

		this.deck = deck;
	}

	/**
	 * @return the bettingSpot
	 */
	public int getBettingSpot() {
		return bettingSpot;
	}

	/**
	 * @param bettingSpot
	 *            the bettingSpot to set
	 */
	private void setBettingSpot(int bettingSpot) {
		this.bettingSpot = bettingSpot;
	}
}