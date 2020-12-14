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

import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.nio.charset.StandardCharsets;
import javax.annotation.Nonnull;

class FillBuffer implements Answer<Integer> {
    private final byte[] data;

    // EOF
    FillBuffer() {
        data = null;
    }

    FillBuffer(@Nonnull String data) {
        this.data = data.getBytes(StandardCharsets.US_ASCII);
    }

    @Override
    public Integer answer(InvocationOnMock args) {
        if (data == null) {
            return -1;
        }
        byte[] buf = args.getArgument(0);
        int offset = args.getArgument(1);
        int length = args.getArgument(2);
        length = Math.min(length, data.length);
        System.arraycopy(data, 0, buf, offset, length);
        return length;
    }
}
