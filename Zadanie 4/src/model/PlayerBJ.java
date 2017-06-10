package model;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;
import deck.DeckCard;
import deck.DeckModel;

public class PlayerBJ {

	private static final int PLAYER_HAND = 1;
	private static final int DEALER_HAND = 2;
	public static final int HOUSE_LIMIT = 17;

	private DeckModel deck;
	private List<DeckCard> playerCard;
	private List<DeckCard> dealerCard;

	private int valueOfPlayerHand = 0;
	private int valueOfDealerHand = 0;

	private int startingCards = 2;
	private int maxSizeOfHand = 20;

	private int playerWins = 0;
	private int dealerWins = 0;

	private boolean displayAllDealerCards;
	private boolean playerStay;
	private boolean dealerStay;

	public PlayerBJ() {
		deck = new DeckModel();
		playerCard = new ArrayList<>();
		dealerCard = new ArrayList<>();
	}

	public void play() {
		playerCard = new ArrayList<>();
		dealerCard = new ArrayList<>();
		displayAllDealerCards = false;
		playerStay = false;
		dealerStay = false;
		valueOfDealerHand = 0;
		valueOfPlayerHand = 0;

		System.out.println("Gra w BlackJack-a");
		System.out.println("Pierwsze rozdanie");
		System.out.println("Rozdane zostały po dwie karty dla gracza oraz krupiera");

		for (int i = 0; i < startingCards; i++) {
			drawHand(PLAYER_HAND);
			drawHand(DEALER_HAND);
		}

		checkHand(PLAYER_HAND);
		checkHand(DEALER_HAND);

		deal();

		if (!displayAllDealerCards) {
			System.out.println("Od tego momentu karty krupiera są widoczne");
		}
		displayAllDealerCards = true;

		checkHand(PLAYER_HAND);
		checkHand(DEALER_HAND);

		if (valueOfPlayerHand == 21) {
			System.out.print("21! Wygrałeś!");
			playerWins++;
		}
		else if (valueOfDealerHand == 21) {
			System.out.print("21! Krupier wygrał!");
			dealerWins++;
		}
		else if (valueOfPlayerHand > 21) {
			System.out.print("Przekroczyłeś 21, przegrywasz!");
			dealerWins++;
		}
		else if (valueOfDealerHand > 21) {
			System.out.print("Krupier przekroczył 21, Wygrywasz!");
			playerWins++;
		}

		if (playerStay && valueOfDealerHand <= 21) {
			if (valueOfPlayerHand == valueOfDealerHand) {
				System.out.print("Remis! Grasz oraz krupier mają tyle samo punktów");
				playerWins++;
				dealerWins++;
			} else if (valueOfPlayerHand > valueOfDealerHand) {
				System.out.print("Wygrałeś! Posiadasz więcej punktów");
				playerWins++;
			} else if (valueOfDealerHand > valueOfPlayerHand) {
				System.out.print("Przegrywasz! Masz mniej punktów!");
				dealerWins++;
			}
		}
	}

	private void deal() {
		String choice = "#";

		while (choice.charAt(0) != 'o' && valueOfDealerHand < 21 && valueOfPlayerHand < 21 && !playerStay) {
			System.out.println("Jaki ruch chcesz wykonać?");
			System.out.print("|                   Opcje                    |\n");
			System.out.print("|                 (D)Dobierz                 |\n");
			System.out.print("|                 (C)Czekaj                  |\n");
			System.out.print("|                 (O)Odejdz                  |\n");
                            
                            choice = input().toLowerCase();

			switch (choice.charAt(0)) {
			case 'd':
				System.out.print("\n Wybrałeś dobranie.\n");
				hit(PLAYER_HAND);
				break;
			case 'c':
				System.out.print("\n Wybrałeś czekanie.\n");
				playerStay = true;
				checkHand(PLAYER_HAND);
				break;
			case 'o':
                                System.out.print("\n Wybrałeś wyjście, dziękujemy za grę! \n");
				break;
			default:
				System.out.print("\n Bład wprowadzenia!\n");
				choice = "INVALID_INPUT";
				break;
			}

			if (!choice.equals("INVALID_INPUT")) {
				if (valueOfPlayerHand < 21) {
					if (valueOfDealerHand < HOUSE_LIMIT) {
						System.out.print("\n Krupier dobiera kartę.\n");
						hit(DEALER_HAND);
					} else {
						System.out.print("\n Krupier czeka.\n");
						dealerStay = true;
						checkHand(DEALER_HAND);
					}
				}
			}
		}
	}

	private void hit(int who) {
		drawHand(who);
		checkHand(who);

		if (who == PLAYER_HAND) {
			if (valueOfPlayerHand > 21) {
				for (int i = 0; i < playerCard.size(); i++) {
					DeckCard card = playerCard.get(i);
					if (card.checkIfAs()) {
						System.out.println("Wartość twojej ręki jest powyżej 21 ale posiadasz Asa.");
						System.out.println("Zamiana wartości Asa z 11 na 1");
						card.setFirstValue(1);
						break;
					}
				}

				checkHand(who);
			}
		} else {
			if (valueOfDealerHand > 21) {
				for (int i = 0; i < dealerCard.size(); i++) {
					DeckCard card = dealerCard.get(i);
					if (card.checkIfAs()) {
						System.out.println("Wartość ręki krupiera jest powyżej 21 ale posiada Asa.");
						System.out.println("Zamiana wartości Asa z 11 na 1");
						card.setFirstValue(1);
						break;
					}
				}

				checkHand(who);
			}
		}
	}

	private void drawHand(int who) {
		switch (who) {
                    
		case PLAYER_HAND:
			playerCard.add(deck.drawRandomCard());
			break;
                        
		case DEALER_HAND:
			dealerCard.add(deck.drawRandomCard());
			break;
		}
	}

	private void checkHand(int who) {
		int handValue = 0;
		switch (who) {
                    
		case PLAYER_HAND:
			for (int i = 0; i < playerCard.size(); i++) {
				handValue += playerCard.get(i).getFirstValue();
			}
			System.out.println("Punkty gracza: " + handValue);
			valueOfPlayerHand = handValue;
			break;
                        
		case DEALER_HAND:
                    
			if (displayAllDealerCards) {
				for (int i = 0; i < dealerCard.size(); i++) {
					handValue += dealerCard.get(i).getFirstValue();
				}
				System.out.println("Punkty krupiera: " + handValue);
				valueOfDealerHand = handValue;
			} else {
				for (int i = 1; i < dealerCard.size(); i++) {
					handValue += dealerCard.get(i).getFirstValue();
				}
				System.out.println("Punkty krupiera: " + handValue + " (UKRYTA JEDNA KARTA)");
			}
			break;
		}
	}

	public static String input() {
		LineNumberReader numberReader = new LineNumberReader(new InputStreamReader(System.in));
		String input = "";
		try {
			input = numberReader.readLine();
		} catch (IOException ExceptionIO) {
			System.err.println("Błąd IO");
		}
		return input;
	}

	public int getDealerWins() {
		return dealerWins;
	}

	public int getPlayerWins() {
		return playerWins;
	}
}
