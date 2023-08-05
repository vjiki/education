package com.epam.rd.autocode.startegy.cards;

public class CardDealingStrategies {
    public static CardDealingStrategy texasHoldemCardDealingStrategy() {
       return CardDealingStrategy.texasHoldemCardDealingStrategy();
    }

    public static CardDealingStrategy classicPokerCardDealingStrategy() {
        return CardDealingStrategy.classicPokerCardDealingStrategy();
    }

    public static CardDealingStrategy bridgeCardDealingStrategy(){
        return CardDealingStrategy.bridgeCardDealingStrategy();
    }

    public static CardDealingStrategy foolCardDealingStrategy(){
        return CardDealingStrategy.foolCardDealingStrategy();
    }

}
