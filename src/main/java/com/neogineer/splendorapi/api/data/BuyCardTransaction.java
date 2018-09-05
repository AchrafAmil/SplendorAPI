package com.neogineer.splendorapi.api.data;

import com.neogineer.splendorapi.api.ReadOnlyPlayerState;

import static com.neogineer.splendorapi.api.data.Color.*;

public class BuyCardTransaction extends Transaction {

    public final Card mCardToBuy;
    public final TokensArray mCost;

    /**
     *
     * @param cardToBuy
     * @param cost
     * @throws IllegalArgumentException if the specified cost is either more or less than needed, jokers inferred.
     */
    public BuyCardTransaction(Card cardToBuy, TokensArray cost){
        mCardToBuy = cardToBuy;
        mCost = cost;
        checkCorrectness();
    }

    /**
     * checks that mCost matches exactly the card's cost, jokers inferred.
     */
    private void checkCorrectness() {
        boolean ok = true;
        TokensArray tokensDiff = mCardToBuy.cost.difference(mCost);
        if(!tokensDiff.isZero()){
            // now check that jokers (gold) can fill the gap
            int jokers = mCost.getCount(GOLD);
            for(Color color: Color.values()){
                int cardCost = mCardToBuy.cost.getCount(color);
                int cost = mCost.getCount(color);
                int diff = cardCost - cost;
                if(diff == 0)
                    continue;
                if(diff < 0)
                    throw new IllegalArgumentException("specified more tokens than needed for color: "+color);
                // use a joker to fill this gap
                jokers -= diff;
            }
            if(jokers!=0)
                throw new IllegalArgumentException("number of gold specified is "
                        +((jokers>0)? "more":"less")+ " than needed");

        }
    }

    @Override
    public boolean canBeIssuedBy(ReadOnlyPlayerState playerState) {
        return playerState.getTokens().difference(this.mCost).isPositive(); // mCost can be paid by player
    }
}
