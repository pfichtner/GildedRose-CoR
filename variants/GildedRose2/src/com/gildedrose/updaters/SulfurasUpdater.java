package com.gildedrose.updaters;

public class SulfurasUpdater implements ItemUpdater {

	private static final String SULFURAS = "Sulfuras, Hand of Ragnaros";

	private final ItemUpdater next;

	public SulfurasUpdater(ItemUpdater next) {
		this.next = next;
	}

	@Override
	public void update(ItemWrapper item) {
		if (SULFURAS.equals(item.getName())) {
			// noop
		} else {
			next.update(item);
		}
	}

}
