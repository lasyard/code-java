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

package io.github.lasyard.code.serdes;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@Slf4j
public class KryoTest {
    private Kryo kryo;

    @Before
    public void setup() {
        kryo = new Kryo();
        kryo.register(Model.class);
    }

    @Test
    public void test() {
        Model model = new Model();
        model.setName("Alice");
        model.setScore(100);
        Output output = new Output(1024);
        kryo.writeClassAndObject(output, model);
        log.info("The size of output is {}.", output.total());
        Input input = new Input(output.getBuffer());
        Model model1 = (Model) kryo.readClassAndObject(input);
        assertThat(model1, is(model));
    }

    @Test
    public void test1() {
        Model model = new Model();
        model.setName("Alice");
        model.setScore(100);
        Output output = new Output(1024);
        kryo.writeObject(output, model);
        log.info("The size of output is {}.", output.total());
        Input input = new Input(output.getBuffer());
        Model model1 = kryo.readObject(input, Model.class);
        assertThat(model1, is(model));
    }
}
