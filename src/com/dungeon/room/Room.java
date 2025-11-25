package com.dungeon.room;

import com.dungeon.hero.Position;

public sealed interface Room permits Corridor , Merchand , Treasure, RoomEnnemy , Healer{
	Position position();
	int index();
}
