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

package io.github.lasyard.code.antlr4.array.init;

class Visitor extends ArrayInitBaseVisitor<Node> {
    @Override
    public ArrayNode visitInit(ArrayInitParser.InitContext ctx) {
        ArrayNode array = new ArrayNode(ctx.value().size());
        int i = 0;
        for (ArrayInitParser.ValueContext valueContext : ctx.value()) {
            array.set(i++, visitValue(valueContext));
        }
        return array;
    }

    @Override
    public Node visitValue(ArrayInitParser.ValueContext ctx) {
        if (ctx.init() != null) {
            return visitInit(ctx.init());
        } else if (ctx.INT() != null) {
            return new ValueNode(Integer.parseInt(ctx.INT().getText()));
        }
        throw new RuntimeException("Not implemented! Context: " + ctx);
    }
}
