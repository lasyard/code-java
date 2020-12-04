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

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.BeanDeserializerModifier;

public class PeaBeanDeserializerModifier extends BeanDeserializerModifier {
    @SuppressWarnings("unchecked")
    @Override
    public JsonDeserializer<?> modifyDeserializer(
        DeserializationConfig config,
        BeanDescription beanDesc,
        JsonDeserializer<?> deserializer
    ) {
        if (Pea.class.isAssignableFrom(beanDesc.getBeanClass())) {
            return new PeaDeserializer((JsonDeserializer<Object>) deserializer);
        }
        return deserializer;
    }
}
