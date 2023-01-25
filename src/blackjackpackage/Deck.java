package blackjackpackage;

import java.util.ArrayList;
import java.util.Random;
// import java.util.Stack;

public class Deck {

    private ArrayList<Card> cards;

    //constructor
    public Deck(){
        this.cards = new ArrayList<Card>();
    }

    public void createFullDeck(){ //makes deck of cards
        //Generates Cards
        for(Suit cardSuit : Suit.values()){
            for(Value cardValue : Value.values()) {
                //Add a new card to the deck
                this.cards.add(new Card(cardSuit,cardValue));
            }
        }
    }

    public void shuffle(){ //shuffles deck
        ArrayList<Card> tempDeck = new ArrayList<Card>();
        //use Random
        Random random = new Random();
        int randomCardIndex = 0;
        int originalSize = this.cards.size();
        for(int i = 0; i < originalSize; i++){
            //Generate Random Index
            randomCardIndex = random.nextInt((this.cards.size()-1) + 1);
            tempDeck.add(this.cards.get(randomCardIndex));
            //Remove from original deck
            this.cards.remove(randomCardIndex);
        }

        this.cards = tempDeck;
    }
   /*
   public void convertToStack(){ //converts arraylist to Stack
    Stack<String> st = new Stack<>();

        st.addAll(tmpDeck)
} //end fo convertStack method

    */

    //converts cards to strings
    public String toString(){
        String cardListOutput = "";
        int i = 0;
        for(Card aCard : this.cards){
            cardListOutput += "\n" + aCard.toString();
        }
        return cardListOutput;

    }

    //removes card from deck
    public void removeCard(int i){
        this.cards.remove(i);
    }

    //gets cards from playing deck
    public Card getCard(int i){
        return this.cards.get(i);
    }

    //adds cards to player or dealer hand
    public void addCard(Card addCard){
        this.cards.add(addCard);
    }

    //Draws from the deck
    public void draw(Deck comingFrom){
        this.cards.add(comingFrom.getCard(0));
        comingFrom.removeCard(0);
    }

    //checks the size of deck
    public int deckSize(){
        return this.cards.size();
    }

    //moves used cards back to deck after end of hand
    public void moveBackToDeck(Deck moveto){
        int thisDeckSize = this.cards.size();

        //puts cards into moveTo deck
        for(int i = 0; i < thisDeckSize; i++){
            moveto.addCard(this.getCard(i));
        }

        for(int i = 0; i < thisDeckSize; i++){
            this.removeCard(0);
        }

    } //end of moveBackToDeck method

    //Return total val of cards in hand
    public int cardsValue(){
        int totalValue = 0;
        int aces = 0;

        for(Card aCard : this.cards){
            switch(aCard.getValue()){ //assigns values to each card
                case TWO: totalValue += 2; break;
                case THREE: totalValue += 3; break;
                case FOUR: totalValue += 4; break;
                case FIVE: totalValue += 5; break;
                case SIX: totalValue += 6; break;
                case SEVEN: totalValue += 7; break;
                case EIGHT: totalValue += 8; break;
                case NINE: totalValue += 9; break;
                case TEN: totalValue += 10; break;
                case JACK: totalValue += 10; break;
                case QUEEN: totalValue += 10; break;
                case KING: totalValue += 10; break;
                case ACE: aces += 1; break;
            } //end of switch case

        } // end of for loop

        for(int i = 0; i < aces; i++){ //checks if aces should be valued at 1 or 11

            if(totalValue > 10){
                totalValue += 1;
            }
            else {
                totalValue += 11;
            }

        } //end of for loop

        return totalValue; //returns point value of the hand

    } //end of card value method

} //end of deck class
