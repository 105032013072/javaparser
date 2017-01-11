/*
 * Copyright (C) 2007-2010 Júlio Vilmar Gesser.
 * Copyright (C) 2011, 2013-2016 The JavaParser Team.
 *
 * This file is part of JavaParser.
 *
 * JavaParser can be used either under the terms of
 * a) the GNU Lesser General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 * b) the terms of the Apache License
 *
 * You should have received a copy of both licenses in LICENCE.LGPL and
 * LICENCE.APACHE. Please refer to those files for details.
 *
 * JavaParser is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 */

package com.github.javaparser.javadoc;

import com.github.javaparser.ast.comments.JavadocComment;
import com.github.javaparser.javadoc.description.JavadocDescription;

import java.util.LinkedList;
import java.util.List;

/**
 * The structured content of a single Javadoc Comment.
 * <p>
 * It is composed by a description and a list of block tags.
 */
public class JavadocDocument {
    private JavadocDescription description;
    private List<JavadocBlockTag> blockTags;

    public JavadocDocument(JavadocDescription description) {
        this.description = description;
        this.blockTags = new LinkedList<>();
    }

    public JavadocDocument addBlockTag(JavadocBlockTag blockTag) {
        this.blockTags.add(blockTag);
        return this;
    }

    public JavadocDocument addBlockTag(String tagName, String content) {
        return addBlockTag(new JavadocBlockTag(tagName, content));
    }

    public JavadocDocument addBlockTag(String tagName) {
        return addBlockTag(tagName, "");
    }

    public JavadocComment toJavadocComment() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JavadocDocument document = (JavadocDocument) o;

        if (!description.equals(document.description)) return false;
        return blockTags.equals(document.blockTags);

    }

    @Override
    public int hashCode() {
        int result = description.hashCode();
        result = 31 * result + blockTags.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "JavadocDocument{" +
                "description=" + description +
                ", blockTags=" + blockTags +
                '}';
    }
}
