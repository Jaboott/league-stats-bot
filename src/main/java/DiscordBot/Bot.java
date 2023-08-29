package DiscordBot;

import LeagueObjects.LeaguePlayer;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;
import utility.KeyHandler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class Bot extends ListenerAdapter {

    LeaguePlayer currPlayer;

    public static void main(String[] args) {
        Bot bot = new Bot();
        bot.initializeBot();
    }
    @Override
    public void onReady(ReadyEvent event) {
        System.out.println("Bot is logged in");
    }
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) return;
        Message message = event.getMessage();
        String content = message.getContentRaw();
        MessageChannel channel = event.getChannel();
        if (content.startsWith("!searchPlayer")) {
            currPlayer = new LeaguePlayer(content.replaceFirst("!searchPlayer", ""));
            channel.sendMessage(currPlayer.toString()).queue();
        } else if (content.startsWith("!recentGames")) {
            ArrayList<String> tempMatchList = currPlayer.getMatchHistory(3, false);
            for (String match : tempMatchList) {
                channel.sendMessage(match).queue();
            }
        } else if (content.startsWith("!detailedRecentGames")) {
            ArrayList<String> tempMatchList = currPlayer.getMatchHistory(3, true);
            for (String match : tempMatchList) {
                channel.sendMessage(match).queue();
            }
        }
    }

    private void initializeBot() {
        JDABuilder jdaBuilder = null;
        try {
            jdaBuilder = JDABuilder.createDefault(KeyHandler.getKey("DiscordBotKey"));
            jdaBuilder.addEventListeners(this);
            jdaBuilder.enableIntents(GatewayIntent.MESSAGE_CONTENT);
            jdaBuilder.build();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
