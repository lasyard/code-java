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

package io.github.lasyard.code.annotation.runtime;

import org.junit.Test;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.junit.Assert.assertThat;

public class AnnotationTest {
    @Test
    public void test1() {
        Annotation[] annotations = TestObject1.class.getAnnotations();
        List<String> names = Arrays.stream(annotations)
            .map(Annotation::annotationType)
            .map(Class::getSimpleName)
            .collect(Collectors.toList());
        assertThat(names, hasItem("Annotation1"));
    }
}
