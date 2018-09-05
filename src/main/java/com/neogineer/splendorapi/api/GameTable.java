package com.neogineer.splendorapi.api;

import com.neogineer.splendorapi.api.data.Card;
import com.neogineer.splendorapi.api.data.Noble;
import com.neogineer.splendorapi.api.data.TokensArray;

import java.util.ArrayList;
import java.util.List;

class GameTable {

    TokensArray mTokens;

    private List<Noble> mNoblesDeck;
    List<Noble> mNobles;

    // for the three levels
    private List<Card>[] mCardsDeck = new List[3];
    private List<Card>[] mCards = new List[3];

    public GameTable(int playersCount) {
        if(playersCount<2 || playersCount>4)
            throw new IllegalArgumentException("number of players should be from 2 to 4");
        loadCards();
        loadNobles(playersCount);
        loadTokens(playersCount);
    }

    private void loadCards() {
        for(int i=0; i<3; i++)
            mCards[i] = new ArrayList<>();
        CardsLoader loader = new CardsLoader();
        mCardsDeck[0] = loader.loadFirstLevelCards();
        mCardsDeck[1] = loader.loadSecondLevelCards();
        mCardsDeck[2] = loader.loadThirdLevelCards();

        // TODO add assertion for number of cards by level

        for(int i=0; i<3; i++){ // for each level
            for(int x=0; x<4; x++){ // draw 4 cards
                drawCardFromDeck(i);
            }
        }
    }

    private void loadNobles(int playersCount) {
        mNobles = new ArrayList<>();
        mNoblesDeck = new NoblesLoader().loadNobles();
        for(int i=0; i < playersCount+1 ; i++){ // draw (number_of_players + 1) nobles.
            drawNobleFromDeck();
        }
    }

    private void loadTokens(int playersCount) {
        int n = 0; // number of initial tokens for each color
        switch(playersCount) {
            case 4:
                n = 7; break;
            case 3:
                n = 5; break;
            case 2:
                n = 4; break;
        }

        mTokens = new TokensArray(n,n,n,n,n,5);
    }

    private boolean drawCardFromDeck(int level){
        if(mCardsDeck[level].size()==0)
            return false;
        Card card = mCardsDeck[level].get(0);
        mCardsDeck[level].remove(card);
        mCards[level].add(card);
        return true;
    }

    private void drawNobleFromDeck(){
        if(mNoblesDeck.size()==0)
            throw new IllegalStateException("tried to draw more nobles than available");
        Noble noble = mNoblesDeck.get(0);
        mNoblesDeck.remove(noble);
        mNobles.add(noble);
    }

    public boolean contains(Card card){
        return mCards[0].contains(card)
                || mCards[1].contains(card)
                || mCards[2].contains(card);
    }

    @Override
    public String toString() {
        StringBuilder description = new StringBuilder();
        description.append("Nobles:\n");
        for(Noble n : mNobles)
            description.append(n.toString()).append("\n");

        description.append("\nFirst level cards:\n");
        for(Card c : mCards[0])
            description.append(c.toString()).append("\n");
        description.append("\nSecond level cards:\n");
        for(Card c : mCards[1])
            description.append(c.toString()).append("\n");
        description.append("\nThird level cards:\n");
        for(Card c : mCards[2])
            description.append(c.toString()).append("\n");

        return description.toString();
    }
}
