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

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

public class LineReader {
    private byte[] buffer;
    private int maxLineLength;
    private int lineStart;
    private int writePos;

    public LineReader(int bufferSize) {
        this(new byte[bufferSize]);
    }

    public LineReader(byte[] buffer) {
        this(buffer, buffer.length / 2);
    }

    public LineReader(int bufferSize, int maxLineLength) {
        this(new byte[bufferSize], maxLineLength);
    }

    public LineReader(byte[] buffer, int maxLineLength) {
        this.buffer = buffer;
        this.maxLineLength = maxLineLength;
        reset();
    }

    public void reset() {
        lineStart = 0;
        writePos = 0;
    }

    public String readLine(RandomAccessFile file, Charset charset) throws IOException {
        ByteBuffer byteBuffer = readLine(file);
        if (byteBuffer == null) {
            return null;
        }
        return stringfy(byteBuffer, charset);
    }

    public ByteBuffer readLine(RandomAccessFile file) throws IOException {
        int readPos = lineStart;
        while (true) {
            while (readPos == writePos) {
                int bytes = file.read(buffer, writePos, buffer.length - writePos);
                if (bytes == -1) {
                    return null;
                }
                writePos += bytes;
            }
            for (; readPos < writePos; readPos++) {
                int ch = buffer[readPos];
                if (ch == '\n') {
                    ByteBuffer byteBuffer = null;
                    if (readPos > 0 && buffer[readPos - 1] == '\r') {
                        byteBuffer = ByteBuffer.wrap(buffer, lineStart, readPos - 1 - lineStart);
                    } else {
                        byteBuffer = ByteBuffer.wrap(buffer, lineStart, readPos - lineStart);
                    }
                    lineStart = readPos + 1;
                    if (lineStart == buffer.length) {
                        lineStart = 0;
                        writePos = 0;
                    }
                    return byteBuffer;
                } else if (readPos - lineStart == maxLineLength) {
                    ByteBuffer byteBuffer = ByteBuffer.wrap(buffer, lineStart, readPos - lineStart);
                    lineStart = readPos;
                    return byteBuffer;
                }
            }
            if (writePos == buffer.length) {
                int length = writePos - lineStart;
                System.arraycopy(buffer, lineStart, buffer, 0, length);
                lineStart = 0;
                writePos = length;
                readPos = writePos;
            }
        }
    }

    public String stringfy(ByteBuffer byteBuffer, Charset charset) {
        return new String(
            byteBuffer.array(),
            byteBuffer.arrayOffset() + byteBuffer.position(),
            byteBuffer.limit() - byteBuffer.position(),
            charset
        );
    }
}
