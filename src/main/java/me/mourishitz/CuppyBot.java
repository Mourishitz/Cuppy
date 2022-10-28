package me.mourishitz;

import io.github.cdimascio.dotenv.Dotenv;
import me.mourishitz.listeners.EventListener;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;

import javax.security.auth.login.LoginException;


public class CuppyBot {

    private final Dotenv config;
    private final ShardManager shardManager;

    public CuppyBot() throws LoginException {
        config = Dotenv.configure().load();
        String token = config.get("TOKEN");
        DefaultShardManagerBuilder builder = DefaultShardManagerBuilder.createDefault(token);
        builder.setStatus(OnlineStatus.ONLINE);
        builder.setActivity(Activity.playing("☕"));
        builder.enableIntents(GatewayIntent.GUILD_MEMBERS, GatewayIntent.GUILD_MESSAGES, GatewayIntent.GUILD_PRESENCES);
        shardManager = builder.build();

        shardManager.addEventListener(new EventListener());
    }

    public Dotenv getConfig(){
        return config;
    }

    public ShardManager getShardManager() { return shardManager; }

    public static void main(String[] args) {
        try {
            CuppyBot bot = new CuppyBot();
        } catch (LoginException e) {
            System.out.println("ERRO: Token inválido!");
        }
    }
}