/*
 * Copyright 2020 lasyard@github.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.lasyard.code.quiz;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class BigDecimalCompareToTest {
    @Test
    public void testBigDecimalCompareTo() {
        BigDecimal a = BigDecimal.valueOf(Double.parseDouble("2"));
        BigDecimal b = BigDecimal.valueOf(Integer.parseInt("2"));
        assertEquals(0, a.compareTo(b));
        assertNotEquals(a, b);
    }

    @Test
    public void testBigDecimalCompareTo1() {
        BigDecimal a = BigDecimal.valueOf(0.1);
        BigDecimal b = new BigDecimal(0.1);
        assertNotEquals(0, a.compareTo(b));
        assertNotEquals(a, b);
    }

    @Test
    public void testBigDecimalCompareTo2() {
        BigDecimal a = BigDecimal.valueOf(0.1);
        BigDecimal b = BigDecimal.valueOf(Float.parseFloat("0.1"));
        assertNotEquals(0, a.compareTo(b));
        assertNotEquals(a, b);
    }

    @Test
    public void testBigDecimalCompareTo3() {
        String s1 = "0.99999999999999995"; // 16 of '9's
        String s2 = "1.00000000000000004"; // 16 of '0's
        BigDecimal a1 = BigDecimal.valueOf(Double.parseDouble(s1));
        BigDecimal b1 = new BigDecimal(s1);
        BigDecimal a2 = BigDecimal.valueOf(Double.parseDouble(s2));
        BigDecimal b2 = new BigDecimal(s2);
        assertEquals(a1, a2);
        assertNotEquals(b1, b2);
        assertTrue(b1.compareTo(b2) < 0);
    }
}
