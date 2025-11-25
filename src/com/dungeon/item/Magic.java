package com.dungeon.item;

import java.util.List;
import java.util.Objects;

import com.dungeon.combat.CombatEffect;
import com.dungeon.hero.Position;

public record Magic(String name ,int damage , CombatEffect combatEffect, int energy , int size , int price , boolean block) implements Item , Equipment{
	public Magic {
		Objects.requireNonNull(name);
		Objects.requireNonNull(combatEffect);
		if(damage < 0 || energy < 0 || size < 0 || price < 0)
			throw new IllegalArgumentException();
	}


	@Override
	public List<Position> getItemsPosition(Position current) {
		Objects.requireNonNull(current);
		var left = new Position(current.x() + size, current.y());
		var down = new Position(current.x(), current.y() + size);
		var downLeft = new Position(current.x() + size,current.y() + size);
		return List.of(current , left , down , downLeft);
	}


	@Override
	public Item blockItem() {
		// TODO Auto-generated method stub
		return new Magic(name ,damage , combatEffect, energy , size , price , true) ;
	}

}
