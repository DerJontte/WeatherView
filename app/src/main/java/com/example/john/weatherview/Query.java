package com.example.john.weatherview;

public class Query {
    private String query = new String();

    public void addParameter(String name, String[] values) {
        String valuesFormatted = new String();
        for(int i = 0; i < values.length; i++) {
            valuesFormatted = valuesFormatted.concat(values[i] + ",");
        }
        query = query.concat("&" + name + "=" + valuesFormatted);
    }
}
