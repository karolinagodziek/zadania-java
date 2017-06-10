package deck;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DeckModel {

	private List<DeckCard> trefl;
	private List<DeckCard> karo;
	private List<DeckCard> kier;
	private List<DeckCard> pik;

	public DeckModel() {
		trefl = new ArrayList<>();
		karo = new ArrayList<>();
		kier = new ArrayList<>();
		pik = new ArrayList<>();

                kier.add(new DeckCard("as kier", 1, 11));
		kier.add(new DeckCard("2 kier", 2, 0));
		kier.add(new DeckCard("3 kier", 3, 0));
		kier.add(new DeckCard("4 kier", 4, 0));
		kier.add(new DeckCard("5 kier", 5, 0));
		kier.add(new DeckCard("6 kier", 6, 0));
		kier.add(new DeckCard("7 kier", 7, 0));
		kier.add(new DeckCard("8 kier", 8, 0));
		kier.add(new DeckCard("9 kier", 9, 0));
		kier.add(new DeckCard("10 kier", 10, 0));
		kier.add(new DeckCard("walet kier", 10, 0));
		kier.add(new DeckCard("dama kier", 10, 0));
		kier.add(new DeckCard("krol kier", 10, 0));
                
                karo.add(new DeckCard("as karo", 1, 11));
		karo.add(new DeckCard("2 karo", 2, 0));
		karo.add(new DeckCard("3 karo", 3, 0));
		karo.add(new DeckCard("4 karo", 4, 0));
		karo.add(new DeckCard("5 karo", 5, 0));
		karo.add(new DeckCard("6 karo", 6, 0));
		karo.add(new DeckCard("7 karo", 7, 0));
		karo.add(new DeckCard("8 karo", 8, 0));
		karo.add(new DeckCard("9 karo", 9, 0));
		karo.add(new DeckCard("10 karo", 10, 0));
		karo.add(new DeckCard("walet karo", 10, 0));
		karo.add(new DeckCard("dama karo", 10, 0));
		karo.add(new DeckCard("krol karo", 10, 0));
                
                trefl.add(new DeckCard("as trefl", 1, 11));
		trefl.add(new DeckCard("2 trefl", 2, 0));
		trefl.add(new DeckCard("3 trefl", 3, 0));
		trefl.add(new DeckCard("4 trefl", 4, 0));
		trefl.add(new DeckCard("5 trefl", 5, 0));
		trefl.add(new DeckCard("6 trefl", 6, 0));
		trefl.add(new DeckCard("7 trefl", 7, 0));
		trefl.add(new DeckCard("8 trefl", 8, 0));
		trefl.add(new DeckCard("9 trefl", 9, 0));
		trefl.add(new DeckCard("10 trefl", 10, 0));
		trefl.add(new DeckCard("walet trefl", 10, 0));
		trefl.add(new DeckCard("dama trefl", 10, 0));
		trefl.add(new DeckCard("krol trefl", 10, 0));

		pik.add(new DeckCard("as pik", 1, 11));
		pik.add(new DeckCard("2 pik", 2, 0));
		pik.add(new DeckCard("3 pik", 3, 0));
		pik.add(new DeckCard("4 pik", 4, 0));
		pik.add(new DeckCard("5 pik", 5, 0));
		pik.add(new DeckCard("6 pik", 6, 0));
		pik.add(new DeckCard("7 pik", 7, 0));
		pik.add(new DeckCard("8 pik", 8, 0));
		pik.add(new DeckCard("9 pik", 9, 0));
		pik.add(new DeckCard("10 pik", 10, 0));
		pik.add(new DeckCard("walet pik", 10, 0));
		pik.add(new DeckCard("dama pik", 10, 0));
		pik.add(new DeckCard("krol", 10, 0));
	}

	public DeckCard drawRandomCard() {
		Random random = new Random();

		int color = random.nextInt(4);
		List<DeckCard> cardColor = new ArrayList<>();
		
		switch (color) {
		case 0:
			cardColor = trefl;
			break;
		case 1:
			cardColor = karo;
			break;
		case 2:
			cardColor = kier;
			break;
		case 3:
			cardColor = pik;
			break;
		}
		
		int valueOfCard = random.nextInt(13);
		return cardColor.get(valueOfCard);
	}
}
