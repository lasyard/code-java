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

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import javax.annotation.Nonnull;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

public class LineReaderTest {
    private AutoCloseable mock;

    @Mock
    private RandomAccessFile mockFile;

    @Before
    public void setup() {
        mock = MockitoAnnotations.openMocks(this);
    }

    @After
    public void tearDown() throws Exception {
        mock.close();
    }

    private void assertRead(@Nonnull LineReader lineReader, String test) throws IOException {
        String str = lineReader.readLine(mockFile, StandardCharsets.US_ASCII);
        assertThat(str, is(test));
    }

    private void assertEof(@Nonnull LineReader lineReader) throws IOException {
        ByteBuffer byteBuffer = lineReader.readLine(mockFile);
        assertNull(byteBuffer);
    }

    @Test
    public void test1() throws Exception {
        when(mockFile.read(any(byte[].class), anyInt(), anyInt()))
            .then(new FillBuffer("abc"))
            .then(new FillBuffer("def\n"))
            .then(new FillBuffer());
        LineReader lineReader = new LineReader(10, 7);
        assertRead(lineReader, "abcdef");
        assertEof(lineReader);
    }

    @Test
    public void test2() throws Exception {
        when(mockFile.read(any(byte[].class), anyInt(), anyInt()))
            .then(new FillBuffer("abc"))
            .then(new FillBuffer("\ndef\r\n"))
            .then(new FillBuffer());
        LineReader lineReader = new LineReader(10);
        assertRead(lineReader, "abc");
        assertRead(lineReader, "def");
        assertEof(lineReader);
    }

    @Test
    public void test3() throws Exception {
        when(mockFile.read(any(byte[].class), anyInt(), anyInt()))
            .then(new FillBuffer("abcde\n"))
            .then(new FillBuffer("\ndef"))
            .then(new FillBuffer("ghi\n"))
            .then(new FillBuffer());
        LineReader lineReader = new LineReader(10, 7);
        assertRead(lineReader, "abcde");
        assertRead(lineReader, "");
        assertRead(lineReader, "defghi");
        assertEof(lineReader);
    }

    @Test
    public void test4() throws Exception {
        when(mockFile.read(any(byte[].class), anyInt(), anyInt()))
            .then(new FillBuffer("abcd\n"))
            .then(new FillBuffer("efgh\n"))
            .then(new FillBuffer());
        LineReader lineReader = new LineReader(5, 5);
        assertRead(lineReader, "abcd");
        assertRead(lineReader, "efgh");
        assertEof(lineReader);
    }

    @Test
    public void test5() throws Exception {
        when(mockFile.read(any(byte[].class), anyInt(), anyInt()))
            .then(new FillBuffer("a"))
            .then(new FillBuffer("b\ncd"))
            .then(new FillBuffer("ef\n"))
            .then(new FillBuffer());
        LineReader lineReader = new LineReader(5, 2);
        assertRead(lineReader, "ab");
        assertRead(lineReader, "cd");
        assertRead(lineReader, "ef");
        assertEof(lineReader);
    }
}
