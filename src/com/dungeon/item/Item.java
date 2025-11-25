package com.dungeon.item;

import java.util.List;

import com.dungeon.hero.Position;

public sealed interface Item permits Magic , Gold , Potion, Weapon , Curse {
	int size();
	List<Position> getItemsPosition(Position position);
	Item blockItem();
}
