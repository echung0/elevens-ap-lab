/* This is a class that tests the Card class.
 */
public class CardTester {

 public static void main(String[] args) {
  Card one = new Card("3", "Spades", 3);
  Card two = new Card("2", "Spades", 3);
  Card three = new Card("3", "Clubs", 3);
  
  System.out.println("1 matches 2" + one.matches(two));
  System.out.println("1 matches 3" + one.matches(three));    
  System.out.println("Card 1 suit: " + one.suit());
  System.out.println("Card 1: " + one.toString());
 }
}