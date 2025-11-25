package com.dungeon.room;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import com.dungeon.hero.Position;
import com.dungeon.item.Equipment;

public record Merchand(Map<Position , Equipment> equipments , Position position , int index) implements Room{
	public Merchand{
		Objects.requireNonNull(equipments);
		Objects.requireNonNull(position);
	}
	public int buyEquipment(Position position, int gold) {
		if(gold < 0)
			throw new IllegalArgumentException();
		Objects.requireNonNull(position);
		Optional<Equipment> equipment = Optional.ofNullable(equipments.getOrDefault(position, null)); 
		int price = (equipment.isPresent())? equipment.get().price() : 0;
		return gold - price;

	}
}
