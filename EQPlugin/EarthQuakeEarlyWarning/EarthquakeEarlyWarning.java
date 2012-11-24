package jp.sourceforge.user.yykimoto.EarthQuakeEarlyWarning;

import java.util.logging.Logger;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import twitter4j.*;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import twitter4j.conf.Configuration;

public class EarthquakeEarlyWarning extends JavaPlugin {
	Logger log;
	Twitter t;
	TwitterStream ts;
	StatusListener listener;
	RequestToken rt;
	AccessToken at = null;
	String pin;

	public void onEnable() {
		listener = new StatusListener() {
			@Override
			public void onStatus(Status status) {
				if (status.getUser().getScreenName() == "eew_jp") {
					log.info(status.getText());
				}
			}

			@Override
			public void onException(Exception ex) {
				log.info("Error:" + ex);
			}

			@Override
			public void onDeletionNotice(
					StatusDeletionNotice statusDeletionNotice) {
			}

			@Override
			public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
			}

			@Override
			public void onScrubGeo(long userId, long upToStatusId) {
				// TODO 自動生成されたメソッド・スタブ

			}

			@Override
			public void onStallWarning(StallWarning warning) {
				// TODO 自動生成されたメソッド・スタブ

			}
		};
		t = TwitterFactory.getSingleton();
		if (at != null) {
			ts = new TwitterStreamFactory((Configuration) listener)
					.getInstance(at);
			ts.sample();
		}
		t.setOAuthConsumer("nqhQhAZBMzrfYaJWOEry0g",
				"75u3x0zJaEbGloVqCBYOw99efDcErP9JZcOQzC3YBk");
		log = this.getLogger();
		log.info("EEW plugin has been enabled!");

	}

	public boolean onCommand(CommandSender sender, Command cmd,
			String commandLabel, String[] args) {
		Player player = null;
		if (sender instanceof Player) {
			player = (Player) sender;
		}

		if (cmd.getName().equalsIgnoreCase("oautheew")) {
			if (player == null || player.isOp()) {
				if (args[0] == "p") {
					try {
						rt = t.getOAuthRequestToken();
						log.info(rt.getAuthorizationURL());
					} catch (TwitterException e) {
						log.info("Error:" + e);
					}
				} else {
					pin = args[0];
					try {
						at = t.getOAuthAccessToken(rt, pin);
					} catch (TwitterException e) {
						log.info("Error:" + e);
					}

				}
			}
			return true;
		}
		return false;

	}
}
