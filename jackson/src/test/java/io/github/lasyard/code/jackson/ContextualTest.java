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

import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

public class ContextualTest {
    private static JsonMapper mapper;

    @BeforeClass
    public static void setupClass() {
        mapper = new JsonMapper();
        SimpleModule module = new SimpleModule();
        module.setSerializerModifier(new PeaBeanSerializerModifier());
        module.setDeserializerModifier(new PeaBeanDeserializerModifier());
        mapper.registerModule(module);
    }

    @Test
    public void testSerializer() throws IOException {
        Pea pea = new Pea();
        pea.setId(1);
        pea.setName("main");
        Pea pea1 = new Pea();
        pea1.setId(2);
        pea1.setName("child");
        pea.setPea(pea1);
        Pea pea2 = new Pea();
        pea2.setId(3);
        pea2.setName("grand-child");
        pea1.setPea(pea2);
        String json = mapper.writeValueAsString(pea);
        assertThat(json, is("{\"id\":1,\"name\":\"main\",\"pea\":2}"));
        json = mapper.writeValueAsString(pea1);
        assertThat(json, is("{\"id\":2,\"name\":\"child\",\"pea\":3}"));
    }

    @Test
    public void testDeserializer() throws IOException {
        String json = "{\"id\": 1, \"name\": \"main\", \"pea\": 3}";
        Pea pea = mapper.readValue(json, Pea.class);
        assertThat(pea.getId(), is(1));
        assertThat(pea.getName(), is("main"));
        assertThat(pea.getPea().getId(), is(3));
        assertThat(pea.getPea().getName(), is(nullValue()));
        assertThat(pea.getPea().getPea(), is(nullValue()));
    }
}
