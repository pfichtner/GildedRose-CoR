package com.gildedrose.updaters;

import com.gildedrose.ItemWrapper;

public class BackstagePassUpdater {

	private static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";

	public static boolean updateBackstagePass(ItemWrapper item) {
		if (!BACKSTAGE_PASSES.equals(item.getName())) {
			return false;
		}
		if (item.hasSellingDayPassed()) {
			item.setQualityToMin();
		} else {
			item.increaseQuality(getIncreaseBy(item));
		}
		item.decreaseSellIn();
		return true;
	}

	private static int getIncreaseBy(ItemWrapper item) {
		if (item.getSellIn() <= 5) {
			return 3;
		} else if (item.getSellIn() <= 10) {
			return 2;
		}
		return 1;
	}

}
