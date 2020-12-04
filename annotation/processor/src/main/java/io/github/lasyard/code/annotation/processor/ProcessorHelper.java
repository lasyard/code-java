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

package io.github.lasyard.code.annotation.processor;

import java.util.Map;
import javax.annotation.processing.AbstractProcessor;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;

abstract class ProcessorHelper extends AbstractProcessor {
    AnnotationMirror getAnnotationMirror(Element element, Class<?> annotationClass) {
        for (AnnotationMirror am : processingEnv.getElementUtils().getAllAnnotationMirrors(element)) {
            if (am.getAnnotationType().toString().equals(annotationClass.getName())) {
                return am;
            }
        }
        return null;
    }

    AnnotationValue getAnnotationValue(AnnotationMirror annotationMirror, String methodName) {
        for (Map.Entry<? extends ExecutableElement, ? extends AnnotationValue> entry
            : annotationMirror.getElementValues().entrySet()) {
            if (entry.getKey().getSimpleName().toString().equals(methodName)) {
                return entry.getValue();
            }
        }
        return null;
    }
}
