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

package io.github.lasyard.code.prop;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class Configuration2Test {
    private static Configuration configuration;

    @BeforeClass
    public static void setupClass() throws ConfigurationException {
        Configurations configurations = new Configurations();
        configuration = configurations.properties(new File("test.properties"));
    }

    @Test
    public void testConfiguration2String() {
        assertEquals("abc", configuration.getString("str1"));
    }

    @Test
    public void testConfiguration2StringSubstitute() {
        assertEquals("abc is abc", configuration.getString("str2"));
    }

    @Test
    public void testConfiguration2Int() {
        assertEquals(3, configuration.getInt("int"));
    }

    @Test
    public void testConfiguration2Include() {
        assertEquals("sub.str", configuration.getString("sub.str"));
        assertNull(configuration.getString("include"));
    }
}
