package com.dungeon.item;

import java.util.List;
import java.util.Objects;
import java.util.Random;

import com.dungeon.combat.CombatEffect;
import com.dungeon.hero.Position;

public final class Curse implements Item, Equipment {

    private static final Random RANDOM = new Random();

    private final String name;
    private final int damage;
    private final CombatEffect effect;
    private final int price;
    private final boolean block;

    private final List<Position> shape;

    public Curse(String name, int damage, CombatEffect effect, int price, boolean block) {
        Objects.requireNonNull(name);
        Objects.requireNonNull(effect);

        if (damage < 0 || price < 0)
            throw new IllegalArgumentException();

        this.name = name;
        this.damage = damage;
        this.effect = effect;
        this.price = price;
        this.block = block;

        this.shape = generateRandomShape();
    }

    private List<Position> generateRandomShape() {
        int form = RANDOM.nextInt(3);

        if (form == 0) {
            return List.of(new Position(0, 0));
        }

        if (form == 1) {
            boolean horizontal = RANDOM.nextBoolean();
            if (horizontal) {
                return List.of(
                    new Position(0, 0),
                    new Position(1, 0)
                );
            } else {
                return List.of(
                    new Position(0, 0),
                    new Position(0, 1)
                );
            }
        }

        int shape3 = RANDOM.nextInt(3);

        if (shape3 == 0) {
            return List.of(
                new Position(0, 0),
                new Position(1, 0),
                new Position(2, 0)
            );
        }

        if (shape3 == 1) {
            return List.of(
                new Position(0, 0),
                new Position(0, 1),
                new Position(0, 2)
            );
        }

        return List.of(
            new Position(0, 0),
            new Position(0, 1),
            new Position(1, 1)
        );
    }

    @Override
    public List<Position> getItemsPosition(Position start) {
        return shape.stream()
                .map(p -> new Position(start.x() + p.x(), start.y() + p.y()))
                .toList();
    }

    @Override
    public Item blockItem() {
        return new Curse(name, damage, effect, price, true);
    }
}



	
	
	
	
}
