package project;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import core.CoreOfGame;
import model.PlayerBJ;

public class Main {

	private static CoreOfGame core;
	private static PlayerBJ PlayerBj;

	private static boolean loop = true;

	public static void main(String[] args) {
		core = CoreOfGame.getInstance();
		PlayerBj = new PlayerBJ();
		while (loop) {
			checkCash();
			checkGame();
		}
	}

	private static void checkGame() {
		System.out.print("|                    Opcje                   |\n");
		System.out.print("|                                            |\n");
		System.out.print("|                (B)BlackJack                |\n");
		System.out.print("|                (S)Stan konta               |\n");

		String choice = input().toLowerCase();
                switch (choice.charAt(0)) {
                    
                    case 's':
			System.out.println("Twój obecny stan konta to: " + core.getCash() + "$");
			checkGame();
			break;
                    case 'b':
			boolean furtherPlay = true;
			while (furtherPlay) {
				PlayerBj.play();

				System.out.println("\nCzy chcesz grać dalej?");
				System.out.print("|                    Opcje                    |\n");
				System.out.print("|                    (T)Tak                   |\n");
				System.out.print("|                    (N)Nie                   |\n");
				System.out.print("|                                             |\n");

				choice = input().toLowerCase();
				switch (choice.charAt(0)) {
                                    case 't':
					break;
                                    case 'n':
					countCashAfterGameBlackJack();
					PlayerBj = new PlayerBJ();
					furtherPlay = false;
					break;
				default:
					System.out.print("\n Bład wprowadzenia!\n");
					choice = "INVALID_INPUT";
					countCashAfterGameBlackJack();
					checkGame();
					break;
				}
			}
			break;
                    default:
			System.out.print("\n Bład wprowadzenia!\n");
			choice = "INVALID_INPUT";
			checkGame();
			break;
		}
	}

	private static void countCashAfterGameBlackJack() {
            
		core.addCash(PlayerBj.getPlayerWins() * 20);
		core.substractCash(PlayerBj.getDealerWins() * 20);
	}

	private static void checkCash() {
		System.out.println("Wartośc twojego konta: " + core.getCash() + "$");
		if (core.getCash() <= 0) {
			System.out.println("Potrzebujesz minimum 20$ aby zacząć grę!");
			System.out.println("hcesz wpłacić brakującą kwotę?");
                        System.out.print("|                    Opcje                    |\n");
		        System.out.print("|                    (T)Tak                   |\n");
                        System.out.print("|                    (N)Nie                   |\n");
			System.out.print("|                                             |\n");
			String choice = input().toLowerCase();

			switch (choice.charAt(0)) {
                            case 't':
				core.setCash(20);
				break;
                            case 'n':
				System.out.println("Nie posiadasz odpowiedniej ilości pieniędzy by grać.");
				loop = false;
				break;
                            default:
				System.out.print("\n Bład wprowadzenia!\n");
				choice = "INVALID_INPUT";
				checkCash();
				break;
			}
		}
	}

	public static String input() {
		LineNumberReader BANANA = new LineNumberReader(new InputStreamReader(System.in));
		String input = "";
		try {
			input = BANANA.readLine();
		} catch (IOException APPLE) {
			System.err.println("Error taking input...");
		}
		return input;
	}
}
