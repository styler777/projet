package com.dungeon.enemy;

import java.util.Objects;

import com.dungeon.hero.Stat;

public record Protection(int protect , String name) implements AttackEnemy{
	public Protection {
		Objects.requireNonNull(name);
		if(protect < 0)
			throw new IllegalArgumentException();
	}
	
	public Enemy protectEnemy(Enemy enemy) {
		Objects.requireNonNull(enemy);
		return enemy.modifyHealthEnemy(Stat.PROTECTION, protect, true);

	}
}
