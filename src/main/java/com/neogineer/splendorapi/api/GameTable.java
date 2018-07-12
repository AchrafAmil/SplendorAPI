package com.neogineer.splendorapi.api;

import com.neogineer.splendorapi.api.data.Card;
import com.neogineer.splendorapi.api.data.Noble;
import com.neogineer.splendorapi.api.data.TokensArray;

import java.util.List;

class GameTable {

    private List<Noble> mNoblesDeck;
    List<Noble> mNobles;

    // for the three levels
    private List<Card>[] mCardsDeck = new List[3];
    private List<Card>[] mCards = new List[3];

    TokensArray mTokens;

    public GameTable(int playersCount) {
        loadCards();
    }

    private void loadCards() {
        CardsLoader loader = new CardsLoader();
        mCardsDeck[0] = loader.loadFirstLevelCards();
        mCardsDeck[1] = loader.loadSecondLevelCards();
        mCardsDeck[2] = loader.loadThirdLevelCards();

        // TODO add assertion for number of cards by level

        for(int i=0; i<3; i++){ // for each level
            for(int x=0; i<4; i++){ // draw 4 cards
                drawCardFromDeck(i);
            }
        }
    }

    private boolean drawCardFromDeck(int level){
        if(mCardsDeck[level].size()==0)
            return false;
        Card card = mCardsDeck[level].get(0);
        mCardsDeck[level].remove(card);
        mCards[level].add(card);
        return true;
    }
}
