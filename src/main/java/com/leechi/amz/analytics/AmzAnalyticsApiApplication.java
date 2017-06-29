package com.leechi.amz.analytics;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import java.io.PrintStream;

@SpringBootApplication
public class AmzAnalyticsApiApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(AmzAnalyticsApiApplication.class);
		app.setBanner(new Banner() {
			@Override
			public void printBanner(Environment environment, Class<?> aClass, PrintStream printStream) {
				printStream.print("\n" +
						".____                        .__    .__ \n" +
						"|    |    ____   ____   ____ |  |__ |__|\n" +
						"|    |  _/ __ \\_/ __ \\_/ ___\\|  |  \\|  |\n" +
						"|    |__\\  ___/\\  ___/\\  \\___|   Y  \\  |\n" +
						"|_______ \\___  >\\___  >\\___  >___|  /__|\n" +
						"        \\/   \\/     \\/     \\/     \\/    \n");
			}
		});
		app.run(args);
	}
}
