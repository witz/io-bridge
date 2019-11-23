package io.bridge.api;

/*-
 * #%L
 * IO Bridge API
 * %%
 * Copyright (C) 2019 Witz
 * %%
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 * #L%
 */

import java.util.List;
import java.util.Optional;
import java.util.ServiceLoader;

import org.apache.commons.collections4.IteratorUtils;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Charaf-Eddine SAIDI
 *
 */
public enum IOSourceManager {
    INSTANCE;

    private final ServiceLoader<IOSource> loader;

    private IOSourceManager() {
        loader = ServiceLoader.load(IOSource.class);
    }

    public List<IOSource> getIOSources(boolean refresh) {
        if (refresh) {
            loader.reload();
        }
        return IteratorUtils.toList(loader.iterator());
    }

    public Optional<IOSource> getIOSource(String name) {
        return getIOSource(name, false);
    }

    public Optional<IOSource> getIOSource(String name, boolean refresh) {
        List<IOSource> sources = getIOSources(refresh);
        return sources.stream().filter(s -> StringUtils.equals(name, s.getName())).findFirst();
    }
}
