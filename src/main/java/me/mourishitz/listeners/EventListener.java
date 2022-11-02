package me.mourishitz.listeners;

import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.List;

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
        List<Member> mentioned = event.getMessage().getMentions().getMembers();
        Member cuppy = event.getGuild().getMemberById("1035013322040426558");
        if(mentioned.contains(cuppy)){
            event.getMessage().reply("Digite '/' no chat para conhecer meus comandos").queue();
        }
    }
}
