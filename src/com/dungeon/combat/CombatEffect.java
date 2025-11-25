package com.dungeon.combat;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.dungeon.enemy.Attack;
import com.dungeon.enemy.Enemy;
import com.dungeon.hero.Inventory;
import com.dungeon.hero.LifeRecord;
import com.dungeon.hero.Position;
import com.dungeon.hero.Stat;
import com.dungeon.hero.StatHero;
import com.dungeon.item.Item;

public record CombatEffect(String name ,int care , int defense , int attack , Weak weak) {
	public CombatEffect {
		Objects.requireNonNull(weak);
		if(care < 0 || defense < 0 || attack < 0)
			throw new IllegalArgumentException();
	}
	
	public LifeRecord modifyHealth(Stat stat , LifeRecord lifeRecord , int amount) {
		Objects.requireNonNull(stat);
		Objects.requireNonNull(lifeRecord);
		if(amount < 0)
			throw new IllegalArgumentException();
		return lifeRecord.modifyHealth(stat, amount , true);
	}
	
	public List<Enemy> modifyHealthEnemy(Enemy enemy1 , Attack attack, Enemy enemy2) {
		Objects.requireNonNull(enemy1);
		Objects.requireNonNull(enemy2);
		Objects.requireNonNull(attack);
		var enemy = enemy2.modifyHealthEnemy(Stat.HEALTH, attack.damage() , true);
		var enemyM = enemy1.modifyHealthEnemy(Stat.ALLOW, 0 , false);
		return List.of(enemy , enemyM);

	}
	
	public Enemy sleepCase(Enemy enemy) {
		Objects.requireNonNull(enemy);
		return enemy.modifyHealthEnemy(Stat.ALLOW, 0, false);
	}
	public StatHero curseInventory(Item item , Item curse  , Position position, Inventory inventory) {
		Objects.requireNonNull(item);
		Objects.requireNonNull(curse);
		Objects.requireNonNull(inventory);
		Objects.requireNonNull(position);
		inventory.removeItem(item);
		inventory.addInventory(item, position);
		return inventory;
	}
	
	public LifeRecord zombiEffect(Optional<String> name , LifeRecord lifeRecord , int amount) {
		Objects.requireNonNull(name);
		Objects.requireNonNull(lifeRecord);
		return lifeRecord.modifyHealth(Stat.HEALTH, (name.isEmpty())? -amount : amount, true);

	}
}
