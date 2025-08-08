/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.unogame;

/**
 *
 * @author prabs
 */
public class Player {
    private String name;
    private Hand hand;

    public Player(String name) {
        this.name = name;
        this.hand = new Hand();
    }

    public String getName() {
        return name;
    }

    public Hand getHand() {
        return hand;
    }

    public void drawCard(Deck deck) {
        Card drawn = deck.drawCard();
        if (drawn != null) {
            hand.addCard(drawn);
        }
    }

    public void playCard(Card card, Deck discardPile) {
        hand.removeCard(card);
        discardPile.addCard(card);
    }
}
