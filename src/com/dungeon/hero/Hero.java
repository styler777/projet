package com.dungeon.hero;

import java.util.ArrayList;
import java.util.Objects;


public class Hero {
	private final ArrayList<StatHero> stathero;//s
	private final static int STARTING_EXPERIENCE = 10;
	public Hero() {
		this.stathero = new ArrayList<>();
	}
	
	public void remove(StatHero statHero) {
		Objects.requireNonNull(statHero);
		stathero.remove(statHero);

	}

	public void add(StatHero statHero) {
		Objects.requireNonNull(statHero);
		
		stathero.add(statHero);

	}
	
	


}
