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

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

public class NestedEntityTest {
    @Test
    public void test() {
        List<EntityB> bs = new LinkedList<>();
        bs.add(new EntityB("Ba"));
        bs.add(new EntityB("Bb"));
        bs.add(new EntityB("Ba"));
        EntityA a = new EntityA(bs);
        Set<String> props = a.getPropertySet(EntityA.class, "name");
        assertThat(props, allOf(hasItems("Aa"), not(hasItems("Ba")), not(hasItems("Bb"))));
        props = a.getPropertySet(EntityB.class, "name");
        assertThat(props, allOf(not(hasItems("Aa")), hasItems("Ba", "Bb")));
    }
}
