package com.gildedrose.updaters;

import com.gildedrose.ItemWrapper;

public class SulfurasUpdater {

	private static final String SULFURAS = "Sulfuras, Hand of Ragnaros";

	public static boolean updateSulfuras(ItemWrapper item) {
		// noop on sulfuras
		return SULFURAS.equals(item.getName());
	}

}
