package com.dungeon.enemy;

import java.util.Objects;

import com.dungeon.hero.LifeRecord;
import com.dungeon.hero.Stat;


public record Attack(int damage , String name) implements AttackEnemy {
	public Attack {
		Objects.requireNonNull(name);
		if(damage < 0)
			throw new IllegalArgumentException();
	}
	
	public LifeRecord InflictDamage(LifeRecord lifeRecord) {
		Objects.requireNonNull(lifeRecord);
		return switch (lifeRecord.protection()) {
			case 0 -> lifeRecord.modifyHealth(Stat.HEALTH, damage , true);
			default -> lifeRecord.modifyHealth(Stat.PROTECTION, damage, true);
		};
	}
	
	
	
}
