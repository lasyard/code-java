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

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import javax.annotation.Nonnull;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(Parameterized.class)
@RequiredArgsConstructor
@Slf4j
public class CalcBitsTest {
    private final int input;
    private final int output;

    @Nonnull
    @Parameterized.Parameters(name = "{0} -> {1}")
    public static Collection<Integer[]> getRunParameters() {
        return Arrays.asList(new Integer[][]{
            {0, 0},
            {1, 1},
            {2, 1},
            {3, 2},
            {4, 1},
            {5, 2},
            {6, 2},
            {7, 3},
            {8, 1},
            {9, 2},
        });
    }

    @Test
    public void testCalcBits() {
        log.info("Number {} has {} bits set to 1.", input, output);
        assertThat(CalcBits.calcBits(input), is(output));
    }

    @Test
    public void testCalcBitsRecursive() {
        log.info("Number {} has {} bits set to 1.", input, output);
        assertThat(CalcBits.calcBitsRecursive(input), is(output));
    }
}
