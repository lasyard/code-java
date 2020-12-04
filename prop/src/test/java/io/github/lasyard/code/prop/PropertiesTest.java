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

import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.util.Properties;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class PropertiesTest {
    private static Properties properties;

    @BeforeClass
    public static void setupClass() throws IOException {
        properties = new Properties();
        // Use absolute path here to search the root of classpath.
        properties.load(PropertiesTest.class.getResourceAsStream("/test.properties"));
    }

    @Test
    public void testPropertiesString() {
        assertEquals("abc", properties.getProperty("str1"));
    }

    @Test
    public void testPropertiesStringSubstitute() {
        assertEquals("${str1} is abc", properties.getProperty("str2"));
    }

    @Test
    public void testPropertiesInt() {
        assertEquals(3, Integer.parseInt(properties.getProperty("int")));
    }

    @Test
    public void testPropertiesInclude() {
        assertEquals("sub.properties", properties.getProperty("include"));
        assertNull(properties.getProperty("sub.str"));
    }
}
