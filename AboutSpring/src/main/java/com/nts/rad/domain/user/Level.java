package com.nts.rad.domain.user;

public enum Level {

//	BASIC(1), SILVER(2), GOLD(3);
	GOLD(4, null), SILVER(3, GOLD), BRONZE(2, SILVER), BASIC(1, BRONZE);
	
	private final int value;
	private final Level nextLevel;
	
	Level(int value, Level nextLevel) {
		
		this.value = value;
		this.nextLevel = nextLevel;
	
	}
	
	public int intValue() {
		
		return value;
	
	}
	
	public Level getNextLevel() {
		
		return this.nextLevel;
	
	}
	
	public static Level valueOf(int value) {
		
		switch (value) {
		
			case 1: return BASIC;
			case 2: return BRONZE;
			case 3: return SILVER;
			case 4: return GOLD;
			default: throw new AssertionError("Unknown Value: " + value);
		
		}
	
	}

}
