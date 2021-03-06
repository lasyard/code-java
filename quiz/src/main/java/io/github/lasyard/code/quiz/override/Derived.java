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

package io.github.lasyard.code.quiz.override;

public class Derived extends Base {
    static final String SAY = "Derived is saying.";
    static final String SAY_ELSE = "Derived is saying something else.";

    @Override
    public String say() {
        return SAY;
    }

    /**
     * Add @Override here would cause an error in compilation.
     */
    // @Override
    public String saySomethingElse() {
        return SAY_ELSE;
    }
}
