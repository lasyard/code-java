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

final class CalcBottles {
    private CalcBottles() {
    }

    static int calcBottles(int num) {
        int totalBottles = 0;
        int emptyBottles = 0;
        int emptyCovers = 0;
        int newBottles = num / 2;
        while (newBottles > 0) {
            totalBottles += newBottles;
            emptyBottles += newBottles;
            emptyCovers += newBottles;
            newBottles = emptyBottles / 2 + emptyCovers / 4;
            emptyBottles %= 2;
            emptyCovers %= 4;
        }
        return totalBottles;
    }
}
