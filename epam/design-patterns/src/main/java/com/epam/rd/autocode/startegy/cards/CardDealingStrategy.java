package com.epam.rd.autocode.startegy.cards;

import java.util.*;
import java.util.stream.IntStream;

public interface CardDealingStrategy {

    int TEXAS_HOLDEM_POKER_CARDS_PER_PLAYER = 2;
    int CLASSIC_POKER_CARDS_PER_PLAYER = 5;
    int BRIDGE_CARDS_PER_PLAYER = 13;
    int FOOL_CARDS_PER_PLAYER = 6;
    int COMMUNITY_STACK_SIZE = 5;

    Map<String, List<Card>> dealStacks(Deck deck, int players);

    static CardDealingStrategy texasHoldemCardDealingStrategy() {
        return (deck, players) -> {
            Map<String, List<Card>> cardStack = new HashMap<>();
            if (players > 0) {
                for (int round = 1; round <= TEXAS_HOLDEM_POKER_CARDS_PER_PLAYER; round++) {
                    IntStream.range(0, players).forEach(i ->
                            cardStack.computeIfAbsent(String.format("Player %d", i+1), k -> new ArrayList<>()).add(deck.dealCard()));
                }
            }

            List<Card> communityCardList = new ArrayList<>();
            IntStream.range(0, COMMUNITY_STACK_SIZE).forEach(i -> communityCardList.add(deck.dealCard()));
            cardStack.put("Community", communityCardList);

            if (deck.size() >
                    ((TEXAS_HOLDEM_POKER_CARDS_PER_PLAYER * players) + COMMUNITY_STACK_SIZE)) {
                cardStack.put("Remaining", deck.restCards());
            }

            return cardStack;
        };
    }

    static CardDealingStrategy classicPokerCardDealingStrategy() {
        return (deck, players) -> {
            Map<String, List<Card>> cardStack = new HashMap<>();
            if (players > 0) {
                for (int round = 1; round <= CLASSIC_POKER_CARDS_PER_PLAYER; round++) {
                        IntStream.range(0, players).forEach(i ->
                                cardStack.computeIfAbsent(String.format("Player %d", i+1), k -> new ArrayList<>()).add(deck.dealCard()));
                }
            }
            cardStack.put("Remaining", deck.restCards());
            return cardStack;
        };
    }

    static CardDealingStrategy bridgeCardDealingStrategy() {
        return (deck, players) -> {
            Map<String, List<Card>> cardStack = new HashMap<>();
            if (players > 0) {
                for (int round = 1; round <= BRIDGE_CARDS_PER_PLAYER; round++) {
                    IntStream.range(0, players).forEach(i ->
                            cardStack.computeIfAbsent(String.format("Player %d", i+1), k -> new ArrayList<>()).add(deck.dealCard()));
                }
            }
            if (deck.size() > (BRIDGE_CARDS_PER_PLAYER * players)) {
                cardStack.put("Remaining", deck.restCards());
            }
            return cardStack;
        };
    }


    static CardDealingStrategy foolCardDealingStrategy() {
        return (deck, players) -> {
            Map<String, List<Card>> cardStack = new HashMap<>();
            if (players > 0) {
                for (int round = 1; round <= FOOL_CARDS_PER_PLAYER; round++) {
                    IntStream.range(0, players).forEach(i ->
                            cardStack.computeIfAbsent(String.format("Player %d", i+1), k -> new ArrayList<>()).add(deck.dealCard()));
                }
            }
            cardStack.put("Trump card", Collections.singletonList(deck.dealCard()));
            cardStack.put("Remaining", deck.restCards());
            return cardStack;
        };
    }
}
