package com.gildedrose.updaters;

import com.gildedrose.Item;

public class AgedBrieUpdater implements ItemUpdater {

	@Override
	public void update(Item arg) {
		ItemWrapper item = new ItemWrapper(arg);
		item.increaseQuality(item.hasSellingDayPassed() ? 2 : 1);
		item.decreaseSellIn();
	}

}
