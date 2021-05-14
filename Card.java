//Christian Oliver
package card;

import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class Card extends Parent {
    
   
    //card suits
    enum Suit {
        HEARTS, DIAMONDS, SPADES, CLUBS;         
    
    };
    //card points
    enum Rank {
        TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), 
        NINE(9), TEN(10), JACK(10), QUEEN(10), KING(10), ACE(11);
        
        final int value;
        private Rank(int value){
            this.value = value;
        }
    };
    
    public final Suit suit;
    public final Rank rank;
    public final int value;
    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
        this.value = rank.value;
        
        Rectangle jack = new Rectangle(90, 150);
        // Creates card Background
        jack.setArcWidth(20);
        jack.setArcHeight(20);
        jack.setFill(Color.YELLOW);
        
        //Sets the wrapping of text to smaller than the card it's self
        Text t = new Text(toString());
        t.setWrappingWidth(80);
        
        //adds stack pane to allow for text to go on top of card
        getChildren().add(new StackPane(jack, t));
        
        
        
        
    }
    public String toString() {
        return rank.toString() + " of " + suit.toString();
    }
    
    
    
}
