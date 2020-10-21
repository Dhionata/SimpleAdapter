package br.com.example.meuprimeiroexemplo.model;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public final class RawStringTypeAdapter<T>
        implements JsonDeserializer<T> {


    private RawStringTypeAdapter() {
    }

    @Override
    public T deserialize(final JsonElement jsonElement, final Type type, final JsonDeserializationContext context)
            throws JsonParseException {
        return (T) jsonElement.toString();
    }

}
