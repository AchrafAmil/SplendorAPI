package com.neogineer.splendorapi.api.data;

public class Card {

    public final int cardId;
    public final int color;
    public final int points;
    public final TokensArray cost;

    public Card(int cardId, int color, int points, TokensArray cost) {
        if(points < 0 || color > 5)
            throw new IllegalArgumentException();
        this.cardId = cardId;
        this.color = color;
        this.points = points;
        this.cost = cost;
    }
}
