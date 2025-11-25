package com.dungeon.item;

public sealed interface Equipment permits Curse , Magic , Weapon{
	int price();
}
