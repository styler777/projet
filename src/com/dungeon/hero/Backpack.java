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
		this.pack = new ArrayList<>();
	} 
	
    //crée la grille avec une liste de liste
	private void initPack(int rows, int cols) {
	    pack = new ArrayList<>();
	    for (int i = 0; i < rows; i++) {
	        List<Item> row = new ArrayList<>();
	        for (int j = 0; j < cols; j++) {
	            row.add(null); 
	        }
	        pack.add(row);
	    }
	}
	
	
	// return true si une position(x,y) est dans le sac
	private boolean validPosition(int x, int y) {
    return x >= 0 && x < pack.size()
        && y >= 0 && y < pack.get(0).size();
	}
	
	
	//est ce que l'on peut mettre l'item
	private boolean canPlaceItem(List<Position> itemPositions) {
	    for (Position p : itemPositions) {
	        // La case est dans les limites du sac ?
	        if (!validPosition(p.x(), p.y()))
	            return false;
	
	        // La case est libre(null) ou déjà un Item ?
	        if (pack.get(p.x()).get(p.y()) != null)
	            return false;
	    }
	    return true;
	}
	public boolean addItem(Item item, Position start) {
		Objects.requireNonNull(item);
	    Objects.requireNonNull(start);
	    
	    List<Position> positions =item.getItemsPosition(start);
	    
	    if(!canPlaceItem (itemPositions)) {
	    	return false;
	    }
	    
	     for (Position p : positions) {
            Item present = pack.get(p.x()).get(p.y());
            if (present != null) {
                removeItem(present);
            }
        }
		for (Position p : positions) {
            pack.get(p.x()).set(p.y(), item);
		}
	    
	 // enregistrer les positions occupées
	    items.put(item, positions);
	    return true;
	    
	}
	public boolean removeItem(Item item) {
    Objects.requireNonNull(item);
    List<Position> pos = items.get(item);
    if (pos == null)
        return false;

    for (Position p : pos) {
        pack.get(p.x()).set(p.y(), null);
    }
    items.remove(item);
    return true;
	}
	public void modifyItem(Item newItem) {
	    Objects.requireNonNull(newItem);
	
	    List<Position> pos = items.get(newItem);
	    if (pos == null)
	        return;
	
	    for (Position p : pos) {
	        pack.get(p.x()).set(p.y(), newItem);
	    }
	
	    items.put(newItem, pos);
	}
	
}

	
