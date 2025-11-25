package com.dungeon.hero;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import com.dungeon.item.Item;

public final class Backpack implements StatHero{
	private  List<List<Item>> pack;
	private final Map<Item , List<Position>> items;
	
	public Backpack() {
		this.items = new HashMap<>();
		initPack(3, 5); 
	}
	
	}
	private void initPack(int rows, int cols) {
    pack = new ArrayList<>();

    for (int i = 0; i < rows; i++) {
        List<Item> row = new ArrayList<>();
        for (int j = 0; j < cols; j++) {
            row.add(null); // case vide
        }
        pack.add(row);
    }
}

//supression d'item
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
