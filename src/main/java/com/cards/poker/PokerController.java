package com.cards.poker;

import com.cards.poker.objects.Card;
import com.cards.poker.objects.Deck;
import com.cards.poker.objects.HandRank;
import com.cards.poker.objects.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * The controller for the Poker game
 */
public class PokerController {

    private static final String PNG_SUFFIX = ".png";
    private static final String CARD_RESOURCE_PREFIX = "cards/";
    private static final String PLAYER_CARD_1 = "playerCard1";
    private static final String PLAYER_CARD_2 = "playerCard2";
    private static final String PLAYER_CARD_3 = "playerCard3";
    private static final String PLAYER_CARD_4 = "playerCard4";
    private static final String PLAYER_CARD_5 = "playerCard5";
    private static final String COVER_CARD_URL = "cards/Cover.png";
    @FXML
    private Label welcomeText;

    @FXML
    private ImageView dealerCard1;

    @FXML
    private ImageView dealerCard2;

    @FXML
    private ImageView dealerCard3;

    @FXML
    private ImageView dealerCard4;

    @FXML
    private ImageView dealerCard5;

    @FXML
    private ImageView playerCard1;

    @FXML
    private ImageView playerCard2;

    @FXML
    private ImageView playerCard3;

    @FXML
    private ImageView playerCard4;

    @FXML
    private ImageView playerCard5;

    @FXML
    private Button discardButton;

    @FXML
    private Button foldButton;

    @FXML
    private Button showButton;

    @FXML
    private Button restartButton;

    private Deck deck;
    private Player humanPlayer;
    private Player computerPlayer;

    @FXML
    /**
     * Introduction click button to welcome the player
     */
    protected void introductionButtonClick(ActionEvent e) {
        newGame();
        displayPlayerHand();
        hideBeginButton(e);
        this.welcomeText.setText("Welcome to the Poker Game!");
        this.discardButton.setVisible(true);
    }

    @FXML
    /**
     * Discard the user's selected cards
     */
    protected void discardCard(ActionEvent e) {
        Node node = (Node)e.getSource();
        node.setVisible(false);
        this.humanPlayer.discardCards(deck);
        displayPlayerHand();
        this.showButton.setVisible(true);
        this.foldButton.setVisible(true);
        playerCard1.setTranslateY(0);
        playerCard2.setTranslateY(0);
        playerCard3.setTranslateY(0);
        playerCard4.setTranslateY(0);
        playerCard5.setTranslateY(0);
    }

    @FXML
    /**
     * A method called when the user clicks on their card to prepare to move it
     */
    protected void selectedCard(ActionEvent e) {
        Node node = (Node)e.getSource();
        String data = (String)node.getUserData();
        if(!discardButton.isVisible()) {
            return;
        }
        switch(data) {
            case PLAYER_CARD_1:
                if(playerCard1.getTranslateY() == 0) {
                    playerCard1.setTranslateY(100);
                    humanPlayer.getSelectedCards()[0] = true;
                }else{
                    playerCard1.setTranslateY(0);
                    humanPlayer.getSelectedCards()[0] = false;
                }
                break;
            case PLAYER_CARD_2:
                if(playerCard2.getTranslateY() == 0) {
                    playerCard2.setTranslateY(100);
                    humanPlayer.getSelectedCards()[1] = true;
                }else{
                    playerCard2.setTranslateY(0);
                    humanPlayer.getSelectedCards()[1] = false;
                }
                break;
            case PLAYER_CARD_3:
                if(playerCard3.getTranslateY() == 0) {
                    playerCard3.setTranslateY(100);
                    humanPlayer.getSelectedCards()[2] = true;
                }else{
                    playerCard3.setTranslateY(0);
                    humanPlayer.getSelectedCards()[2] = false;
                }
                break;
            case PLAYER_CARD_4:
                if(playerCard4.getTranslateY() == 0) {
                    playerCard4.setTranslateY(100);
                    humanPlayer.getSelectedCards()[3] = true;
                }else{
                    playerCard4.setTranslateY(0);
                    humanPlayer.getSelectedCards()[3] = false;
                }
                break;
            case PLAYER_CARD_5:
                if(playerCard5.getTranslateY() == 0) {
                    playerCard5.setTranslateY(100);
                    humanPlayer.getSelectedCards()[4] = true;
                }else{
                    playerCard5.setTranslateY(0);
                    humanPlayer.getSelectedCards()[4] = false;
                }
                break;
        }
    }

    @FXML
    /**
     * Show the player's and dealer's hand to compare and declare the winner
     */
    protected void showHand() {
        displayComputerHand();
        showButton.setVisible(false);
        foldButton.setVisible(false);
        restartButton.setVisible(true);
        HandRank playerHandRank = new HandRank(humanPlayer.getCards());
        HandRank computerHandRank = new HandRank(computerPlayer.getCards());
        if(playerHandRank.compareTo(computerHandRank) > 0) {
            this.welcomeText.setText("You have won with " + playerHandRank + " compared to the dealer's hand " + computerHandRank);
        }else if(playerHandRank.compareTo(computerHandRank) < 0) {
            this.welcomeText.setText("You have lost with " + playerHandRank + " compared to the dealer's hand " + computerHandRank);
        }else {
            this.welcomeText.setText("The dealer and you both have the same hand: " + computerHandRank);
        }
    }


    @FXML
    /**
     * Allow the player to fold early instead of revealing the computer's hand
     */
    protected void fold() {
        hideAllCards();
        showButton.setVisible(false);
        foldButton.setVisible(false);
        restartButton.setVisible(true);
    }

    @FXML
    /**
     * Restart the game and hide all of the necessary information
     */
    protected void restartGame() {
        restartButton.setVisible(false);
        discardButton.setVisible(true);
        hideAllCards();
        newGame();
        displayPlayerHand();
    }

    /**
     * Hide the begin button once the user has clicked it
     * @param e
     */
    private void hideBeginButton(ActionEvent e) {
        Node node = (Node)e.getSource();
        node.setVisible(false);
    }

    /**
     * Start a new game
     */
    private void newGame() {
        this.deck = new Deck();
        this.humanPlayer = new Player();
        this.computerPlayer = new Player();
        this.humanPlayer.drawHand(deck);
        this.computerPlayer.drawHand(deck);
    }

    /**
     * Display the player's hand based on the card he or she possess
     */
    private void displayPlayerHand() {
        Card[] humanCards = humanPlayer.getCards();
        Image newImage1 = new Image(this.getClass().getResource(CARD_RESOURCE_PREFIX + humanCards[0].getSuit().toString() + humanCards[0].getNumber().getLetter() + PNG_SUFFIX).toExternalForm());
        Image newImage2 = new Image(this.getClass().getResource(CARD_RESOURCE_PREFIX + humanCards[1].getSuit().toString() + humanCards[1].getNumber().getLetter() + PNG_SUFFIX).toExternalForm());
        Image newImage3 = new Image(this.getClass().getResource(CARD_RESOURCE_PREFIX + humanCards[2].getSuit().toString() + humanCards[2].getNumber().getLetter() + PNG_SUFFIX).toExternalForm());
        Image newImage4 = new Image(this.getClass().getResource(CARD_RESOURCE_PREFIX + humanCards[3].getSuit().toString() + humanCards[3].getNumber().getLetter() + PNG_SUFFIX).toExternalForm());
        Image newImage5 = new Image(this.getClass().getResource(CARD_RESOURCE_PREFIX + humanCards[4].getSuit().toString() + humanCards[4].getNumber().getLetter() + PNG_SUFFIX).toExternalForm());
        playerCard1.setImage(newImage1);
        playerCard2.setImage(newImage2);
        playerCard3.setImage(newImage3);
        playerCard4.setImage(newImage4);
        playerCard5.setImage(newImage5);
    }

    /**
     * Display the computer's hand based on the card it has
     */
    private void displayComputerHand() {
        Card[] computerCards = computerPlayer.getCards();
        Image newImage1 = new Image(this.getClass().getResource(CARD_RESOURCE_PREFIX + computerCards[0].getSuit().toString() + computerCards[0].getNumber().getLetter() + PNG_SUFFIX).toExternalForm());
        Image newImage2 = new Image(this.getClass().getResource(CARD_RESOURCE_PREFIX + computerCards[1].getSuit().toString() + computerCards[1].getNumber().getLetter() + PNG_SUFFIX).toExternalForm());
        Image newImage3 = new Image(this.getClass().getResource(CARD_RESOURCE_PREFIX + computerCards[2].getSuit().toString() + computerCards[2].getNumber().getLetter() + PNG_SUFFIX).toExternalForm());
        Image newImage4 = new Image(this.getClass().getResource(CARD_RESOURCE_PREFIX + computerCards[3].getSuit().toString() + computerCards[3].getNumber().getLetter() + PNG_SUFFIX).toExternalForm());
        Image newImage5 = new Image(this.getClass().getResource(CARD_RESOURCE_PREFIX + computerCards[4].getSuit().toString() + computerCards[4].getNumber().getLetter() + PNG_SUFFIX).toExternalForm());
        dealerCard1.setImage(newImage1);
        dealerCard2.setImage(newImage2);
        dealerCard3.setImage(newImage3);
        dealerCard4.setImage(newImage4);
        dealerCard5.setImage(newImage5);
    }

    /**
     * Hide all of the cards such as when the click restarts the game
     */
    private void hideAllCards() {
        Image coverImage = new Image(this.getClass().getResource(COVER_CARD_URL).toExternalForm());
        dealerCard1.setImage(coverImage);
        dealerCard2.setImage(coverImage);
        dealerCard3.setImage(coverImage);
        dealerCard4.setImage(coverImage);
        dealerCard5.setImage(coverImage);
        playerCard1.setImage(coverImage);
        playerCard2.setImage(coverImage);
        playerCard3.setImage(coverImage);
        playerCard4.setImage(coverImage);
        playerCard5.setImage(coverImage);

    }
}