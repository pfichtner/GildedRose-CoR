package com.gildedrose;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class GildedRoseTest {

	@Test
	public void testPassDay_withAgedBrie_shouldIncreaseQualityByOne() {
		Item item = new Item(getAgedBrie(), notSellinDatePassed(), 10);
		updateQuality(item);
		assertThat(item.quality, is(11));
	}

	@Test
	public void testPassDay_withBackstagePassesFiveDaysLeft_shouldIncreaseQualityByThree() {
		Item item = new Item("Backstage passes to a TAFKAL80ETC concert",
				daysLeft(5), 10);
		updateQuality(item);
		assertThat(item.quality, is(13));
	}

	@Test
	public void testPassDay_withBackstagePassesTenDaysLeft_shouldIncreaseQualityByTwo() {
		Item item = new Item("Backstage passes to a TAFKAL80ETC concert",
				daysLeft(10), anyQuality());
		updateQuality(item);
		assertThat(item.quality, is(anyQuality() + 2));
	}

	@Test
	public void testPassDay_withBackstagePassesZeroDaysLeft_shouldDropQualityToZero() {
		Item item = new Item("Backstage passes to a TAFKAL80ETC concert",
				daysLeft(0), moreThan(4));
		updateQuality(item);
		assertThat(item.quality, is(0));
	}

	@Test
	public void testPassDay_withBackstagePassesMoreThanTenDaysLeft_shouldIncreaseQualityByOne() {
		Item item = new Item("Backstage passes to a TAFKAL80ETC concert",
				daysLeft(moreThan(10)), anyQuality());
		updateQuality(item);
		assertThat(item.quality, is(anyQuality() + 1));
	}

	@Test
	public void testPassDay_withNormalItem_shouldDecreaseQualityByOne() {
		Item item = new Item(anyName(), notSellinDatePassed(), 10);
		updateQuality(item);
		assertThat(item.quality, is(9));
	}

	@Test
	public void testPassDay_withNormalItem_shouldDecreaseDaysLeftByOne() {
		Item item = new Item(anyName(), notSellinDatePassed(), 10);
		updateQuality(item);
		assertThat(item.sellIn, is(notSellinDatePassed() - 1));
	}

	@Test
	public void testPassDay_withSulfuras_shouldNotChangeQuality() {
		Item item = new Item("Sulfuras, Hand of Ragnaros",
				notSellinDatePassed(), anyQuality());
		updateQuality(item);
		assertThat(item.quality, is(anyQuality()));
	}

	@Test
	public void testPassDay_withNormalItemAndZeroQuality_shouldNotDecreaseQuality() {
		Item item = new Item(anyName(), anySellingDate(), zeroQuality());
		updateQuality(item);
		assertThat(item.quality, is(zeroQuality()));
	}

	@Test
	public void testPassDay_withNormalItemAndNegativeSellDate_shouldDecreaseQualityeByTwo() {
		Item item = new Item(anyName(), anyNegativeSellingDate(), anyQuality());
		updateQuality(item);
		assertThat(item.quality, is(anyQuality() - 2));
	}

	@Test
	public void testPassDay_withOldBrieAndQualityFifty_shouldNotIncreaseQuality() {
		Item item = new Item(getAgedBrie(), anySellingDate(), getMaxQuality());
		updateQuality(item);
		assertThat(item.quality, is(getMaxQuality()));
	}

	@Test
	public void testPassDay_withOldBrieAndQuality0_shouldIncreaseQualityTwice() {
		Item item = new Item(getAgedBrie(), anyNegativeSellingDate(), zeroQuality());
		updateQuality(item);
		assertThat(item.quality, is(2));
	}

	private int anyNegativeSellingDate() {
		return -1;
	}

	private Item updateQuality(Item item) {
		GildedRose app = createApp(item);
		app.updateQuality();
		return item;
	}

	private int moreThan(int num) {
		return num + 1;
	}

	private int anyQuality() {
		return 10;
	}

	private int getMaxQuality() {
		return 50;
	}

	private int zeroQuality() {
		return 0;
	}

	private int anySellingDate() {
		return 0;
	}

	private GildedRose createApp(Item item) {
		return new GildedRose(new Item[] { item });
	}

	private int daysLeft(int i) {
		return i;
	}

	private String getAgedBrie() {
		return "Aged Brie";
	}

	private int notSellinDatePassed() {
		return 3;
	}

	private String anyName() {
		return "foo";
	}
}
