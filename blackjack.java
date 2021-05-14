//Christian Oliver
package card;

import javafx.application.Application;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
public class blackjack extends Application{
    
    private Deck cards = new Deck();
    private Text message = new Text();
    private Dealer Rusty, PPlay;
    
    private SimpleBooleanProperty playable = new SimpleBooleanProperty(false);
    
    
    
    
    private HBox aICards = new HBox(20);
    
    private HBox p1Cards = new HBox(20);
     private Parent createContent() {
        Rusty = new Dealer(aICards.getChildren());
         PPlay= new Dealer(p1Cards.getChildren());

        Pane root = new Pane();
        root.setPrefSize(500, 300);

        Region background = new Region();
        background.setPrefSize(1000, 800);
        background.setStyle("-fx-background-color: WHITE");

        HBox rootLayout = new HBox(5);
        rootLayout.setPadding(new Insets(5, 5, 5, 5));
        Rectangle leftBG = new Rectangle(550, 560);
        leftBG.setArcWidth(50);
        leftBG.setArcHeight(50);
        leftBG.setFill(Color.MAGENTA);
        Rectangle rightBG = new Rectangle(230, 560);
        rightBG.setArcWidth(50);
        rightBG.setArcHeight(50);
        rightBG.setFill(Color.YELLOW);

        
        VBox leftVBox = new VBox(50);
        leftVBox.setAlignment(Pos.TOP_CENTER);

        Text RustyScore = new Text("Rusty: ");
        Text playerScore = new Text("Player: ");

        leftVBox.getChildren().addAll(RustyScore, aICards, message, p1Cards, playerScore);

        
        VBox rightVBox = new VBox(20);
        rightVBox.setAlignment(Pos.CENTER);

        final TextField bet = new TextField("BET");
        bet.setDisable(true);
        bet.setMaxWidth(50);
        Text credits = new Text("Credits");

        Button btnPlay = new Button("PLAY");
        Button btnHit = new Button("HIT");
        Button btnStand = new Button("STAND");

        HBox buttonsHBox = new HBox(15, btnHit, btnStand);
        buttonsHBox.setAlignment(Pos.CENTER);

        rightVBox.getChildren().addAll(bet, btnPlay, credits, buttonsHBox);

       

        rootLayout.getChildren().addAll(new StackPane(leftBG, leftVBox), new StackPane(rightBG, rightVBox));
        root.getChildren().addAll(background, rootLayout);

       

        btnPlay.disableProperty().bind(playable);
        btnHit.disableProperty().bind(playable.not());
        btnStand.disableProperty().bind(playable.not());

        playerScore.textProperty().bind(new SimpleStringProperty("Player: ").concat(PPlay.valueProperty().asString()));
       RustyScore.textProperty().bind(new SimpleStringProperty("Rusty: ").concat(Rusty.valueProperty().asString()));

        PPlay.valueProperty().addListener((obs, old, newValue) -> {
            if (newValue.intValue() >= 21) {
                endGame();
            }
        });

        Rusty.valueProperty().addListener((obs, old, newValue) -> {
            if (newValue.intValue() >= 21) {
                endGame();
            }
        });

        // Buttons to play the game

        btnPlay.setOnAction(event -> {
            startNewGame();
        });

        btnHit.setOnAction(event -> {
            PPlay.takeCard(cards.drawCard());
        });

        btnStand.setOnAction(event -> {
            while (Rusty.valueProperty().get() < 17) {
                Rusty.takeCard(cards.drawCard());
            }

            endGame();
        });

        return root;
    }

    private void startNewGame() {
        playable.set(true);
        message.setText("");

        cards.refill();

        Rusty.reset();
        PPlay.reset();

        Rusty.takeCard(cards.drawCard());
        Rusty.takeCard(cards.drawCard());
        PPlay.takeCard(cards.drawCard());
        PPlay.takeCard(cards.drawCard());
    }

    private void endGame() {
        playable.set(false);

        int RustyValue = Rusty.valueProperty().get();
        int PPlayValue = PPlay.valueProperty().get();
        String winner = "Exceptional case: d: " + RustyValue + " p: " + PPlayValue;

        // Checks who one the game
        if (RustyValue == 21 || PPlayValue > 21 ||(RustyValue < 21 && RustyValue > PPlayValue)) {
            winner = "Rusty";
        }
        else if (PPlayValue == 21 || RustyValue > 21 || PPlayValue > RustyValue) {
            winner = "You";
        }
        else {
            winner = "NO ONE ";
        }

        message.setText(winner + " WON");
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setScene(new Scene(createContent()));
        primaryStage.setWidth(800);
        primaryStage.setHeight(600);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Black-Jack");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
