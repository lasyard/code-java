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

package io.github.lasyard.code.quiz;

final class CalcBits {
    private CalcBits() {
    }

    static int calcBits(int num) {
        int bits = 0;
        while (num > 0) {
            if (num % 2 == 1) {
                bits++;
            }
            num >>= 1;
        }
        return bits;
    }

    static int calcBitsRecursive(int num) {
        if (num < 2) {
            return num;
        }
        return calcBitsRecursive(num / 2) + num % 2;
    }
}
