package me.mourishitz.listeners;

import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.events.user.update.UserUpdateOnlineStatusEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

// Lista de eventos JDA: https://jda.wiki/introduction/events-list/

public class EventListener extends ListenerAdapter {

    @Override
    public void onMessageReactionAdd(@NotNull MessageReactionAddEvent event) {
        User user = event.getUser();
        String emoji = event.getReaction().getReactionEmote().getAsReactionCode();


        assert user != null;
        if(emoji.equals("☕")){
            String message = "Valeu " + user.getAsMention() + " por fazer café!";
            event.getTextChannel().sendMessage(message).queue();
        }
    }

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        String message = event.getMessage().getContentRaw();
        if(message.equals("c.ping")){
            event.getChannel().sendMessage("pong").queue();
        }
    }

    @Override
    public void onUserUpdateOnlineStatus(@NotNull UserUpdateOnlineStatusEvent event) {
        User user = event.getUser();
        String message = "Olha o " + user.getAsTag() + " mudando  para " + event.getNewOnlineStatus().name();
        Objects.requireNonNull(event.getGuild().getDefaultChannel()).sendMessage(message).queue();

    }
}
