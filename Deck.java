/**
 * The Deck class represents a shuffled deck of cards.
 * It provides several operations including
 *      initialize, shuffle, deal, and check if empty.
 */
public class Deck {

 /**
  * cards contains all the cards in the deck.
  */
 private Card[] cards;

 /**
  * size is the number of not-yet-dealt cards.
  * Cards are dealt from the top (highest index) down.
  * The next card to be dealt is at size - 1.
  */
 private int size;


 /**
  * Creates a new <code>Deck</code> instance.<BR>
  * It pairs each element of ranks with each element of suits,
  * and produces one of the corresponding card.
  * @param ranks is an array containing all of the card ranks.
  * @param suits is an array containing all of the card suits.
  * @param values is an array containing all of the card point values.
  */
 public Deck(String[] ranks, String[] suits, int[] values) {
  cards = new Card[ranks.length * suits.length];
  int index = 0;
  for (int j = 0; j < ranks.length; j++) {
   for (String suitString : suits) {
    cards[index++] = new Card(ranks[j], suitString, values[j]);
   }
  }
  size = cards.length;
  shuffle();
 }


 /**
  * Determines if this deck is empty (no undealt cards).
  * @return true if this deck is empty, false otherwise.
  */
 public boolean isEmpty() {
  return size == 0;
 }

 /**
  * Accesses the number of undealt cards in this deck.
  * @return the number of undealt cards in this deck.
  */
 public int size() {
  return size;
 }

 /**
  * Randomly permute the given collection of cards
  * and reset the size to represent the entire deck.
  */
 public void shuffle() {
  for (int i = cards.length - 1; i > 0; i--) {
   int amt = i + 1;
   int start = 0;
   int randPos = (int) (Math.random() * amt) + start;
   Card temp = cards[i];
   cards[i] = cards[randPos];
   cards[randPos] = temp;
  }
  size = cards.length;
 }

 /**
  * Deals a card from this deck.
  * @return the card just dealt, or null if all the cards have been
  *         previously dealt.
  */
 public Card deal() {
  if (isEmpty()) {
   return null;
  }
  size--;
  Card c = cards[size];
  return c;
 }

 /**
  * Generates and returns a string representation of this deck.
  * @return a string representation of this deck.
  */
 @Override
 public String toString() {
  String rtn = "size = " + size + "\nUndealt cards: \n";

  for (int k = size - 1; k >= 0; k--) {
   rtn = rtn + cards[k];
   if (k != 0) {
    rtn = rtn + ", ";
   }
   if ((size - k) % 2 == 0) {
    // Insert carriage returns so entire deck is visible on console.
    rtn = rtn + "\n";
   }
  }

  rtn = rtn + "\nDealt cards: \n";
  for (int k = cards.length - 1; k >= size; k--) {
   rtn = rtn + cards[k];
   if (k != size) {
    rtn = rtn + ", ";
   }
   if ((k - cards.length) % 2 == 0) {
    // Insert carriage returns so entire deck is visible on console.
    rtn = rtn + "\n";
   }
  }

  rtn = rtn + "\n";
  return rtn;
 }
}
