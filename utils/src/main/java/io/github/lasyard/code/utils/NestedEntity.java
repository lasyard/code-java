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

import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.Nonnull;

public interface NestedEntity {
    Collection<? extends NestedEntity> getNested();

    default <T extends NestedEntity> Set<String> getPropertySet(@Nonnull Class<T> type, String propertyName) {
        Set<String> props = new HashSet<>();
        if (type.isAssignableFrom(this.getClass())) {
            try {
                String value = BeanUtils.getProperty(this, propertyName);
                if (value != null) {
                    props.add(value);
                }
            } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException ignored) {
            }
        }
        Collection<? extends NestedEntity> children = getNested();
        if (children != null) {
            for (NestedEntity child : children) {
                props.addAll(child.getPropertySet(type, propertyName));
            }
        }
        return props;
    }
}
