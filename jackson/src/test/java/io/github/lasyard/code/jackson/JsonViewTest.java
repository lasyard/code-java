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

package io.github.lasyard.code.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator;
import lombok.extern.slf4j.Slf4j;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@Slf4j
public class JsonViewTest {
    private static ObjectMapper yamlMapper;
    private static JsonMapper jsonMapper;

    @BeforeClass
    public static void setupClass() {
        YAMLFactory yamlFactory = new YAMLFactory()
            .enable(YAMLGenerator.Feature.MINIMIZE_QUOTES);
        yamlMapper = new ObjectMapper(yamlFactory);
        jsonMapper = new JsonMapper();
    }

    @Test
    public void testStudent() throws IOException {
        Student student = yamlMapper.readerFor(Student.class).readValue("{ id: 1, name: John, gender: true }");
        String json = jsonMapper.writerWithView(Animal.class).writeValueAsString(student);
        log.info(json);
        assertThat(json, is("{\"name\":\"John\",\"gender\":true}"));
        json = jsonMapper.writeValueAsString(student);
        log.info(json);
        assertThat(json, is("{\"id\":1,\"name\":\"John\",\"gender\":true}"));
    }
}
