package com.gildedrose.updaters;

import static java.lang.Math.max;
import static java.lang.Math.min;

import com.gildedrose.Item;

public interface ItemUpdater {

	class ItemWrapper {

		private static final int MAX_QUALITY = 50;
		private static final int MIN_QUALITY = 0;

		private final Item item;

		public ItemWrapper(Item item) {
			this.item = item;
		}

		public void increaseQuality() {
			increaseQuality(1);
		}

		public void decreaseQuality() {
			decreaseQuality(1);
		}

		public void increaseQuality(int amount) {
			item.quality = min(MAX_QUALITY, item.quality + amount);
		}

		public void decreaseQuality(int amount) {
			item.quality = max(MIN_QUALITY, item.quality - amount);
		}

		public void setQualityToMin() {
			item.quality = MIN_QUALITY;
		}

		public void decreaseSellIn() {
			item.sellIn--;
		}

		public String getName() {
			return item.name;
		}

		public int getSellIn() {
			return item.sellIn;
		}

		public boolean hasSellingDayPassed() {
			return getSellIn() <= 0;
		}

	}

	void update(ItemWrapper item);

}