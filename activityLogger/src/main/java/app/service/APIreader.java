package app.service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class APIreader {
    private static final String CITY_URL = " https://dawa.aws.dk/steder?hovedtype=Bebyggelse&undertype=by&prim%C3%A6rtnavn=Roskilde";
    public static void main (String[]args) {

        APIreader apIreader = new APIreader();
        apIreader.readAPI()
    }

    public String readAPI(String url) {

        try {
            // Create an HttpClient instance
            HttpClient client = HttpClient.newHttpClient();

            // Create a request
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("https://vejr.eu/api.php?location=Roskilde&degree=C"))
                    .GET()
                    .build();

            // Send the request and get the response
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Check the status code and print the response
            if (response.statusCode() == 200) {
                System.out.println(response.body());
            } else {
                System.out.println("GET request failed. Status code: " + response.statusCode());
            }
            return  response.body();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }

    }
    public CityDTO convertFromJson(String json){
        try{

        }

    }

}
