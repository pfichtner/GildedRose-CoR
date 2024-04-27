package com.gildedrose;

import static java.util.Collections.unmodifiableMap;

import java.util.HashMap;
import java.util.Map;

import com.gildedrose.updaters.AgedBrieUpdater;
import com.gildedrose.updaters.BackstagePassUpdater;
import com.gildedrose.updaters.DefaultUpdater;
import com.gildedrose.updaters.ItemUpdater;

class GildedRose {

	private final Map<String, ItemUpdater> updaters = unmodifiableMap(updaters());
	private final ItemUpdater defaultUpdater = new DefaultUpdater();

	private static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
	private static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
	private static final String AGED_BRIE = "Aged Brie";

	private final Item[] items;

	public GildedRose(Item[] items) {
		this.items = items;
	}

	public void updateQuality() {
		for (Item item : items)
			getItemUpdater(item).update(item);
	}

	private ItemUpdater getItemUpdater(Item item) {
		ItemUpdater updater = updaters.get(item.name);
		return updater == null ? defaultUpdater : updater;
	}

	private static Map<String, ItemUpdater> updaters() {
		Map<String, ItemUpdater> updaters = new HashMap<String, ItemUpdater>();
		updaters.put(SULFURAS, ItemUpdater.NOOP);
		updaters.put(AGED_BRIE, new AgedBrieUpdater());
		updaters.put(BACKSTAGE_PASSES, new BackstagePassUpdater());
		return updaters;
	}

}
