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

import lombok.RequiredArgsConstructor;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;

@RequiredArgsConstructor
public class BufferedReaderIterator implements Iterable<String> {
    private final BufferedReader reader;

    @Override
    @Nonnull
    public Iterator<String> iterator() {
        return new Iterator<String>() {
            private String line;

            @Override
            public boolean hasNext() {
                try {
                    line = reader.readLine();
                    return line != null;
                } catch (IOException e) {
                    return false;
                }
            }

            @Override
            public String next() {
                return line;
            }
        };
    }
}
