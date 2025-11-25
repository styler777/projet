package com.dungeon.hero;

import java.util.List;
import java.util.Objects;

public record Position(int x , int y) {
	public Position {
		if(x < 0 || y < 0)
			throw new IllegalArgumentException();
	}
	
	public boolean adjacentPosition(Position position) {
		Objects.requireNonNull(position);
		var list = List.of(new Position(-1 ,0) , new Position(1 ,0) , new Position(0 ,-1) , new Position(0 ,1));
		return list.stream().anyMatch(p -> x + p.x() == position.x() && y + p.y() == position.y());

	}
	
	
}
