package com.pheonix.core.utils.enums;

public enum ReferralTransactionStatus {

	SHARED, // Referral Link is shared
	REDEEMED, // Referral has been redeemed successfully
	AWAITING_REVIEW, // Referral has been redeemed and pending confirmation
	CONFIRMED,//Confirmation is done but amount yet to be credited.
	DONE,//This is done

}
