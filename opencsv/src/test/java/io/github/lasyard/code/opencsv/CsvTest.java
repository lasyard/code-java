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

package io.github.lasyard.code.opencsv;

import com.opencsv.bean.CsvToBeanBuilder;
import org.junit.Test;

import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class CsvTest {
    @Test
    public void test() {
        List<Event> events = new CsvToBeanBuilder<Event>(new InputStreamReader(
            getClass().getResourceAsStream(("/data.csv")),
            StandardCharsets.UTF_8)
        )
            .withType(Event.class)
            .build()
            .parse();
        assertThat(events.size(), is(9));
        long count = 1;
        for (Event event : events) {
            assertThat(event.getId(), is(count++));
        }
    }
}
