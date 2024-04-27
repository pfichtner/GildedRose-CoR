package com.gildedrose.updaters;

public class AgedBrieUpdater implements ItemUpdater {

	private static final String AGED_BRIE = "Aged Brie";

	private final ItemUpdater next;

	public AgedBrieUpdater(ItemUpdater next) {
		this.next = next;
	}

	@Override
	public void update(ItemWrapper item) {
		if (AGED_BRIE.equals(item.getName())) {
			item.increaseQuality(item.hasSellingDayPassed() ? 2 : 1);
			item.decreaseSellIn();
		} else {
			next.update(item);
		}
	}

}
