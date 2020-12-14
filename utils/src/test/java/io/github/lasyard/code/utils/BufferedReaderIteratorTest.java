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

package io.github.lasyard.code.utils;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@Slf4j
public class BufferedReaderIteratorTest {
    private static final String FILE = "/test.txt";

    @Test
    public void test() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(
            getClass().getResourceAsStream(FILE),
            StandardCharsets.UTF_8
        ));
        List<String> lines = Files.readAllLines(
            Paths.get(getClass().getResource(FILE).getPath()),
            StandardCharsets.UTF_8
        );
        int count = 0;
        for (String line : new BufferedReaderIterator(in)) {
            log.info(line);
            assertThat(line, is(lines.get(count++)));
        }
    }
}
