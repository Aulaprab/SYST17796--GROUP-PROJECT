/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.unogame;

/**
 *
 * @author prabs
 */
import java.util.ArrayList;
import java.util.List;

public class Hand {
    private List<Card> cards;

    public Hand() {
        cards = new ArrayList<>();
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public void removeCard(Card card) {
        cards.remove(card);
    }

    public List<Card> getCards() {
        return cards;
    }

    public boolean hasPlayableCard(Card topCard) {
        for (Card c : cards) {
            if (c.getColor().equals(topCard.getColor()) || 
                c.getValue().equals(topCard.getValue()) || 
                c instanceof SpecialCard) {
                return true;
            }
        }
        return false;
    }
}
