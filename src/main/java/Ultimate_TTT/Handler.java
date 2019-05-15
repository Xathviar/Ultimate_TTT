package Ultimate_TTT;

import Ultimate_TTT.commands.Challenge;
import Ultimate_TTT.commands.TileCommand;
import Ultimate_TTT.commands.View;
import Ultimate_TTT.listeners.ReadyListener;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.OnlineStatus;

import javax.security.auth.login.LoginException;

public class Handler {
    JDA jda;

    public static void main(String[] args) throws LoginException, InterruptedException {
        String token = "NTA5MDg4NTM0ODQ2NTA0OTYx.XNqexA.fChJEb43CCMYKt7e5nDoVYXutDA";
        new Handler(token);
    }

    private Handler(String token) throws InterruptedException, LoginException {
        PlayField playField = new PlayField();
        Character prefix = '.';
        this.jda =  new JDABuilder(token)
                .setStatus(OnlineStatus.ONLINE)
                .setGame(net.dv8tion.jda.core.entities.Game.playing("Ultimate TicTacToe"))
                .setAutoReconnect(true)
                .setAudioEnabled(false)
                .addEventListener(new ReadyListener())
                .addEventListener(new Challenge(playField, prefix))
                .addEventListener(new TileCommand(playField, prefix))
                .build();
        jda.awaitReady();
    }
}
