package com.dungeon.room;

import java.util.Objects;
import java.util.Optional;

import com.dungeon.hero.BodyCurse;
import com.dungeon.hero.Inventory;
import com.dungeon.hero.LifeRecord;
import com.dungeon.hero.Position;
import com.dungeon.hero.Stat;
import com.dungeon.hero.StatHero;
import com.dungeon.item.Item;


public record Healer(int care , Position position , int index) implements Room{
	public Healer {
		if(care < 0)
			throw new IllegalArgumentException();
	}
	public Optional<StatHero> applyCare(StatHero stat) {
		Objects.requireNonNull(stat);
	
		return switch (stat) {
			case LifeRecord l -> Optional.ofNullable(l.modifyHealth(Stat.HEALTH, care , true));
			case Inventory _ -> Optional.ofNullable(null);
			case BodyCurse b -> Optional.ofNullable(b.modifyActiveBodyCurse(false));
	
	
		};
		
	}
	
	public static Inventory applyInventory(Inventory inventory , Item item) {
		Objects.requireNonNull(inventory);
		Objects.requireNonNull(item);
		inventory.removeItem(item);
		return inventory;

	}
}
