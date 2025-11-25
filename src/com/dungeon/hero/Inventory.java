package com.dungeon.hero;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import com.dungeon.item.Item;

public final class Inventory implements StatHero{
	private int placeRemaining;
	private final Position position;
	private final HashMap<Item , List<Position>> items;
	public Inventory(int placeRemaining , Position position) {
		Objects.requireNonNull(position);
		if(placeRemaining < 0)
			throw new IllegalArgumentException();
		this.placeRemaining = placeRemaining;
		this.position = position;
		this.items = new HashMap<>();
	}
	public Inventory(int placeRemaining , Position position , Map<Item, List<Position>> items){
		Objects.requireNonNull(items);
		Objects.requireNonNull(position);
		if(placeRemaining < 0)
			throw new IllegalArgumentException();
		this.placeRemaining = placeRemaining;
		this.position = position;
		this.items = new HashMap<>(items);
	}

	public void removeItem(Item item) {
		Objects.requireNonNull(position);
		
		this.placeRemaining += item.size();
		items.remove(item);

	}
	
	public void modifyItem(Item item) {
		Objects.requireNonNull(item);
		var positions = items.getOrDefault(item, new ArrayList<>());
		items.put(item, positions);
	}
	
	private boolean itemsCurrentPosition(List<Position> positions , Position current) {
		Objects.requireNonNull(positions);
		Objects.requireNonNull(current);
		return positions.stream().anyMatch(p -> current.x() <= p.x() && current.y() >= p.y());
	}

	
	
	public void addInventory(Item item , Position position) {
		Objects.requireNonNull(item);
		Objects.requireNonNull(position);
		if(!(items.values().stream().flatMap(pts -> pts.stream()).anyMatch(p -> itemsCurrentPosition(item.getItemsPosition(position), p))) && placeRemaining - item.size() >= 0 ) {
			items.put(item, item.getItemsPosition(position));
			placeRemaining -= item.size();
			

		}
			
			
	}
	
	public Optional<Item> usingItem(Position position) {
		Objects.requireNonNull(position);
		
		return items.entrySet()
								.stream()
								.filter(e -> itemsCurrentPosition(e.getValue(), position))
								.map(e-> e.getKey()).findFirst();
	}
	
	public Inventory enlargissementInventory(Position resize , Map<Item, List<Position>> items) {
		Objects.requireNonNull(resize);
		Objects.requireNonNull(items);
		this.placeRemaining += (resize.x() != 0)? resize.x() : resize.y();
		return new Inventory(placeRemaining,new Position(position.x() + resize.x(), position.y() + resize.y())  , items);
		
	}
}
