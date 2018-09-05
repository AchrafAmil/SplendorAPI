package com.neogineer.splendorapi.api.data;

import java.util.HashMap;
import java.util.Map;

/**
 * Associates an int to each of colors specified at {@link Color}.
 * Use {@link this#getCount(Color)} to get the int for each color.
 *
 * This can be used to represent the number of tokens
 * or the number of cards (by color) a player has, or to represent the
 * cost of a card or a noble.
 */
public class TokensArray {

    // was an Array, now it's a Map, but things are abstract enough that it doesn't matter :-)
    private Map<Color, Integer> mTokens = new HashMap<>(6);

    public TokensArray() {
        for (Color color :
                Color.values()) {
            mTokens.put(color, 0);
        }
    }

    public TokensArray(int red, int green, int blue, int black, int white, int gold) {
        mTokens.put(Color.RED, red);
        mTokens.put(Color.GREEN, green);
        mTokens.put(Color.BLUE, blue);
        mTokens.put(Color.BLACK, black);
        mTokens.put(Color.WHITE, white);
        mTokens.put(Color.GOLD, gold);
    }

    /**
     * This is mainly used to parse cards' cost from a file
     * Please notice that gold is optional.
     *
     * @param input should match the pattern:
     *             "red green blue black white[ gold]"
     *             so something like "2 0 2 1 1"
     */
    public static TokensArray fromString(String input){
        String[] parts = input.split(" ");
        boolean hasGold = (parts.length>5 && parts[5].length()>0);
        return new TokensArray(Integer.valueOf(parts[0]),
                Integer.valueOf(parts[1]),
                Integer.valueOf(parts[2]),
                Integer.valueOf(parts[3]),
                Integer.valueOf(parts[4]),
                hasGold?
                        Integer.valueOf(parts[5]) : 0 ); // gold is optional
    }

    public int getCount(Color color){
        return mTokens.get(color);
    }

    @Override
    public String toString() {
        String result = "";
        for(Color color: Color.values()){
            result += color.name()+":"+mTokens.get(color)+" ";
        }
        return result;
    }

    public TokensArray difference(TokensArray other){
        TokensArray result = new TokensArray();
        for(Color color: Color.values()){
            int value = this.getCount(color)-other.getCount(color);
            result.mTokens.put(color, value);
        }
        return result;
    }

    public boolean isPositive(){
        for(Color color: Color.values()){
            if(this.getCount(color)<0)
                return false;
        }
        return true;
    }

    public boolean isZero(){
        for(Integer i : mTokens.values())
            if(i!=0)
                return false;
        return true;
    }

    public int getSum(){
        int result = 0;
        for(int i: mTokens.values())
            result += i;
        return result;
    }

    public TokensArray plus(TokensArray other){
        TokensArray result = new TokensArray();
        for(Color color: Color.values())
            result.mTokens.put(color, getCount(color)+other.getCount(color));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        TokensArray other = (TokensArray) obj;
        return this.difference(other).isZero();
    }

    public TokensArray copy(){
        TokensArray result = new TokensArray();
        result.mTokens.putAll(mTokens);
        return result;
    }
}
