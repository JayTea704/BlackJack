package blackjackpackage;

import java.awt.*;
import java.util.Scanner;

public class BlackJack {

    public static void main(String[] args) {

        //Welcome
        System.out.println("Welcome to Blackjack!");

        //Create Deck
        Deck playingDeck = new Deck();
        playingDeck.createFullDeck();
        //shuffle deck
        playingDeck.shuffle();
        //Create a deck for the player
        Deck playerDeck = new Deck();
        //Create a deck for the Dealer
        Deck dealerDeck = new Deck();

        double playerMoney = 100.00;

        Scanner userInput = new Scanner(System.in);

        //Game Loop
        while(playerMoney > 0) {
            //Game on!
            //Take bet
            System.out.println("you have $" + playerMoney + ", how much would you like to bet?");
            double playerBet = userInput.nextDouble();
            if(playerBet > playerMoney){
                System.out.println("You can't bet money you don't have.");
                break;
            }

            boolean endRound = false;

            //Start Dealing
            //Player gets 2 cards
            playerDeck.draw(playingDeck);
            playerDeck.draw(playingDeck);

            //Dealer gets 2 cards
            dealerDeck.draw(playingDeck);
            dealerDeck.draw(playingDeck);

            while (true){
                System.out.println("Your hand:");
                System.out.println(playerDeck.toString());
                System.out.println("Your deck is valued at: " + playerDeck.cardsValue());

                //Display Dealer Hand
                System.out.println("Dealer Hand: " + dealerDeck.getCard(0).toString() + " and [Hidden]");

                //Hit or Stand?
                System.out.println("Would you like to (1)Hit or (2)Stand?");
                int response = userInput.nextInt();

                //They hit
                if(response == 1){
                    playerDeck.draw(playingDeck);
                    System.out.println("You draw a:" + playerDeck.getCard(playerDeck.deckSize()-1).toString());
                    //Bust if > 21
                    if(playerDeck.cardsValue() > 21){
                        System.out.println("Bust! Currently valued at: " + playerDeck.cardsValue());
                        playerMoney -= playerBet;
                        endRound = true;
                        break;
                    } //end of nested if statement

                } //end of if statement

                //They stand
                if(response == 2){
                    break;
                }
            } //end of while loop

            //Reveal Dealer Cards
            System.out.println("Dealer Cards: " + dealerDeck.toString());
            //See if dealer has more points than player
            if((dealerDeck.cardsValue() > playerDeck.cardsValue()) && endRound == false){
                System.out.println("Dealer Wins!");
                playerMoney -= playerBet;
                endRound = true;
            }
            //Dealer Stands at 17
            while ((dealerDeck.cardsValue() < 17) && endRound == false){
               dealerDeck.draw(playingDeck);
               System.out.println("Dealer Draws: " + dealerDeck.getCard(dealerDeck.deckSize()-1).toString());
            }
            //display total value for dealer
            System.out.println("Dealers Hand is valued at: " + dealerDeck.cardsValue());
            //determine if dealer busted
            if((dealerDeck.cardsValue()) > 21 && endRound == false){
                System.out.println("Dealer busts! You Win.");
                playerMoney += playerBet;
                endRound = true;
            }

            //if tie
            if((playerDeck.cardsValue() == dealerDeck.cardsValue()) && endRound == false) {
                System.out.println("Push");
                endRound = true;
            }

            if((playerDeck.cardsValue() > dealerDeck.cardsValue()) && endRound == false){
                System.out.println("You win!");
                playerMoney += playerBet;
                endRound = true;
            }
            else if(endRound == false){
                System.out.println("You lose the hand.");
                playerMoney -= playerBet;
                endRound = true;
            }

            playerDeck.moveBackToDeck(playingDeck);
            dealerDeck.moveBackToDeck(playingDeck);
            System.out.println("End of hand.");

        } //end of game loop

        System.out.println("Game over! You are broke.");

    }// end of main method

} //end of BlackJack class
