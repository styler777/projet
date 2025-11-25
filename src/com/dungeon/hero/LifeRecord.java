package com.dungeon.hero;

import java.util.Objects;

public record LifeRecord(int health , int protection , int gold , int mana , int experience, int level , int energy , boolean canAttack ) implements StatHero{
	public LifeRecord {
		if(health < 0 || protection < 0 || gold < 0 || mana < 0 || experience < 0 || level < 0 || energy < 0)
			throw new IllegalArgumentException();
	}
	
	public LifeRecord modifyHealth(Stat stat , int amount , boolean allow) {
		Objects.requireNonNull(stat);
		return switch (stat) {
			case HEALTH -> new LifeRecord(health + amount , protection ,  gold ,  mana ,  experience, level , energy , canAttack); 
			case PROTECTION ->new LifeRecord(health  , protection + amount ,  gold ,  mana ,  experience, level , energy , canAttack);
			case GOLD ->new LifeRecord(health , protection ,  gold + amount,  mana ,  experience, level  , energy , canAttack);
			case MANA ->new LifeRecord(health , protection ,  gold ,  mana ,  experience, level  , energy , canAttack);
			case EXPERIENCE ->new LifeRecord(health , protection ,  gold ,  mana ,  experience + amount , level , energy ,canAttack);
			case LEVEL ->new LifeRecord(health, protection ,  gold ,  mana ,  experience, level  + amount  , energy , canAttack);
			case ENERGY -> new LifeRecord(health, protection ,  gold ,  mana ,  experience, level , energy + amount , canAttack);
			case ALLOW -> new LifeRecord(health, protection ,  gold ,  mana ,  experience, level , energy + amount , allow);
		};
	}
	public LifeRecord levelUp(int statingExperience) {
		
		return switch (experience) {
			case 0 -> new LifeRecord(health, protection,gold,mana, statingExperience * level, level , energy , canAttack);
			default -> this;
		
		};

	}
	
	
	
}
