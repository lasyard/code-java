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

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class AvroTest {
    @Test
    public void test() throws IOException {
        User user = new User();
        user.setName("Alice");
        user.setScore(100);
        ByteBuffer buffer = user.toByteBuffer();
        byte[] byteArray = buffer.array();
        User user1 = User.fromByteBuffer(ByteBuffer.wrap(byteArray));
        assertThat(user1.getName().toString(), is("Alice"));
        assertThat(user1.getScore(), is(100));
    }

    @Test
    public void test1() throws IOException {
        User user = new User("Alice", 100);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        user.writeExternal(oos);
        oos.close();
        byte[] bytes = bos.toByteArray();
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        ObjectInputStream ois = new ObjectInputStream(bis);
        User user1 = new User();
        user1.readExternal(ois);
        assertThat(user1, is(user));
    }
}
