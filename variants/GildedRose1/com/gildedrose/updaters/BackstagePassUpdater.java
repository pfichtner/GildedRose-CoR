package com.gildedrose.updaters;

import com.gildedrose.Item;

public class BackstagePassUpdater implements ItemUpdater {

	@Override
	public void update(Item arg) {
		ItemWrapper item = new ItemWrapper(arg);
		if (item.hasSellingDayPassed()) {
			item.setQualityToMin();
		} else {
			item.increaseQuality(calcIncreaseBy(item));
		}
		item.decreaseSellIn();
	}

	private int calcIncreaseBy(ItemWrapper item) {
		if (item.getSellIn() <= 5) {
			return 3;
		} else if (item.getSellIn() <= 10) {
			return 2;
		}
		return 1;
	}

}
