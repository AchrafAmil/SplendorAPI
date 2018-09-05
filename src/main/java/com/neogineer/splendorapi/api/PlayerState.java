package com.neogineer.splendorapi.api;

import com.neogineer.splendorapi.api.data.Card;
import com.neogineer.splendorapi.api.data.CardsArray;
import com.neogineer.splendorapi.api.data.TokensArray;

import java.util.LinkedList;
import java.util.List;

public class PlayerState {

    public final String playerName;
    CardsArray mCards = new CardsArray();
    TokensArray mTokens = new TokensArray();
    int points = 0;
    List<Card> bookedCards = new LinkedList<>();

    public PlayerState(String playerName) {
        this.playerName = playerName;
    }

    public TokensArray getCardsPlusTokens(){
        return mTokens.plus(mCards);
    }
}
