package io.bridge.ws;

/*-
 * #%L
 * io-bridge-ws
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

import static java.lang.Boolean.parseBoolean;
import static org.apache.commons.lang3.StringUtils.trimToNull;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;

import io.bridge.api.Attribute;
import io.bridge.api.IOProperty;
import io.bridge.api.IOSource;
import io.bridge.api.IOSourceManager;
import io.bridge.api.exceptions.InvalidAttributeException;
import io.javalin.Javalin;
import io.javalin.http.NotFoundResponse;

/**
 *
 * @author Charaf-Eddine SAIDI
 *
 */
public class IOApp {

    private static final String ERROR_PROPERTY_NOT_FOUND = "Property not found";
    private static final String ERROR_PROTOCOL_NOT_FOUND = "Protocol not found";
    private static final String ERROR_SOURCE_NOT_FOUND = "Source not found";
    private static final String PARAM_PROPERTY = "property";
    private static final String PARAM_PROTOCOL = "protocol";
    private static final String PARAM_SOURCE = "source";
    private static final String PARAM_REFRESH = "refresh";

    public static void main(String[] args) {
        Javalin app = Javalin.create().start(getPort());
        app.get("/", ctx -> ctx.result("IO-Bridge REST API"));

        // Handle exception
        app.exception(InvalidAttributeException.class, (e, ctx) -> {
            ctx.status(400);
            ctx.result(e.getMessage());
        });
        app.exception(RuntimeException.class, (e, ctx) -> {
            ctx.status(400);
            ctx.result(e.getMessage());
        });

        // List all available sources
        app.get("/sources", ctx -> {
            boolean refresh = parseBoolean(ctx.queryParam(PARAM_REFRESH));
            List<IOSource> sources = IOSourceManager.getIOSources(refresh);
            String response = sources.stream().map(IOSource::getName).collect(Collectors.toList()).toString();
            ctx.result(response);
        });

        // List a source supported protocols
        app.get("/:source/protocols", ctx -> {
            boolean refresh = parseBoolean(ctx.queryParam(PARAM_REFRESH));
            Optional<IOSource> source = IOSourceManager.getIOSource(ctx.pathParam(PARAM_SOURCE), refresh);
            if (!source.isPresent()) {
                throw new NotFoundResponse(ERROR_SOURCE_NOT_FOUND);
            }
            Set<String> protocols = source.get().getProtocols();
            if (protocols == null) {
                protocols = Collections.emptySet();
            }
            ctx.result(protocols.toString());
        });

        // List a source supported properties
        app.get("/:source/:protocol/properties", ctx -> {
            boolean refresh = parseBoolean(ctx.queryParam(PARAM_REFRESH));
            Optional<IOSource> source = IOSourceManager.getIOSource(ctx.pathParam(PARAM_SOURCE), refresh);
            if (!source.isPresent()) {
                throw new NotFoundResponse(ERROR_SOURCE_NOT_FOUND);
            }
            Set<String> protocols = source.get().getProtocols();
            if (protocols == null) {
                protocols = Collections.emptySet();
            }

            Optional<String> protocol = protocols.stream().filter(p -> ctx.pathParam(PARAM_PROTOCOL).equals(p)).findFirst();
            if (!protocol.isPresent()) {
                throw new NotFoundResponse(ERROR_PROTOCOL_NOT_FOUND);
            }

            Map<String, IOProperty> properties = source.get().getProperties(protocol.get());
            if (properties == null) {
                properties = Collections.emptyMap();
            }

            ctx.result(properties.keySet().toString());
        });

        // List a property attributes
        app.get("/:source/:protocol/:property/attributes", ctx -> {
            boolean refresh = parseBoolean(ctx.queryParam(PARAM_REFRESH));
            Optional<IOSource> source = IOSourceManager.getIOSource(ctx.pathParam(PARAM_SOURCE), refresh);
            if (!source.isPresent()) {
                throw new NotFoundResponse(ERROR_SOURCE_NOT_FOUND);
            }
            Set<String> protocols = source.get().getProtocols();
            if (protocols == null) {
                protocols = Collections.emptySet();
            }

            Optional<String> protocol = protocols.stream().filter(p -> ctx.pathParam(PARAM_PROTOCOL).equals(p)).findFirst();
            if (!protocol.isPresent()) {
                throw new NotFoundResponse(ERROR_PROTOCOL_NOT_FOUND);
            }

            Map<String, IOProperty> properties = source.get().getProperties(protocol.get());
            if (properties == null) {
                properties = Collections.emptyMap();
            }

            IOProperty property = properties.get(ctx.pathParam(PARAM_PROPERTY));
            if (property == null) {
                throw new NotFoundResponse(ERROR_PROPERTY_NOT_FOUND);
            }

            Map<String, Attribute> attributes = property.getAttributes();
            if (attributes == null) {
                attributes = Collections.emptyMap();
            }

            ctx.result(attributes.keySet().toString());
        });

        // Read a property
        app.get("/:source/:protocol/:property", ctx -> {

            // 1. Get IO source
            boolean refresh = parseBoolean(ctx.queryParam(PARAM_REFRESH));
            Optional<IOSource> source = IOSourceManager.getIOSource(ctx.pathParam(PARAM_SOURCE), refresh);
            if (!source.isPresent()) {
                throw new NotFoundResponse(ERROR_SOURCE_NOT_FOUND);
            }

            // 2. Get IO Protocol
            Set<String> protocols = source.get().getProtocols();
            if (CollectionUtils.isEmpty(protocols) || !protocols.contains(ctx.pathParam(PARAM_PROTOCOL))) {
                throw new NotFoundResponse(ERROR_PROTOCOL_NOT_FOUND);
            }

            // 3.1 Get IO Property to read
            Map<String, IOProperty> properties = source.get().getProperties(ctx.pathParam(PARAM_PROTOCOL));
            if (MapUtils.isEmpty(properties) || !properties.containsKey(ctx.pathParam(PARAM_PROPERTY))) {
                throw new NotFoundResponse(ERROR_PROPERTY_NOT_FOUND);
            }

            // 3.2 Set property attributes
            Map<String, List<String>> params = ctx.queryParamMap();
            for (Entry<String, List<String>> e : params.entrySet()) {
                Attribute a = properties.get(ctx.pathParam(PARAM_PROPERTY)).getAttributes().get(e.getKey());
                if (a != null && CollectionUtils.isNotEmpty(e.getValue())) {
                    String value = e.getValue().get(0);
                    if (StringUtils.isNotBlank(value)) {
                        a.setValue(value);
                    }
                }
            }

            // 4. Read property
            source.get().read(properties.get(ctx.pathParam(PARAM_PROPERTY)));

            // 5. Return result
            ctx.result(properties.get(ctx.pathParam(PARAM_PROPERTY)).getValue());
        });

        app.post("/:source/:protocol/:property", new PropertiePostHandler());
    }

    private static int getPort() {
        String portStr = trimToNull(System.getenv("port"));
        int port;
        try {
            port = Integer.parseInt(portStr);
        } catch (Exception e) {
            // Ignore parsing errors
            port = 80;
        }
        return port;
    }

}
