package jp.sourceforge.user.yykimoto.EarthQuakeEarlyWarning;

import java.util.logging.Logger;
import org.bukkit.plugin.java.JavaPlugin;

import twitter4j.*;

public class EarthquakeEarlyWarning extends JavaPlugin {
	Logger log;
	Twitter t;
	TwitterStream ts;
	StatusListener listener;

	public void onEnable() {
		t = new TwitterFactory().getInstance();
		ts = new TwitterStreamFactory().getInstance();
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
		t.setOAuthConsumer("nqhQhAZBMzrfYaJWOEry0g",
				"75u3x0zJaEbGloVqCBYOw99efDcErP9JZcOQzC3YBk");
		log = this.getLogger();
		log.info("EEW plugin has been enabled!");

	}
}
