package com.gildedrose.updaters;

public class DefaultUpdater implements ItemUpdater {

	@Override
	public void update(ItemWrapper item) {
		item.decreaseQuality(item.hasSellingDayPassed() ? 2 : 1);
		item.decreaseSellIn();
	}

}
