package com.gildedrose.updaters;

import com.gildedrose.ItemWrapper;

public class AgedBrieUpdater {

	private static final String AGED_BRIE = "Aged Brie";

	public static boolean updateAgedBrie(ItemWrapper item) {
		if (!AGED_BRIE.equals(item.getName())) {
			return false;
		}
		item.increaseQuality(item.hasSellingDayPassed() ? 2 : 1);
		item.decreaseSellIn();
		return true;
	}

}
