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

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static java.lang.reflect.Modifier.isPublic;
import static java.lang.reflect.Modifier.isStatic;

public final class BeanUtils {
    private BeanUtils() {
    }

    @Nullable
    public static Object getPropertyValue(@Nonnull Object obj, String propertyName) {
        try {
            Field f = obj.getClass().getField(propertyName);
            if (isPublic(f.getModifiers()) && !isStatic(f.getModifiers())) {
                return f.get(obj);
            }
        } catch (NoSuchFieldException | IllegalAccessException ignored) {
        }
        try {
            Method m = obj.getClass().getMethod("get" + StringUtils.capitalize(propertyName));
            if (isPublic(m.getModifiers()) && !isStatic(m.getModifiers())) {
                return m.invoke(obj);
            }
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException ignored) {
        }
        return null;
    }
}
