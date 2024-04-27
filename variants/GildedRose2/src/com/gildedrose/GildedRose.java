package com.gildedrose;

import com.gildedrose.updaters.AgedBrieUpdater;
import com.gildedrose.updaters.BackstagePassUpdater;
import com.gildedrose.updaters.DefaultUpdater;
import com.gildedrose.updaters.ItemUpdater;
import com.gildedrose.updaters.SulfurasUpdater;
import com.gildedrose.updaters.ItemUpdater.ItemWrapper;

class GildedRose {

	private final ItemUpdater updaterChain = new BackstagePassUpdater(
			new AgedBrieUpdater(new SulfurasUpdater(new DefaultUpdater())));

	private final Item[] items;

	public GildedRose(Item[] items) {
		this.items = items;
	}

	public void updateQuality() {
		for (Item item : items) {
			updaterChain.update(new ItemWrapper(item));
		}
	}

}
