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
    //fonction qui crée la grille avec une liste de liste
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
	























	
