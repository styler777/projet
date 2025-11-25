package com.dungeon.hero;

import java.util.Objects;

public record BodyCurse(int damage , Stat stat, int round , int intensity , boolean startRound, boolean active) implements StatHero{
	public BodyCurse {
		Objects.requireNonNull(stat);
		if(damage < 0 || round < 0 || intensity < 0)
			throw new IllegalArgumentException();
		
	}
	
	public BodyCurse modifyActiveBodyCurse(boolean activeBody) {
		return new BodyCurse(damage, stat,round, intensity , startRound, activeBody);

	}
	
	public BodyCurse modifyIntensityeBodyCurse() {
		return new BodyCurse(damage *intensity + 1 , stat  , round, intensity + 1 , startRound, active);

	}
	
	public LifeRecord modifyLifeRecord(LifeRecord lifeRecord) {
		Objects.requireNonNull(lifeRecord);
		return lifeRecord.modifyHealth(stat, damage, true);
	}
	return 0;
}
