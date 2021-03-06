package com.switchfully.spectangular;

import com.switchfully.spectangular.dtos.SessionDto;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;

import static io.restassured.RestAssured.given;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class SessionControllerEndToEndTest {

    @LocalServerPort
    private int port;

    @Test
    @Sql("/sql/testSetup.sql")
    void createSession_whenCalled_thenOneMoreSessionIsPresent(){
        //GIVEN
        LocalDate testDate = LocalDate.now().plusWeeks(1);

        String requestBody = "{\"subject\":\"spring\"," +
                "\"date\":\"" + testDate + "\"," +
                "\"startTime\":\"12:12:12\", " +
                "\"location\":\"Microsoft Teams\", " +
                "\"coachId\": 1001," +
                "\"coacheeId\": 1000," +
                "\"remarks\":\"These are remarks.\"}";

        Response AuthorizepostResponse = given()
                .baseUri("http://localhost")
                .port(port)
                .basePath("/authenticate")
                .body("{\"username\":\"test@spectangular.com\",\"password\":\"YouC0ach\"}")
                .post();

        String bearerToken = AuthorizepostResponse.header("Authorization");

        //WHEN
        Response postResponse = given()
                .header("Authorization", (bearerToken == null) ? "" : bearerToken)
                .baseUri("http://localhost")
                .port(port)
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .and()
                .body(requestBody)
                .when()
                .post("/sessions");

        //THEN
        postResponse
                .then()
                .assertThat()
                .statusCode(HttpStatus.CREATED.value())
                .extract()
                .as(SessionDto.class);
    }

    @Test
    @Sql("/sql/testSetup.sql")
    void getAllCoacheeSessions_givenRequestMadeByCoachee_thenAllCoacheeSessionsAreFound(){
        //GIVEN
       Response AuthorizepostResponse = given()
                .baseUri("http://localhost")
                .port(port)
                .basePath("/authenticate")
                .body("{\"username\":\"test@spectangular.com\",\"password\":\"YouC0ach\"}")
                .post();

        String bearerToken = AuthorizepostResponse.header("Authorization");

        //WHEN
        Response postResponse = given()
                .header("Authorization", (bearerToken == null) ? "" : bearerToken)
                .baseUri("http://localhost")
                .port(port)
                .when()
                .get("/sessions/coachee");

        //THEN
        postResponse
                .then()
                .assertThat()
                .statusCode(HttpStatus.ACCEPTED.value())
                .extract()
                .as(SessionDto[].class);
    }

    @Test
    @Sql("/sql/testSetup.sql")
    void getAllCoachSessions_givenRequestMadeByCoach_thenAllCoachSessionsAreFound(){
        //GIVEN
        Response AuthorizepostResponse = given()
                .baseUri("http://localhost")
                .port(port)
                .basePath("/authenticate")
                .body("{\"username\":\"coach@spectangular.com\",\"password\":\"YouC0ach\"}")
                .post();

        String bearerToken = AuthorizepostResponse.header("Authorization");

        //WHEN
        Response postResponse = given()
                .header("Authorization", (bearerToken == null) ? "" : bearerToken)
                .baseUri("http://localhost")
                .port(port)
                .when()
                .get("/sessions/coach");

        //THEN
        postResponse
                .then()
                .assertThat()
                .statusCode(HttpStatus.ACCEPTED.value())
                .extract()
                .as(SessionDto[].class);
    }

    @Test
    @Sql("/sql/testSetup.sql")
    void getAllCoacheeSessions_givenRequestMadeByCoach_thenAllCoacheeSessionsAreFound(){
        //GIVEN
        Response AuthorizepostResponse = given()
                .baseUri("http://localhost")
                .port(port)
                .basePath("/authenticate")
                .body("{\"username\":\"coach@spectangular.com\",\"password\":\"YouC0ach\"}")
                .post();

        String bearerToken = AuthorizepostResponse.header("Authorization");

        //WHEN
        Response postResponse = given()
                .header("Authorization", (bearerToken == null) ? "" : bearerToken)
                .baseUri("http://localhost")
                .port(port)
                .when()
                .get("/sessions/coachee");

        //THEN
        postResponse
                .then()
                .assertThat()
                .statusCode(HttpStatus.ACCEPTED.value())
                .extract()
                .as(SessionDto[].class);
    }

    @Test
    @Sql("/sql/testSetup.sql")
    void getAllCoachSessions_givenRequestMadeByCoachee_thenStatusCodeIsBadRequest(){
        //GIVEN
        Response AuthorizepostResponse = given()
                .baseUri("http://localhost")
                .port(port)
                .basePath("/authenticate")
                .body("{\"username\":\"test@spectangular.com\",\"password\":\"YouC0ach\"}")
                .post();

        String bearerToken = AuthorizepostResponse.header("Authorization");

        //WHEN
        Response postResponse = given()
                .header("Authorization", (bearerToken == null) ? "" : bearerToken)
                .baseUri("http://localhost")
                .port(port)
                .when()
                .get("/sessions/coach");

        //THEN
        postResponse
                .then()
                .assertThat()
                .statusCode(HttpStatus.FORBIDDEN.value());
    }

    @Test
    @Sql("/sql/testSetup.sql")
    void updateStatus_updatesWithValidStatusChange_ReturnsSessionWithUpdatedStatus(){
        //GIVEN
        Response AuthorizepostResponse = given()
                .baseUri("http://localhost")
                .port(port)
                .basePath("/authenticate")
                .body("{\"username\":\"coach@spectangular.com\",\"password\":\"YouC0ach\"}")
                .post();

        String bearerToken = AuthorizepostResponse.header("Authorization");

        String requestBody = "{\"status\": \"ACCEPTED\"}";

        //WHEN

        Response postResponse = given()
                .header("Authorization", (bearerToken == null) ? "" : bearerToken)
                .baseUri("http://localhost")
                .port(port)
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .and()
                .body(requestBody)
                .when()
                .post("/sessions/1000000/status");

        var testvar = postResponse.then().toString();

        //THEN
        postResponse
                .then()
                .assertThat()
                .statusCode(HttpStatus.ACCEPTED.value());
    }
/*

    @Test
    @Sql("/sql/testSetup.sql")
    void createFeedbackForCoach_whenCalled_thenFeedbackIsAddedToSession(){
        //GIVEN
        Response AuthorizepostResponse = given()
                .baseUri("http://localhost")
                .port(port)
                .basePath("/authenticate")
                .body("{\"username\":\"test@spectangular.com\",\"password\":\"YouC0ach\"}")
                .post();

        String bearerToken = AuthorizepostResponse.header("Authorization");

        String requestBody = "{\"explanation\": 1, " +
                "\"usefulness\": 2, " +
                "\"positive\": \"good\", " +
                "\"negative\": \"none\"}";

        //WHEN

        Response postResponse = given()
                .header("Authorization", (bearerToken == null) ? "" : bearerToken)
                .baseUri("http://localhost")
                .port(port)
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .and()
                .body(requestBody)
                .when()
                .post("/sessions/1000000/feedback-for-coach");

        //THEN
        postResponse
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    @Sql("/sql/testSetup.sql")
    void createFeedbackForCoachee_whenCalled_thenFeedbackIsAddedToSession(){
        //GIVEN
        Response AuthorizepostResponse = given()
                .baseUri("http://localhost")
                .port(port)
                .basePath("/authenticate")
                .body("{\"username\":\"coach@spectangular.com\",\"password\":\"YouC0ach\"}")
                .post();

        String bearerToken = AuthorizepostResponse.header("Authorization");

        String requestBody = "{\"preparedness\": 1, " +
                "\"willingness\": 2, " +
                "\"positive\": \"good\", " +
                "\"negative\": \"none\"}";

        //WHEN

        Response postResponse = given()
                .header("Authorization", (bearerToken == null) ? "" : bearerToken)
                .baseUri("http://localhost")
                .port(port)
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .and()
                .body(requestBody)
                .when()
                .post("/sessions/1000000/feedback-for-coachee");

        //THEN
        postResponse
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value());
    }
*/

}
