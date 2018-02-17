package blackjack;

import java.util.Scanner;

public class Player {
	private int dollars;
	private int cents;
	private Hand hand;
	private Scanner sc;

	/**
	 * Creates a new player with an empty hand and a balance of $0.
	 */
	public Player() {
		dollars = 0;
		cents = 0;
		hand = new Hand();
		sc = new Scanner(System.in);
	}

	/**
	 * Creates a new player with an empty hand and a balance determined by the
	 * parameters.
	 * 
	 * @param setBalance
	 *            the player's starting balance
	 */
	public Player(int setBalance) {
		dollars = setBalance;
		hand = new Hand();
		sc = new Scanner(System.in);
	}

	/**
	 * Increases the player's balance by a certain amount of dollars and cents
	 * then consolidates cents into dollars if possible.
	 * 
	 * @param dollars
	 *            The number of dollars to increase the blance by
	 * @param cents
	 *            The number of cents to increase the blance by
	 */
	public void deposit(int dollars, int cents) {
		this.dollars += dollars;
		this.cents += cents;
		if (cents >= 100) {
			dollars = cents / 100;
			cents = cents % 100;
		}
	}

	/**
	 * Decreases the player's balance by a certain amount.
	 * 
	 * @param dollars
	 *            The number of dollars to decrease the balance by
	 */
	public void withdraw(int dollars) {
		this.dollars -= dollars;
	}

	/**
	 * Adds a card to the player's hand.
	 * 
	 * @param card
	 *            to add
	 */
	public void receive(Card card) {
		hand.add(card);
	}

	/**
	 * Asks the player to input their bet (an integer value) through the console
	 * and ensures a valid response is received.
	 * 
	 * @return the valid bet
	 */
	public int decideBet() {
		System.out.print("Enter bet: ");
		String input = sc.next();
		int bet;
		// how do you code this without a try catch?
		try {
			bet = Integer.parseInt(input);
		} catch (NumberFormatException e) {
			System.out.println("Invalid Input!");
			return bet = decideBet();
		}
		if (bet <= this.dollars && bet > 0) {
			return bet;
		} else {
			System.out.println("You can't bet that amount!");
			return decideBet();
		}
	}

	/**
	 * Allows the player to decide whether or not to hit.
	 * 
	 * @return true if hitting
	 */
	public String makeMove() {
		System.out.print("Hit/Stand? ");
		String input = sc.next();
		if (input.equalsIgnoreCase("h") || input.equalsIgnoreCase("hit")) {
			return "hit";
		} else if (input.equalsIgnoreCase("s") || input.equalsIgnoreCase("stand")) {
			return "stand";
		} else {
			System.out.println("Invalid Input!");
			return makeMove();
		}
	}

	public Hand getHand() {
		return hand;
	}

	/**
	 * @return balance
	 */
	public int getBalance() {
		return dollars;
	}

	/**
	 * @return true if the player wants to play again
	 */
	public boolean askPlayAgain() {
		System.out.print("Play again? (y/n) ");
		String input = sc.next();
		if (input.equalsIgnoreCase("y")) {
			return true;
		} else if (input.equalsIgnoreCase("n")) {
			return false;
		} else {
			return askPlayAgain();
		}
	}

	public Deck clearHand() {
		Deck temp = hand.toDeck();
		hand.clear();
		return temp;
	}
}
