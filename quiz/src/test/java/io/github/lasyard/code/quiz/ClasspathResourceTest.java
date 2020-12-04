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

import org.junit.Test;

import java.net.URL;

import static org.hamcrest.CoreMatchers.endsWith;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.junit.Assert.assertThat;

public class ClasspathResourceTest {
    @Test
    public void testResourcePath() {
        URL resource = getClass().getResource("/name.txt");
        assertThat(resource.toString(), startsWith("file:"));
        assertThat(resource.toString(), endsWith("/name.txt"));
    }

    @Test
    public void testResourcePath1() {
        URL resource = getClass().getResource("name1.txt");
        assertThat(resource.toString(), startsWith("file:"));
        assertThat(resource.toString(), endsWith("io/github/lasyard/code/quiz/name1.txt"));
    }
}
