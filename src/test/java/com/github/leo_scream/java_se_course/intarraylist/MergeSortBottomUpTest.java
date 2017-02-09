package com.github.leo_scream.java_se_course.intarraylist;

import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

/**
 * @author Denis Verkhoturov, @author mod.satyr@gmail.com
 */
public class MergeSortBottomUpTest
{
	@Test
	public void sort() throws Exception
	{
		final Sorter sorter = new MergeSortBottomUp();
		int[] values = new Random()
				.ints(256, Integer.MIN_VALUE, Integer.MAX_VALUE)
				.toArray();

		values = sorter.sort(values);

		for (int i = 0; i < values.length; i++) {
			for (int j = i; j < values.length; j++) {
				assertTrue(values[i] <= values[j]);
			}
		}
	}

}