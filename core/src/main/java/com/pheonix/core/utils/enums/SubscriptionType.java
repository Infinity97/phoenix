package com.pheonix.core.utils.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum SubscriptionType {

	OTT("Over the Top Entertainment"),
	EDUCATION("Knowledge and Learning"),
	MUSIC("Music and Podcasts"),
	NEWS("News and Journals"),
	LIFESTYLE("Lifestyle"),
	PRODUCTIVITY("Productivity"),
	HEALTH("Health Wellness and Fitness"),
	SECURITY("Antivirus & VPN"),
	OFFLINE("Custom Subscriptions"),
	APP("Mobile Apps And Gaming"),
	OTHER("Other")
	;

	private String description;
	private String details;

	SubscriptionType(String details) {
		this.details = details;
	}
}
