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

package io.github.lasyard.code.i18n;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Locale;
import java.util.ResourceBundle;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class TestI18n {
    @BeforeClass
    public static void setupClass() {
        Locale.setDefault(Locale.ROOT);
    }

    @Test
    public void testDefault() {
        ResourceBundle bundle = ResourceBundle.getBundle("message");
        assertThat(bundle.getString("name"), is("xxx"));
    }

    @Test
    public void testZhCn() {
        ResourceBundle bundle = ResourceBundle.getBundle("message", Locale.CHINA);
        assertThat(bundle.getString("name"), is("叉叉叉"));
    }
}
