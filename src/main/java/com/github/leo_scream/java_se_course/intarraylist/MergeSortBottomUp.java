package com.github.leo_scream.java_se_course.intarraylist;

/**
 * @author Denis Verkhoturov, mod.satyr@gmail.com
 */
public class MergeSortBottomUp implements Sorter
{
	@Override
	public int[] sort(int... values)
	{
		int mid;
		int endExclusive;
		int[] sorted = new int[values.length];

		for (int length = 1; length <= values.length; length *= 2) {
			for (int startInclusive = 0; startInclusive < values.length; startInclusive += 2 * length) {
				endExclusive = startInclusive + length;

				if (endExclusive >= values.length) {
					mid = startInclusive + (values.length - startInclusive) / 2;
					merge(values, startInclusive, mid, sorted.length, sorted);
				} else {
					mid = startInclusive + length / 2;
					merge(values, startInclusive, mid, endExclusive, sorted);
				}

				if (endExclusive < values.length - 1 && startInclusive + 2 * length >= values.length) {
					merge(values, 0, endExclusive, values.length, sorted);
				}
			}
		}

		return sorted;
	}

	private void merge(int[] array, int startInclusive, int mid,
	                    int endExclusive, int[] free)
	{
		int i = startInclusive;
		int j = mid;

		System.arraycopy(array, startInclusive, free, startInclusive, endExclusive - startInclusive);

		for (int k = startInclusive; k < endExclusive; k++) {
			if (i >= mid) array[k] = free[j++];
			else if (j >= endExclusive) array[k] = free[i++];
			else if (free[i] < free[j]) array[k] = free[i++];
			else array[k] = free[j++];
		}
	}
}
