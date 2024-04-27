package com.gildedrose.updaters;

public class BackstagePassUpdater implements ItemUpdater {

	private static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";

	private final ItemUpdater next;

	public BackstagePassUpdater(ItemUpdater next) {
		this.next = next;
	}

	@Override
	public void update(ItemWrapper item) {
		if (BACKSTAGE_PASSES.equals(item.getName())) {
			if (item.hasSellingDayPassed()) {
				item.setQualityToMin();
			} else {
				item.increaseQuality(getIncreaseBy(item));
			}
			item.decreaseSellIn();
		} else {
			next.update(item);
		}
	}

	private int getIncreaseBy(ItemWrapper item) {
		if (item.getSellIn() <= 5) {
			return 3;
		} else if (item.getSellIn() <= 10) {
			return 2;
		}
		return 1;
	}

}
