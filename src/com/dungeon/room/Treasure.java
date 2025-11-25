package com.dungeon.room;

import java.util.HashMap;
import java.util.Objects;
import java.util.Optional;

import com.dungeon.hero.Position;
import com.dungeon.item.Curse;
import com.dungeon.item.Equipment;
import com.dungeon.item.Item;
import com.dungeon.item.Magic;
import com.dungeon.item.Weapon;

public final class Treasure implements Room{
	private final HashMap<Position, Equipment> equipments;
	private final Position position;
	private final int index;
	public Treasure(Position position , int index) {
		Objects.requireNonNull(position);
		this.index = index;
		this.equipments = new HashMap<>();
		this.position = position;
	}
	
	public void add(Position position , Equipment equipment) {
		Objects.requireNonNull(position);
		Objects.requireNonNull(equipment);
		equipments.put(position, equipment);

	}
	private static Item getItem(Equipment equipment) {
		Objects.requireNonNull(equipment);
		return switch (equipment) {
		case Curse c -> c;
		case Magic m -> m;
		case Weapon w -> w;
		
	
		};
	}
	
	public Optional<Item> takeTreasure(Position position) {
		Objects.requireNonNull(position);
		var equipment = equipments.getOrDefault(position, null);
		equipments.remove(position);
		return Optional.ofNullable(getItem(equipment));

	}

	@Override
	public Position position() {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public int index() {
		// TODO Auto-generated method stub
		return index;
	}


	
}
