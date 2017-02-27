package com.github.leo_scream.java_se_course.unit_01.intarraylist;

import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * @author Denis Verkhoturov, @author mod.satyr@gmail.com
 */
public class MergeSortBottomUpTest
{
    @Test
    public void sort() throws Exception
    {
        final Sorter sorter = new MergeSortBottomUp();
        final int[] values = new Random()
            .ints(256, Integer.MIN_VALUE, Integer.MAX_VALUE)
            .toArray();

        final int[] sorted = sorter.sort(values);

        int lastValue = 0;
        int valueCount = 0;
        for (int value : sorted) {
            if (value != lastValue) {
                boolean isPresent = false;
                for (int origin : values) {
                    if (value == origin) {
                        isPresent = true;
                        valueCount += 1;
                    }
                }
                if (isPresent) {
                    valueCount -= 1;
                } else {
                    fail("Arrays have different values.");
                }
            } else {
                if (valueCount > 0) {
                    valueCount -= 1;
                } else {
                    fail("Arrays have different values.");
                }
            }
            lastValue = value;
        }

        for (int i = 0; i < sorted.length; i++) {
            for (int j = i; j < sorted.length; j++) {
                assertTrue(sorted[i] <= sorted[j]);
            }
        }
    }

}
