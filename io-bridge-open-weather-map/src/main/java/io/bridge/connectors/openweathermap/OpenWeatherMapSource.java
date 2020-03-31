package io.bridge.connectors.openweathermap;

/*-
 * #%L
 * IOB Open Weather Map module
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

import static org.apache.commons.lang3.StringUtils.EMPTY;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.SystemUtils;
import org.json.JSONObject;

import io.bridge.api.Attribute;
import io.bridge.api.IOProperty;
import io.bridge.api.IOSource;
import kong.unirest.Unirest;

/**
 *
 * @author Charaf-Eddine SAIDI
 *
 */
public class OpenWeatherMapSource implements IOSource {

    private static final String PROTOCOL_REST_API = "REST API";
    public static final String NAME = "Open Weather Map";

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public Map<String, Attribute> getAttributes() {
        return Collections.emptyMap();
    }

    @Override
    public Set<String> getProtocols() {
        return Set.of(PROTOCOL_REST_API);
    }

    @Override
    public Map<String, IOProperty> getProperties(String protocol) {
        if (PROTOCOL_REST_API.equals(protocol)) {
            return Map.of(TemperaturProperty.NAME, new TemperaturProperty());
        }
        return Collections.emptyMap();
    }

    @Override
    public void read(IOProperty property) {
        if (property instanceof TemperaturProperty) {
            String query = property.getAttributes().get(QueryAttribute.NAME).getValue();
            String response = Unirest.get("https://community-open-weather-map.p.rapidapi.com/weather")
                    .queryString("units", "metric")
                    .queryString(QueryAttribute.NAME, query)
                    .header("X-RapidAPI-Key", SystemUtils.getEnvironmentVariable("WEATHER_RAPIDAPI_KEY", EMPTY))
                    .asString()
                    .getBody();

            if (StringUtils.isNotBlank(response)) {
                JSONObject mainJson = new JSONObject(response).optJSONObject("main");

                String result = null;
                if (mainJson != null) {
                    double temp = mainJson.optDouble("temp");
                    if (!Double.isNaN(temp)) {
                        result = Double.toString(mainJson.getDouble("temp"));
                    }
                }

                if (result == null) {
                    throw new IllegalArgumentException("Location not found");
                }

                property.setValue(result);
            }
        }

    }

    @Override
    public void write(IOProperty source) {
        throw new UnsupportedOperationException("Unsupported operation");
    }

}
