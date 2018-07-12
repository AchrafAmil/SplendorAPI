package com.neogineer.splendorapi.api.data;

public class Card {

    public final int cardId;
    public final Color color;
    public final int points;
    public final TokensArray cost;

    public Card(int cardId, Color color, int points, TokensArray cost) {
        if(points < 0)
            throw new IllegalArgumentException("card points can't be negative");
        this.cardId = cardId;
        this.color = color;
        this.points = points;
        this.cost = cost;
    }

    @Override
    public String toString() {
        return color.toString()+" points:"+points+" id:"+cardId
                +"\ncost: "+cost.toString();
    }
}
