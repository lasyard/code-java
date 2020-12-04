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

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.deser.ResolvableDeserializer;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

public class PeaDeserializer extends StdDeserializer<Pea> implements ContextualDeserializer, ResolvableDeserializer {
    private static final long serialVersionUID = 2429400944348722639L;

    private static final PeaDeserializer ID_ONLY = new PeaDeserializer(null);

    private final JsonDeserializer<Object> defaultDeserializer;

    protected PeaDeserializer(JsonDeserializer<Object> defaultDeserializer) {
        super(Pea.class);
        this.defaultDeserializer = defaultDeserializer;
    }

    @Override
    public Pea deserialize(JsonParser parser, DeserializationContext ctx) throws IOException {
        if (defaultDeserializer == null) {
            Pea pea = new Pea();
            pea.setId(parser.getValueAsInt());
            return pea;
        } else {
            return (Pea) defaultDeserializer.deserialize(parser, ctx);
        }
    }

    // for some reason you have to implement ResolvableDeserializer when modifying BeanDeserializer
    @Override
    public void resolve(DeserializationContext ctx) throws JsonMappingException {
        ((ResolvableDeserializer) defaultDeserializer).resolve(ctx);
    }

    @Override
    public JsonDeserializer<?> createContextual(
        DeserializationContext ctx,
        BeanProperty property
    ) {
        if (property != null && Pea.class.isAssignableFrom(property.getType().getRawClass())) {
            return ID_ONLY;
        }
        return this;
    }
}
