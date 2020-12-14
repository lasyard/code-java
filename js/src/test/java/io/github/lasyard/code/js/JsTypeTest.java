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

package io.github.lasyard.code.js;

import jdk.nashorn.api.scripting.ScriptObjectMirror;
import lombok.RequiredArgsConstructor;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStreamReader;
import java.util.HashMap;
import javax.annotation.Nonnull;
import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
@RequiredArgsConstructor
public class JsTypeTest {
    private static final Logger logger = LoggerFactory.getLogger(JsTypeTest.class);

    private static Invocable invocable;

    private final Object obj;
    private final String jsType;
    private final String javaType;

    @Nonnull
    @Parameterized.Parameters(name = "{index}: {0} -> {1}, {2}")
    public static Object[][] getParameters() {
        return new Object[][]{
            {"test", "string", "java.lang.String"},
            {1, "number", "java.lang.Integer"},
            {1L, "object", "java.lang.Long"},
            {1.0, "number", "java.lang.Double"},
            {1.0f, "number", "java.lang.Float"},
            {new Object(), "object", "java.lang.Object"},
            {new HashMap<String, Object>(), "object", "java.util.HashMap"},
        };
    }

    @BeforeClass
    public static void setupClass() throws Exception {
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
        engine.eval(new InputStreamReader(JsTypeTest.class.getResourceAsStream("/type-test.js")));
        invocable = (Invocable) engine;
    }

    @Test
    public void test() throws Exception {
        ScriptObjectMirror result = (ScriptObjectMirror) invocable.invokeFunction("test", obj);
        String jsTypeResult = (String) result.getMember("jsType");
        String javaTypeResult = (String) result.getMember("jType");
        logger.info("jsType = {}, jType = {}", jsTypeResult, javaTypeResult);
        assertEquals(jsTypeResult, jsType);
        assertEquals(javaTypeResult, javaType);
    }
}
