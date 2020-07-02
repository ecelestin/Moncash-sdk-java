package com.digicelgroup.moncash.consomer;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

public class Model {
    public String toJSON() {
        return new GsonBuilder().setPrettyPrinting().create().toJson(this);
    }

    @Override
    public String toString() {
        return toJSON();
    }
}
