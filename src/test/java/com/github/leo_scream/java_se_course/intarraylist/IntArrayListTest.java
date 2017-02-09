package com.github.leo_scream.java_se_course.intarraylist;

import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.Assert.*;

/**
 * @author Denis Verkhoturov, mod.satyr@gmail.com
 */
public class IntArrayListTest
{
	@Test
	public void add() throws Exception
	{
		final IntArrayList list = new IntArrayList();
		list.add(42);
		assertEquals(list.get(0), 42);

		list.add(Integer.MIN_VALUE);
		assertEquals(Integer.MIN_VALUE, list.get(1));

		list.add(Integer.MAX_VALUE);
		assertEquals(Integer.MAX_VALUE, list.get(2));
	}

	@Test
	public void get() throws Exception
	{
		final IntArrayList list = new IntArrayList();
		list.add(42);
		assertEquals("Got unexpected value.", 42, list.get(0));
		list.add(Integer.MIN_VALUE);
		assertEquals("Got unexpected value.", Integer.MIN_VALUE, list.get(1));
		list.add(Integer.MAX_VALUE);
		assertEquals("Got unexpected value.", Integer.MAX_VALUE, list.get(2));
	}

	@Test
	public void getSize() throws Exception
	{
		final IntArrayList list = new IntArrayList();
		assertEquals("Just instantiated list must have a size zero.", 0,
				list.getSize());
		list.add(42);
		assertEquals("Size didn't change after adding element.", 1,
				list.getSize());
	}

	@Test
	public void maxValueInefficient() throws Exception
	{
		final int[] values = new Random()
				.ints(256, Integer.MIN_VALUE, Integer.MAX_VALUE)
				.sorted()
				.toArray();
		final IntArrayList list = new IntArrayList(values);
		final int exceptedMaxValue = Arrays.stream(values)
				.boxed()
				.mapToInt(i -> i)
				.max()
				.getAsInt();

		assertEquals(exceptedMaxValue, list.maxValueInefficient());
	}

	@Test
	public void sort() throws Exception
	{
		final int[] values = new Random()
				.ints(256, Integer.MIN_VALUE, Integer.MAX_VALUE)
				.toArray();
		final IntArrayList list = new IntArrayList(values);

		list.sort(ints -> {
			for (int i = 0; i < ints.length; i++) {
				for (int j = 1; j < ints.length; j++) {
					if (ints[j - 1] > ints[j]) {
						int tmp = ints[j - 1];
						ints[j - 1] = ints[j];
						ints[j] = tmp;
					}
				}
			}

			return ints;
		});
	}

	@Test
	public void binarySearch() throws Exception
	{
		final int[] values = {
				Integer.MAX_VALUE, -1889, -1, 0, 1, 42, Integer.MAX_VALUE
		};
		final IntArrayList list = new IntArrayList(values);

		assertEquals(-3, list.binarySearch(-42));
		assertEquals(1, list.binarySearch(-1889));
		assertEquals(-7, list.binarySearch(Integer.MAX_VALUE - 1));
	}
}