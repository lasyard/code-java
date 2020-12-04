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

package io.github.lasyard.code.quiz.override;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@Slf4j
public class OverrideTest {
    @Test
    public void test() {
        Base base = new Derived();
        String str = base.say();
        log.info(str);
        assertThat(str, is(Derived.SAY));
        str = ((Derived) base).saySomethingElse();
        log.info(str);
        assertThat(str, is(Derived.SAY_ELSE));
    }

    @Test
    public void test2() {
        Base base = new Base();
        String str = base.say();
        log.info(str);
        assertThat(str, is(Base.SAY));
    }

    @Test(expected = ClassCastException.class)
    public void test3() {
        Base base = new Base();
        String str = ((Derived) base).saySomethingElse();
        log.info(str);
        assertThat(str, is(Derived.SAY_ELSE));
    }
}
