package com.dungeon.enemy;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.dungeon.hero.LifeRecord;
import com.dungeon.hero.Position;
import com.dungeon.hero.Stat;
import com.dungeon.hero.StatHero;
import com.dungeon.item.Curse;
import com.dungeon.item.Equipment;
import com.dungeon.item.Item;
import com.dungeon.item.Magic;
import com.dungeon.item.Weapon;

public record Enemy(List<AttackEnemy> attacks, Position position, int health  , int protection , Equipment equipment, int experience , boolean canAttack) {
	public Enemy {
		Objects.requireNonNull(attacks);
		Objects.requireNonNull(equipment);
		if(experience < 0 || health < 0 || protection < 0)
			throw new IllegalArgumentException();
		Objects.requireNonNull(position);
	}
	
	private static Item convertItem(Equipment equipment) {
		Objects.requireNonNull(equipment);
		return switch (equipment) {
			case Magic m -> m;
			case Weapon w -> w;
			case Curse c -> c;
		
		};

	}
	
	public Optional<Item> rewardToHero() {
		if(health == 0)
			return Optional.ofNullable(convertItem(equipment));
		return Optional.empty();
	}
	
	
	
	public Enemy modifyHealthEnemy(Stat stat , int amount , boolean allow) {
		Objects.requireNonNull(stat);
		if(amount < 0)
			throw new IllegalArgumentException();
		
		return switch (stat) {
			case HEALTH -> new Enemy(attacks , position , health - amount, protection , equipment , experience , canAttack);
			case PROTECTION -> new Enemy(attacks , position , health, protection - amount , equipment , experience, canAttack);
			case ALLOW -> new Enemy(attacks , position , health - amount, protection , equipment , experience , allow);
			default ->
			throw new IllegalArgumentException("Unexpected value: " + stat);
		};

	}
	
	public static LifeRecord modifyHealthHero(Stat stat , LifeRecord lifeRecord, Attack attack) {
		Objects.requireNonNull(stat);
		Objects.requireNonNull(attack);
		Objects.requireNonNull(lifeRecord);
		return (lifeRecord.protection() > 0)? lifeRecord.modifyHealth(Stat.PROTECTION, attack.damage() , true) : lifeRecord.modifyHealth(Stat.HEALTH, attack.damage() ,true);
		

	}
}
