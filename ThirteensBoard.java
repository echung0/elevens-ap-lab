import java.util.List;
import java.util.ArrayList;

/**
 * The ElevensBoard class represents the board in a game of Elevens.
 */
public class ThirteensBoard extends Board {

	/**
	 * The size (number of cards) on the board.
	 */
	private static final int BOARD_SIZE = 10;

	/**
	 * The ranks of the cards for this game to be sent to the deck.
	 */
	private static final String[] RANKS =
		{"ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "jack", "queen", "king"};

	/**
	 * The suits of the cards for this game to be sent to the deck.
	 */
	private static final String[] SUITS =
		{"spades", "hearts", "diamonds", "clubs"};

	/**
	 * The values of the cards for this game to be sent to the deck.
	 */
	private static final int[] POINT_VALUES =
		{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 0};

	/**
	 * Creates a new <code>ElevensBoard</code> instance.
	 */
	public ThirteensBoard() {
		super(BOARD_SIZE, RANKS, SUITS, POINT_VALUES);
	}

	/**
	 * Determines if the selected cards form a valid group for removal.
	 * In Elevens, the legal groups are (1) a pair of non-face cards
	 * whose values add to 11, and (2) a group of three cards consisting of
	 * a jack, a queen, and a king in some order.
	 * @param selectedCards the list of the indices of the selected cards.
	 * @return true if the selected cards form a valid group for removal;
	 *         false otherwise.
	 */
	@Override
	public boolean isLegal(List<Integer> selectedCards) {
		if (selectedCards.size() == 2) {
			return findPairSum13(selectedCards).size() > 0;
		} else if (selectedCards.size() == 1) {
			return findKing(selectedCards).size() > 0;
		} else {
			return false;
		}
	}

	/**
	 * Determine if there are any legal plays left on the board.
	 * In Elevens, there is a legal play if the board contains
	 * (1) a pair of non-face cards whose values add to 11, or (2) a group
	 * of three cards consisting of a jack, a queen, and a king in some order.
	 * @return true if there is a legal play left on the board;
	 *         false otherwise.
	 */
	@Override
	public boolean anotherPlayIsPossible() {
		return (findPairSum13(cardIndexes()).size() > 0) || (findKing(cardIndexes()).size() > 0);
	}

	/**
	 * Look for a 13-pair in the selected cards.
	 * @param selectedCards selects a subset of this board.  It is list
	 *                      of indexes into this board that are searched
	 *                      to find a 13-pair.
	 * @return a list of the indexes of a 13-pair, if a 13-pair was found;
	 *         an empty list, if a 13-pair was not found.
	 */
	private List<Integer> findPairSum13(List<Integer> selectedCards) {
		List<Integer> pair = new ArrayList<>();
		for (int i = 0; i < selectedCards.size(); i++) {
			int firstCard = cardAt(selectedCards.get(i)).pointValue();
			for (int j = i + 1; j < selectedCards.size(); j++) {
				int secondCard = cardAt(selectedCards.get(j)).pointValue();
				if (firstCard + secondCard == 13) {
					pair.add(selectedCards.get(i));
					pair.add(selectedCards.get(j));
					return pair;
				}
			}
		}
		return pair;
	}

	private List<Integer> findKing(List<Integer> selectedCards) {
		List<Integer> king = new ArrayList<>();
		for (int i = 0; i < selectedCards.size(); i++) {
			if (cardAt(selectedCards.get(i)).pointValue() == 0) {
				king.add(selectedCards.get(i));
				return king;
			}
		}
		return king;
	}

	/**
	 * Looks for a legal play on the board.  If one is found, it plays it.
	 * @return true if a legal play was found (and made); false otherwise.
	 */
	public boolean playIfPossible() {
		return playPairSum13IfPossible() || playKingIfPossible();
	}

	/**
	 * Looks for a pair of non-face cards whose values sum to 13.
	 * If found, replace them with the next two cards in the deck.
	 * The simulation of this game uses this method.
	 * @return true if a 13-pair play was found (and made); false otherwise.
	 */
	private boolean playPairSum13IfPossible() {
		List<Integer> indexes = findPairSum13(cardIndexes());
		if (indexes.size() == 2) {
			replaceSelectedCards(indexes);
			return true;
		}
		return false;
	}

	private boolean playKingIfPossible() {
		List<Integer> indexes = findKing(cardIndexes());
		if (indexes.size() == 1) {
			replaceSelectedCards(indexes);
			return true;
		}
		return false;
	}
}
