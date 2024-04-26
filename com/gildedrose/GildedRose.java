package com.gildedrose;

import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

import com.gildedrose.updaters.AgedBrieUpdater;
import com.gildedrose.updaters.BackstagePassUpdater;
import com.gildedrose.updaters.ItemWrapper;
import com.gildedrose.updaters.SulfurasUpdater;

class GildedRose {

	private final Item[] items;

	public GildedRose(Item[] items) {
		this.items = items;
	}

	public void updateQuality() {
		for (Item item : items) {
			ItemWrapper itemWrapper = new ItemWrapper(item);
			updaterChain().map(i -> i.apply(itemWrapper)).filter(couldHandle()).findFirst()
					.orElseGet(() -> updateDefault(itemWrapper));
		}
	}

	private Predicate<Boolean> couldHandle() {
		return Boolean.TRUE::equals;
	}

	private static boolean updateDefault(ItemWrapper item) {
		item.decreaseQuality(item.hasSellingDayPassed() ? 2 : 1);
		item.decreaseSellIn();
		return true;
	}

	private static Stream<Function<ItemWrapper, Boolean>> updaterChain() {
		return Stream.<Function<ItemWrapper, Boolean>> of( //
				BackstagePassUpdater::updateBackstagePass, //
				AgedBrieUpdater::updateAgedBrie, //
				SulfurasUpdater::updateSulfuras //
		);
	}

}
