package apiCourse.clients;


import static apiCourse.constants.ProjectConstants.API_KEY;
import static apiCourse.constants.ProjectConstants.API_TOKEN;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TrelloClient {

  // Request specification example
  // Allows us not to add the same information to every call
  private static RequestSpecification trelloSpec(){
    return RestAssured
        .given().log().all()
        .contentType(ContentType.JSON)
        .queryParam("key", API_KEY)
        .queryParam("token", API_TOKEN);
  }

  // A call using the request specification
  public static Response getBoardInfo(String boardId){
    return RestAssured
        .given(trelloSpec())
        .when()
        .get("https://api.trello.com/1/boards/" + boardId)
        .then().log().all()
        .statusCode(200)
        .extract().response();
  }

  public static Response changeBoardName(String name, String boardId){
    return RestAssured
        .given().log().all()
        .contentType(ContentType.JSON)
        .queryParam("key", API_KEY)
        .queryParam("token", API_TOKEN)
        .queryParam("name", name)
        .when()
        .put("https://api.trello.com/1/boards/" + boardId)
        .then().log().all()
        .statusCode(200)
        .extract().response();
  }

  public static Response createList(String name, String boardId){
    return RestAssured
        .given().log().all()
        .contentType(ContentType.JSON)
        .queryParam("key", API_KEY)
        .queryParam("token", API_TOKEN)
        .queryParam("idBoard", boardId)
        .queryParam("name", name)
        .when()
        .post("https://api.trello.com/1/lists")
        .then().log().all()
        .extract().response();
  }

  public static Response deleteList(String id){
    return RestAssured
        .given().log().all()
        .contentType(ContentType.JSON)
        .queryParam("key", API_KEY)
        .queryParam("token", API_TOKEN)
        .queryParam("closed", true)
        .when()
        .put("https://api.trello.com/1/lists/" + id)
        .then().log().all()
        .extract().response();
  }
}
