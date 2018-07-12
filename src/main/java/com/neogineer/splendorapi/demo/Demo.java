package com.neogineer.splendorapi.demo;

import com.neogineer.splendorapi.api.GameMaster;
import com.neogineer.splendorapi.api.Player;
import com.neogineer.splendorapi.api.data.Card;
import com.neogineer.splendorapi.api.data.Noble;
import com.neogineer.splendorapi.api.data.TokensArray;

import java.util.List;

public class Demo {

    public static void main(String[] args){



        GameMaster myGame = new GameMaster();

        myGame.register(new Achraf("achraf"));
        myGame.register(new Kevin("kevin"));

        myGame.start();
    }


}

class Achraf extends Player {

    Achraf(String playerName) {
        super(playerName);
    }

    @Override
    public void yourTurn() {

    }

    @Override
    public Noble chooseNoble(List<Noble> affordableNobles) {
        return null;
    }

    @Override
    public TokensArray returnTokens(int tokensToReturn) {
        return null;
    }
}

class Kevin extends Player {

    Kevin(String playerName) {
        super(playerName);
    }

    @Override
    public void yourTurn() {

    }

    @Override
    public Noble chooseNoble(List<Noble> affordableNobles) {
        return null;
    }

    @Override
    public TokensArray returnTokens(int tokensToReturn) {
        return null;
    }
}