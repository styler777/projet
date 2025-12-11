package com.dungeon.item;

import java.util.List;
import java.util.Objects;


import com.dungeon.combat.CombatEffect;
import com.dungeon.hero.Position;

public final class Curse implements Item, Equipment {

    private final String name;
    private final int damage;
    private final CombatEffect effect;
    private final int price;
    private final boolean block;

    private final List<Position> shape;

   public Curse(String name, int damage, CombatEffect effect, int price, boolean block, List<Position> shape) {
        Objects.requireNonNull(name);
        Objects.requireNonNull(effect);
        Objects.requireNonNull(shape);

        if (damage < 0 || price < 0)
            throw new IllegalArgumentException();

        this.name = name;
        this.damage = damage;
        this.effect = effect;
        this.price = price;
        this.block = block;
        this.shape = List.copyOf(shape);;
    }

	@Override
    public List<Position> getItemsPosition(Position start) {
        return shape.stream()
                .map(p -> new Position(start.x() + p.x(), start.y() + p.y()))
                .toList();
    }

    @Override
    public Item blockItem() {
        return new Curse(name, damage, effect, price, true, shape);
    }

    public List<Position> shape() {
        return shape;
    }
}

	

   
