package com.pheonix.core.utils.enums;

public enum DeviceStatus {
	LIVE, // A Device that is added
	REMOVED, // A Device that has been removed
	SENT_FOR_REPAIR, // A Device that is being repaired right now
	SOLD, // A Device that has been sold
	BROKEN, // A Device that has been broken and cannot be repaired
	RECYCLED, // A Device that has been recycled properly
}
