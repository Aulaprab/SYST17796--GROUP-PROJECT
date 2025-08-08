/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.unogame;


/**
 *
 * @author prabs
 */
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Player> players = new ArrayList<>();

        System.out.print("Enter number of players: ");
        int numPlayers = sc.nextInt();
        sc.nextLine(); 

        for (int i = 0; i < numPlayers; i++) {
            System.out.print("Enter name for Player " + (i+1) + ": ");
            String name = sc.nextLine();
            players.add(new Player(name));
        }

        GameController game = new GameController(players);
        game.startGame();
    }
}
