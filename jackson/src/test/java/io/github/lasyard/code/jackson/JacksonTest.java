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

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.module.afterburner.AfterburnerModule;
import lombok.extern.slf4j.Slf4j;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@Slf4j
public class JacksonTest {
    private static ObjectMapper mapper;
    private static Dog dog;
    private static Cat cat;

    @BeforeClass
    public static void setupClass() {
        mapper = JsonMapper.builder()
            .addModule(new AfterburnerModule())
            .build();
        // don't need this if use @JsonSubTypes at base class.
        // mapper.registerSubtypes(Dog.class, Cat.class);
        dog = new Dog();
        dog.setName("Goofy");
        cat = new Cat();
        cat.setName("Tom");
    }

    private InputStream getResourceFile(String path) {
        return getClass().getResourceAsStream(path);
    }

    @Test
    public void testDog() throws Exception {
        Dog dog = mapper.readValue(getResourceFile("/dog.json"), Dog.class);
        log.info(dog.toString());
        assertThat(dog, is(JacksonTest.dog));
    }

    @Test
    public void testZoo() throws Exception {
        Zoo zoo = mapper.readValue(getResourceFile("/zoo.json"), Zoo.class);
        log.info(zoo.toString());
        assertThat(zoo.getAnimals(), hasItems(dog, cat));
    }

    @Test
    public void testAnimals() throws Exception {
        List<Animal> animalList = mapper.readValue(
            getResourceFile("/animals.json"),
            new TypeReference<List<Animal>>() {
            }
        );
        log.info(animalList.toString());
        assertThat(animalList, hasItems(dog, cat));
    }
}
