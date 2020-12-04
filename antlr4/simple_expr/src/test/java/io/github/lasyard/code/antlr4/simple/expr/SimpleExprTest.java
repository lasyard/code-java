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

package io.github.lasyard.code.antlr4.simple.expr;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SimpleExprTest {
    @Test
    public void test1() {
        assertThat(SimpleExpr.eval("3 + 5;"), is(8));
    }

    @Test
    public void test2() {
        assertThat(SimpleExpr.eval("a = 3; b = 4; 3 + a*b\n"), is(15));
    }

    @Test
    public void test3() {
        assertThat(SimpleExpr.eval("a = 2; b = 3; (a + 2)*b;"), is(12));
    }
}
