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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import static org.junit.Assert.assertEquals;

@Configuration
@PropertySource("classpath:test.properties")
public class SpringTest {
    private static SpringTest bean;

    @Value("${str1}")
    private String str1;
    @Value("${str2}")
    private String str2;
    @Value("${int}")
    private int anInt;
    @Value("${include}")
    private String include;
    @Value("${sub.str}")
    private String subStr;

    @BeforeClass
    public static void setupClass() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(SpringTest.class);
        ctx.refresh();
        bean = ctx.getBean(SpringTest.class);
    }

    @Test
    public void testSpringString() {
        assertEquals("abc", bean.str1);
    }

    @Test
    public void testSpringStringSubstitue() {
        assertEquals("abc is abc", bean.str2);
    }

    @Test
    public void testSpringInt() {
        assertEquals(3, bean.anInt);
    }

    @Test
    public void testSpringInclude() {
        assertEquals("sub.properties", bean.include);
        assertEquals("${sub.str}", bean.subStr);
    }
}
