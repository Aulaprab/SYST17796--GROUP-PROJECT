/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.unogame;

/**
 *
 * @author prabs
 */
public class SpecialCard extends Card {
    private CardType type;

    public SpecialCard(String color, String value, CardType type) {
        super(color, value);
        this.type = type;
    }

    public void applyEffect(GameController game, Player currentPlayer) {
        switch (type) {
            case SKIP:
                game.skipNextPlayer();
                break;
            case REVERSE:
                game.reverseDirection();
                break;
            case DRAW_TWO:
                game.drawCardsToNextPlayer(2);
                break;
            case WILD:
                game.chooseColor(currentPlayer);
                break;
            case WILD_DRAW_FOUR:
                game.chooseColor(currentPlayer);
                game.drawCardsToNextPlayer(4);
                break;
            default:
                break;
        }
    }
}
