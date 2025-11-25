package com.dungeon.Floor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.dungeon.hero.Position;
import com.dungeon.room.Corridor;
import com.dungeon.room.Room;

public class Floor {
	private final HashMap<Integer, ArrayList<Room>> floors;
	private final int maxFloor;
	public Floor(int numberFloor) {
		if(numberFloor < 0)
			throw new IllegalArgumentException();
		this.floors = new HashMap<>();
		this.maxFloor = numberFloor;
	}
	
	public void add(int number , List<Room> rooms) {
		Objects.requireNonNull(rooms);
		if(number < 0)
			throw new IllegalArgumentException();
		floors.put(number, new ArrayList<>(rooms));

	}
	
	public Optional<Room> visitRoom(int numberFloor) {
		if(numberFloor < 0)
			throw new IllegalArgumentException();
		var list = floors.getOrDefault(numberFloor, new ArrayList<>());
		if(list.isEmpty()) {
			list = floors.getOrDefault(numberFloor + 1, new ArrayList<>());
			var room = list.remove(numberFloor);
			floors.put(numberFloor, list);
			return Optional.ofNullable(room);
			
		}
		var room = list.remove(numberFloor);
		floors.put(numberFloor, list);
		return Optional.ofNullable(room);

	}
	
	
	private static Optional<Corridor> isCorridor(Optional<Room> room) {
		Objects.requireNonNull(room);
		if(room.isEmpty())
			return Optional.ofNullable(null);
		
		return switch (room.get()) {
		case Corridor c -> Optional.of(c);
		default -> Optional.ofNullable(null);
		
		
		
		};

	}
	
	
	private List<Room> corridorFloor(int numberFloor) {
		if(numberFloor < 0)
			throw new IllegalArgumentException();
		return floors.getOrDefault(numberFloor, new ArrayList<>()).stream().filter(r -> isCorridor(Optional.ofNullable(r)).isPresent()).toList();

	}
	
	
	public List<Optional<Corridor>> shortcut(int numberFloor , Corridor corridor) {
		if(numberFloor < 0)
			throw new IllegalArgumentException();
		Objects.requireNonNull(corridor);
		var list = new ArrayList<Optional<Corridor>>();
		list.add(Optional.ofNullable(corridor));
		corridorFloor(numberFloor).forEach(_ -> {list.getLast().ifPresent(c -> list.add(isCorridor(c.nextCorridor())));});
		return List.copyOf(list);
	}
	
	private Optional<Corridor> lastCorridorInShortcut(List<Optional<Corridor>> corridors) {
		Objects.requireNonNull(corridors);
		return corridors.reversed().stream().filter(c -> c.isPresent()).findFirst().get();

	}
	
	
	public void removeShortcut(int numberFloor , Corridor corridor) {
		if(numberFloor < 0)
			throw new IllegalArgumentException();
		Objects.requireNonNull(corridor);
		var corridorLast = lastCorridorInShortcut(shortcut(numberFloor , corridor));
		if(corridorLast.isEmpty())
			return;
		var list = floors.getOrDefault(numberFloor, new ArrayList<>()).stream().filter(r -> r.index() < corridorLast.get().index()).toList();
		floors.put(numberFloor, new ArrayList<>(list));
		
	}
	
	public Optional<Room> findExit(int numberFloor) {
		if(numberFloor < 0)
			throw new IllegalArgumentException();
		floors.remove(numberFloor);
		
		return (numberFloor > maxFloor) ?Optional.empty() : visitRoom(numberFloor +1);

	}
	
}
