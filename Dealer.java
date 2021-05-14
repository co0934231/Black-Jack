//Christian Oliver
package card;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import card.Card.Rank;

public class Dealer {
    private ObservableList<Node> card;
    private SimpleIntegerProperty value = new SimpleIntegerProperty(0);
    private int aces = 0;
    
    public Dealer(ObservableList<Node> card) {
        this.card = card;
    }
    public void takeCard(Card card1) {
        card.add(card1);

        if (card1.rank == Rank.ACE) {
            aces++;
        }

        if (value.get() + card1.value > 21 && aces > 0) {
            value.set(value.get() + card1.value - 10);    //then count ace as '1' not '11'
            aces--;
        }
        else {
            value.set(value.get() + card1.value);
        }
    }

    public void reset() {
        card.clear();
        value.set(0);
        aces = 0;
    }

    public SimpleIntegerProperty valueProperty() {
        return value;
    }
    
}
