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

package io.github.lasyard.code.antlr4.simple.expr;

import java.util.LinkedHashMap;
import java.util.Map;

class EvalVisitor extends SimpleExprParserBaseVisitor<Integer> {
    private final Map<String, Integer> memory = new LinkedHashMap<>();

    @Override
    public Integer visitOutput(SimpleExprParser.OutputContext ctx) {
        return visit(ctx.expr());
    }

    @Override
    public Integer visitAssign(SimpleExprParser.AssignContext ctx) {
        String id = ctx.ID().getText();
        Integer value = visit(ctx.expr());
        memory.put(id, value);
        return value;
    }

    @Override
    public Integer visitMulDiv(SimpleExprParser.MulDivContext ctx) {
        Integer left = visit(ctx.expr(0));
        Integer right = visit(ctx.expr(1));
        if (ctx.MUL() != null) {
            return left * right;
        }
        return left / right;
    }

    @Override
    public Integer visitAddSub(SimpleExprParser.AddSubContext ctx) {
        Integer left = visit(ctx.expr(0));
        Integer right = visit(ctx.expr(1));
        if (ctx.ADD() != null) {
            return left + right;
        }
        return left - right;
    }

    @Override
    public Integer visitInt(SimpleExprParser.IntContext ctx) {
        return Integer.valueOf(ctx.INT().getText());
    }

    @Override
    public Integer visitId(SimpleExprParser.IdContext ctx) {
        return memory.get(ctx.ID().getText());
    }

    @Override
    public Integer visitParens(SimpleExprParser.ParensContext ctx) {
        return visit(ctx.expr());
    }
}
