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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import java.util.Objects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@Configuration
@PropertySource("classpath:test.properties")
public class SpringEnvironmentTest {
    private static SpringEnvironmentTest bean;

    @Autowired
    private Environment env;

    @BeforeClass
    public static void setupClass() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(SpringEnvironmentTest.class);
        ctx.refresh();
        bean = ctx.getBean(SpringEnvironmentTest.class);
    }

    @Test
    public void testSpringString() {
        assertEquals("abc", bean.env.getProperty("str1"));
    }

    @Test
    public void testSpringStringSubstitute() {
        assertEquals("abc is abc", bean.env.getProperty("str2"));
    }

    @Test
    public void testSpringInt() {
        assertEquals(3, Integer.parseInt(Objects.requireNonNull(bean.env.getProperty("int"))));
    }

    @Test
    public void testSpringInclude() {
        assertEquals("sub.properties", bean.env.getProperty("include"));
        assertNull(bean.env.getProperty("sub.str"));
    }
}
