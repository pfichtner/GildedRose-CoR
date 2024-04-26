package com.gildedrose;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import com.gildedrose.updaters.AgedBrieUpdater;
import com.gildedrose.updaters.BackstagePassUpdater;
import com.gildedrose.updaters.SulfurasUpdater;

class GildedRose {

	private static final List<Function<ItemWrapper, Boolean>> updaters = List.of( //
			BackstagePassUpdater::updateBackstagePass, //
			AgedBrieUpdater::updateAgedBrie, //
			SulfurasUpdater::updateSulfuras //
	);

	private static final Predicate<Boolean> couldHandle = Boolean.TRUE::equals;

	private final Item[] items;

	public GildedRose(Item[] items) {
		this.items = items;
	}

	public void updateQuality() {
		for (Item item : items) {
			ItemWrapper itemWrapper = new ItemWrapper(item);
			updaters.stream() //
					.map(u -> u.apply(itemWrapper)) //
					.filter(couldHandle) //
					.findFirst() //
					.orElseGet(() -> updateDefault(itemWrapper));
		}
	}

	private static boolean updateDefault(ItemWrapper item) {
		item.decreaseQuality(item.hasSellingDayPassed() ? 2 : 1);
		item.decreaseSellIn();
		return true;
	}

}
