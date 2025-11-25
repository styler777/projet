package com.dungeon.item;

import java.util.List;
import java.util.Objects;

import com.dungeon.hero.Position;

public record Gold(int gold , String name , int size) implements Item{
	public Gold {
		Objects.requireNonNull(name);
		if(gold < 0 || size < 0)
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
		return new Gold(gold , name , size);
	}

}
