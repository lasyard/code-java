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

package io.github.lasyard.code.utils;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class BeanUtilsTest {
    @Test
    public void testA() {
        BeanA a = new BeanA();
        assertThat(BeanUtils.getPropertyValue(a, "name"), is("class A"));
    }

    @Test
    public void testB() {
        BeanB b = new BeanB();
        assertThat(BeanUtils.getPropertyValue(b, "name"), is("class B"));
        assertThat(BeanUtils.getPropertyValue(b, "long"), is(1L));
    }
}
