package com.pack.java.pivot;

import javax.json.Json;
import javax.json.JsonArray;

public class PivotJSON {

    public static void main(String[] args) {
        PivotJSON pivotJSON = new PivotJSON();
        pivotJSON.JSONBuilder();
    }

    private void JSONBuilder() {
        /*JsonObject model = Json.createObjectBuilder()
                .add("Bakery", Json.createArrayBuilder().add(Json.createObjectBuilder().add("name", "Apple Cake")).add(Json.createObjectBuilder().add("name", "Banane Cake")).add(Json.createObjectBuilder().add("name", "Croissant")))
                .add("Breakfast Cereals",
                        Json.createArrayBuilder().add(Json.createObjectBuilder().add("name", "Cinnamon Flakes")).add(Json.createObjectBuilder().add("name", "Corn Flakes")).add(Json.createObjectBuilder().add("name", "Cookie Crisp")))
                .add("Condiments", Json.createArrayBuilder().add(Json.createObjectBuilder().add("name", "BBQ Sauce")).add(Json.createObjectBuilder().add("name", "Ketchup")).add(Json.createObjectBuilder().add("name", "Mustard")))
                .add("Confectionary", Json.createArrayBuilder().add(Json.createObjectBuilder().add("name", "Chocolate Bar")).add(Json.createObjectBuilder().add("name", "Nougat Bar")).add(Json.createObjectBuilder().add("name", "Milkybar")))
                .add("Fruit Preserves", Json.createArrayBuilder().add(Json.createObjectBuilder().add("name", "Mixed Fruit Jam")).add(Json.createObjectBuilder().add("name", "Marmelade")))
                .add("Soups", Json.createArrayBuilder().add(Json.createObjectBuilder().add("name", "Tomato Soup")).add(Json.createObjectBuilder().add("name", "Sweet Corn Soup"))).build();*/
        
        JsonArray model = Json.createArrayBuilder()
                .add(Json.createObjectBuilder()
                        .add("Category", "January")
                        .add("Bakery", "Apple Cake")
                        .add("Breakfast Cereals", "Cinnamon Flakes")
                        .add("Condiments", "BBQ Sauce")
                        .add("Fruit Preserves", "Mixed Fruit Jam")
                        .add("Soups", "Tomato Soup"))
                .add(Json.createObjectBuilder()
                        .add("Category", "January")
                        .add("Bakery", "Apple Cake")
                        .add("Breakfast Cereals", "Cinnamon Flakes")
                        .add("Condiments", "BBQ Sauce")
                        .add("Fruit Preserves", "Mixed Fruit Jam")
                        .add("Soups", "Tomato Soup"))
                .build();
        
        System.out.println( model.toString() );
    }
}
