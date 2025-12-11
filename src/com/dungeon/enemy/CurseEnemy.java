package com.dungeon.enemy;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

import com.dungeon.hero.BodyCurse;
import com.dungeon.hero.Backpack;
import com.dungeon.hero.Stat;
import com.dungeon.item.Item;


public record CurseEnemy(String name  , Stat stat, int damage, int intensity , int duration , boolean isBody ,boolean startRound, Predicate<GameStat> trigger) implements AttackEnemy{
	public CurseEnemy {
		Objects.requireNonNull(name);
		Objects.requireNonNull(trigger);
		Objects.requireNonNull(stat);
		if(intensity < 0 || duration < 0 || damage < 0)
			throw new IllegalArgumentException();
	}
	
	public BodyCurse createBodyCurse() {
		return new BodyCurse(damage, stat ,duration, 0, startRound, true);

	}
	
	public BodyCurse increaseIntensity(BodyCurse bodyCurse) {
		Objects.requireNonNull(bodyCurse);
		return bodyCurse.modifyIntensityeBodyCurse();

	}
	
	public Optional<BodyCurse> onAttackWithTrigger(GameStat gameStat) {
		Objects.requireNonNull(gameStat);
		if(trigger.test(gameStat))
			return Optional.ofNullable(createBodyCurse());
		return Optional.ofNullable(null);

	}
	
	public void blockItem(List<Item> items , Backpack backpack) {
		Objects.requireNonNull(backpack);
		Objects.requireNonNull(items);
		items.stream().map(i -> i.blockItem()).toList().forEach(i -> backpack.modifyItem(i));;
		
	}
	
	
	
}
