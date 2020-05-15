package com.github.xathviar.Ultimate_TTT;

import com.github.xathviar.Ultimate_TTT.commands.*;
import com.github.xathviar.Ultimate_TTT.listeners.ReadyListener;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.OnlineStatus;

import javax.security.auth.login.LoginException;
import javax.swing.*;

public class Handler {

    public static void main(String[] args) throws LoginException, InterruptedException {
        String token;
        if (args.length > 0) {
            token = args[0];
        } else {
            token = JOptionPane.showInputDialog("Enter your Discord Token");
        }
        try {
            new Handler(token);
        } catch (Exception e) {
            token = JOptionPane.showInputDialog("Enter your Discord Token");
            new Handler(token);
        }
    }

    /**
     * Creates the bot and adds all Commands/Listeners
     * @param token the user-token which the bot uses
     * @throws InterruptedException see code
     * @throws LoginException if the user-token doesn't match
     */
    private Handler(String token) throws LoginException, InterruptedException {
        PlayField playField = new PlayField();
        char prefix = ',';
        JDA jda = new JDABuilder(token)
                .setStatus(OnlineStatus.ONLINE)
                .setGame(net.dv8tion.jda.core.entities.Game.playing("Ultimate TicTacToe"))
                .setAutoReconnect(true)
                .setAudioEnabled(false)
                .addEventListener(new ReadyListener())
                .addEventListener(new Challenge(playField, prefix))
                .addEventListener(new TileCommand(playField, prefix))
                .addEventListener(new Stop(playField, prefix))
                .addEventListener(new GetMessage(playField))
                .addEventListener(new View(playField, prefix))
                .addEventListener(new Remove(playField))
                .build();
        jda.awaitReady();
    }
}
