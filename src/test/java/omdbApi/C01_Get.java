package omdbApi;

import com.google.gson.JsonArray;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.protocol.RequestExpectContinue;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C01_Get {
    @Test
    public void hhtpFormat() throws IOException {
        //http://www.omdbapi.com/?t=comedy&y=2020&plot=full
        URL url = new URL("http://www.omdbapi.com/?t=comedy&y=2020&apikey=1959720c");
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        System.out.println(http.getResponseCode() + " " + http.getResponseMessage());
        http.disconnect();
    }

    @Test
    public void req() {
        RequestSpecification req=new RequestSpecBuilder().setBaseUri("http://www.omdbapi.com/?t=comedy&y=2020&plot=full")
                .setKeyStore("apikey","1959720c").build();
        Response response=given()
                .spec(req)
                .accept(ContentType.JSON)
                .when()
                .get();
       // response.prettyPrint();
        //assert
        response.then()
                .assertThat()
                .statusCode(200)
                .contentType("text/html; charset=utf-8");
        /*

Title:Comedy
Year:2020
Rated:N/A
Released:17 Jul 2020
Runtime:N/A
Genre:Animation, Short, Comedy, Horror
Director:David Cazares
Writer:David Cazares
Actors:David Cazares, Zach Fuller
Plot:Laugh with Us
Language:English
Country:Mexico, USA
Awards:N/A
Poster:https://m.media-amazon.com/images/M/MV5BMTA2YTJhZDUtODVmNS00MjRiLWEzNzMtYTRkMjk4ZjI2OWYxXkEyXkFqcGdeQXVyNjg2MDY1NzE@._V1_SX300.jpg
Ratings[1]
Metascore:N/A
imdbRating:8.8
imdbVotes:6
imdbID:tt12754686
Type:movie
DVD:N/A...
BoxOffice:N/A
Production:N/A
Website:N/A
Response:True
         */

    }
    @Test
    public void res() {
        //response olustur
        Response response=given()
                .queryParams("apikey","1959720c")
                .contentType(ContentType.JSON)
                .when()
                .get("http://www.omdbapi.com/?t=comedy&y=2020&plot=full");
        response.prettyPrint();
        //exp body olustur
        JSONObject inner=new JSONObject();
        JSONArray one=new JSONArray();
        JSONObject exp=new JSONObject();
        inner.put("Source", "Internet Movie Database");
        inner.put( "Value", "8.8/10");
        one.put(0,inner);
        exp.put("Title", "Comedy");
        exp.put("Year", "2020");
        exp.put("Rated", "N/A");
        exp.put("Genre", "Animation, Short, Comedy, Horror");
        exp.put("Released", "17 Jul 2020");
        exp.put("Runtime","N/A");
        exp.put("Director", "David Cazares");
        exp.put("Writer", "David Cazares");
        exp.put("Actors", "David Cazares, Zach Fuller");
        exp.put("Actors", "David Cazares, Zach Fuller");
        exp.put("Plot", "Laugh with Us");
        exp.put("Language", "English");
        exp.put("Country", "Mexico, USA");
        exp.put("Awards", "N/A");
        exp.put("Poster", "https://m.media-amazon.com/images/M/MV5BMTA2YTJhZDUtODVmNS00MjRiLWEzNzMtYTRkMjk4ZjI2OWYxXkEyXkFqcGdeQXVyNjg2MDY1NzE@._V1_SX300.jpg");
        exp.put("Ratings",one);
        exp.put("Metascore", "N/A");
        exp.put("Production", "N/A");
        exp.put("BoxOffice", "N/A");
        exp.put("DVD", "N/A");
        exp.put("Type", "movie");
        exp.put("Response", "True");
        exp.put("imdbID", "tt12754686");
        exp.put("imdbVotes", "6");
        exp.put("imdbRating", "8.8");

        /*
        {
    "Title": "Comedy",
    "Year": "2020",
    "Rated": "N/A",
    "Released": "17 Jul 2020",
    "Runtime": "N/A",
    "Genre": "Animation, Short, Comedy, Horror",
    "Director": "David Cazares",
    "Writer": "David Cazares",
    "Actors": "David Cazares, Zach Fuller",
    "Plot": "Laugh with Us",
    "Language": "English",
    "Country": "Mexico, USA",
    "Awards": "N/A",
    "Poster": "https://m.media-amazon.com/images/M/MV5BMTA2YTJhZDUtODVmNS00MjRiLWEzNzMtYTRkMjk4ZjI2OWYxXkEyXkFqcGdeQXVyNjg2MDY1NzE@._V1_SX300.jpg",
    "Ratings": [
        {
            "Source": "Internet Movie Database",
            "Value": "8.8/10"
        }
    ],
    "Metascore": "N/A",
    "imdbRating": "8.8",
    "imdbVotes": "6",
    "imdbID": "tt12754686",
    "Type": "movie",
    "DVD": "N/A",
    "BoxOffice": "N/A",
    "Production": "N/A",
    "Website": "N/A",
    "Response": "True"
}

         */
        //reponse kaydet
        JsonPath act=response.jsonPath();
        //assert yap
        response
                .then()
                .assertThat()
                .statusCode(200)

                .contentType("application/json; charset=utf-8");
          /*
        {
    "Title": "Comedy",
    "Year": "2020",
    "Rated": "N/A",
    "Released": "17 Jul 2020",
    "Runtime": "N/A",
    "Genre": "Animation, Short, Comedy, Horror",
    "Director": "David Cazares",
    "Writer": "David Cazares",
    "Actors": "David Cazares, Zach Fuller",
    "Plot": "Laugh with Us",
    "Language": "English",
    "Country": "Mexico, USA",
    "Awards": "N/A",
    "Poster": "https://m.media-amazon.com/images/M/MV5BMTA2YTJhZDUtODVmNS00MjRiLWEzNzMtYTRkMjk4ZjI2OWYxXkEyXkFqcGdeQXVyNjg2MDY1NzE@._V1_SX300.jpg",
    "Ratings": [
        {
            "Source": "Internet Movie Database",
            "Value": "8.8/10"
        }
    ],
         */
        assertEquals(exp.get("Released"),act.get("Released"));
        assertEquals(exp.get("Runtime"),act.get("Runtime"));
        assertEquals(exp.get("Genre"),act.get("Genre"));
        assertEquals(exp.get("Director"),act.get("Director"));
        assertEquals(exp.get( "Writer"),act.get( "Writer"));
        assertEquals(exp.get( "Actors"),act.get( "Actors"));
        assertEquals(exp.get("Plot"),act.get("Plot"));
        assertEquals(exp.get("Language"),act.get("Language"));
        assertEquals(exp.get("Country"),act.get("Country"));
        assertEquals(exp.get("Awards"),act.get("Awards"));
        assertEquals(exp.get("Poster"),act.get("Poster"));
       // assertEquals(    ((JSONObject)exp.getJSONArray("Ratings").get(0)).getJSONObject("Value"),act.get("Ratings.one.Source"));
       // assertEquals(exp.getJSONArray("Ratings").get(1),act.get("Ratings.one.Value"));

        assertEquals(exp.get("Title"),act.get("Title"));
        assertEquals(exp.get("Year"),act.get("Year"));
        assertEquals(exp.get("Rated"),act.get("Rated"));
        assertEquals(exp.get("Metascore"),act.get("Metascore"));
        assertEquals(exp.get("imdbRating"  ),act.get("imdbRating"));
        assertEquals(exp.get("imdbVotes"),act.get("imdbVotes"));
        assertEquals(exp.get("imdbID"),act.get("imdbID"));
        assertEquals(exp.get("Type"),act.get("Type"));
        assertEquals(exp.get("BoxOffice"),act.get("BoxOffice"));
        assertEquals(exp.get("Production"),act.get("Production"));
        assertEquals(exp.get("Response"),act.get("Response"));
        assertEquals(exp.get("DVD"),act.get("DVD"));






    }

}
