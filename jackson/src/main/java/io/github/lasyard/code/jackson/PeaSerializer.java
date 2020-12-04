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

package io.github.lasyard.code.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class PeaSerializer extends StdSerializer<Pea> implements ContextualSerializer {
    private static final long serialVersionUID = -2374770972725620295L;

    private static final PeaSerializer ID_ONLY = new PeaSerializer(null);

    private final JsonSerializer<Object> defaultSerializer;

    protected PeaSerializer(JsonSerializer<Object> defaultSerializer) {
        super(Pea.class);
        this.defaultSerializer = defaultSerializer;
    }

    @Override
    public void serialize(Pea pea, JsonGenerator gen, SerializerProvider provider) throws IOException {
        if (defaultSerializer == null) {
            gen.writeNumber(pea.getId());
        } else {
            defaultSerializer.serialize(pea, gen, provider);
        }
    }

    @Override
    public JsonSerializer<?> createContextual(
        SerializerProvider provider,
        BeanProperty property
    ) {
        if (property != null && Pea.class.isAssignableFrom(property.getType().getRawClass())) {
            return ID_ONLY;
        }
        return this;
    }
}
