package edu.clap;

import com.google.gson.JsonObject;

public class JAction {
        public static JsonObject main(JsonObject args){
            String name;

            try {
                name = args.getAsJsonPrimitive("name").getAsString();
            } catch(Exception e) {
                name = "Stranger";
            }

            JsonObject response = new JsonObject();
            response.addProperty("greeting", "Hello " + name + "!");
            return response;
        }
}
