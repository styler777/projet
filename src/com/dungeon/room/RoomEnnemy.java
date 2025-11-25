package com.dungeon.room;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.dungeon.combat.Weak;
import com.dungeon.enemy.Enemy;
import com.dungeon.hero.Position;

public record RoomEnnemy(List<Enemy> enemies , Position position , int index) implements Room{
	public RoomEnnemy {
		Objects.requireNonNull(enemies);
		Objects.requireNonNull(position);
	}
	public void enemyAction(int index) {
		if(index < 0)
			throw new IllegalArgumentException();

	}
	public void enemyAction(int index , Optional<Weak> weak) {
		Objects.requireNonNull(weak);
		if(index < 0)
			throw new IllegalArgumentException();

	}
	
	public void removeEnemy(int index) {
		if(index < 0)
			throw new IllegalArgumentException();
		enemies.remove(index);


	}
}
