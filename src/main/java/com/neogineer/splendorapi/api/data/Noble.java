package com.neogineer.splendorapi.api.data;

public class Noble {
    public final int nobleId;
    public final int points;
    public final TokensArray cost;

    public Noble(int nobleId, int points, TokensArray cost) {
        if(points < 0)
            throw new IllegalArgumentException();
        this.nobleId = nobleId;
        this.points = points;
        this.cost = cost;
    }
}
