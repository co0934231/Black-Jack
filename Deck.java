//Christian Oliver
package card;
//Pulls Rank and Suit from Card.java
import card.Card.Rank;
import card.Card.Suit;

public class Deck {
    // determines 52 card deck
   private Card[] card = new Card[52];
   
   public Deck() {
       refill();
       
   }
//   Returns all rank values fron Rank and Suit in Card.Java
   public final void refill() {
       int i= 0;
       for (Suit suit : Suit.values()){
           for (Rank rank : Rank.values()) {
               card[i++] = new Card(suit, rank);
           }
       }
   }
   // Calls random card from the deck at random and does not allow to pull same card twice
   public Card drawCard() {
      Card card1 = null;
      while (card1 == null) {
          int index = (int)(Math.random()*card.length);
          card1 = card[index];
          card[index] = null;
      }
      return card1;
   }
}
