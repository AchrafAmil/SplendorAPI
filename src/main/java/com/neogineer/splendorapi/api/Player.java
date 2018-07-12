package com.neogineer.splendorapi.api;

import com.neogineer.splendorapi.api.data.Noble;
import com.neogineer.splendorapi.api.data.TokensArray;

import java.util.ArrayList;
import java.util.List;

/**
 * This is the class you need to override to define a player.
 *
 * Override abstract methods implementing your own game strategy.
 *
 */
public abstract class Player {

    public final String playerName;
    private ReadOnlyGameTable mGameTable;
    private ReadOnlyPlayerState mMyState;
    private List<ReadOnlyPlayerState> mOpponents = new ArrayList<>();

    public Player(String playerName) {
        this.playerName = playerName;
    }

    final void setGameTable(ReadOnlyGameTable gameTable) {
        this.mGameTable = gameTable;
    }

    final void setMyState(ReadOnlyPlayerState myState) {
        this.mMyState = myState;
    }

    final void introduceOpponent(ReadOnlyPlayerState opponent){
        mOpponents.add(opponent);
        if(mOpponents.size()>3)
            throw new IllegalStateException("Player can't have more than 3 opponents");
    }

    /**
     * here you implement your game strategy.
     *
     * gets called once by round.
     *
     * @return a transaction. Buying a card or collecting tokens for instance.
     * You need to make sure the transaction is feasible and do respect the rules,
     * or it's just gonna get ignore and you'll miss your turn!
     */
    public abstract void yourTurn();

    /**
     * override with your own strategy.
     *
     * gets called if you're eligible for more than one noble within the same turn
     * @param affordableNobles list of noble you're eligible to earn
     * @return one of these
     */
    public abstract Noble chooseNoble(List<Noble> affordableNobles);

    /**
     * here you decide which tokens to return.
     *
     * gets called each time you exceed 10 tokens.
     *
     * @param tokensToReturn number of tokens you need to return (= tokens you hold - 10)
     * @return TokensArray representing the number of tokens you wanna return for each color.
     */
    public abstract TokensArray returnTokens(int tokensToReturn);

}
