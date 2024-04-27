package com.gildedrose.updaters;

import com.gildedrose.Item;

public class DefaultUpdater implements ItemUpdater {

	@Override
	public void update(Item arg) {
		ItemWrapper item = new ItemWrapper(arg);
		item.decreaseQuality(item.hasSellingDayPassed() ? 2 : 1);
		item.decreaseSellIn();
	}

}
