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

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.Nonnull;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(Parameterized.class)
@RequiredArgsConstructor
@Slf4j
public class CalcBottlesTest {
    private final int input;
    private final int output;

    private static int fn(int num) {
        if (num >= 2) {
            return 4 * num - 5;
        } else if (num == 1) {
            return 1;
        } else {
            return 0;
        }
    }

    @Nonnull
    @Parameterized.Parameters(name = "{0} -> {1}")
    public static Collection<Integer[]> getRunParameters() {
        List<Integer[]> list = new LinkedList<>();
        for (int i = 0; i <= 10; i++) {
            list.add(new Integer[]{i, fn(i / 2)});
        }
        return list;
    }

    @Test
    public void test() {
        log.info("{} yuan result in {} bottles totally.", input, output);
        assertThat(CalcBottles.calcBottles(input), is(output));
    }
}
