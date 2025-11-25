package com.dungeon.room;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.dungeon.hero.Position;

public record Corridor(List<Room> adjacentRooms , Position position , int index) implements Room{
	public Corridor {
		Objects.requireNonNull(adjacentRooms);
	}
	
	private static boolean isCorridor(Room room) {
		Objects.requireNonNull(room);
		return switch (room) {
		case Corridor _ -> true;
		default -> false;
		
		
		
		};

	}
	public Optional<Room> nextCorridor() {
		return adjacentRooms.stream().filter(r -> isCorridor(r)).findFirst();
		

	}
	
	
}
