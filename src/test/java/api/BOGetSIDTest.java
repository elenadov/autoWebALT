package api;

import groovy.transform.ToString;
import io.restassured.http.ContentType;
import io.restassured.response.ResponseBody;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import paramsForRequests.CurrencyValues;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import static endpoints.EndPoints.TEST_GS_ALT_WEB;
import static io.restassured.RestAssured.given;
import static java.lang.System.currentTimeMillis;
import static java.nio.file.Paths.get;
import static libs.Utils.getDateAndTimeFormated;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

public class BOGetSIDTest {
    @Test
    public void clientAuthAltWeb() {
//        String dateTime = getDateAndTimeFormated();
        long timestamp = Instant.now().getEpochSecond();
        Logger logger = Logger.getLogger(String.valueOf(getClass()));

        JSONObject requestParams = new JSONObject();
        requestParams.put("PROTO_VER", "3");
        requestParams.put("ACTION", "BOGetSID");
        requestParams.put("CHANNEL_TYPE", "web_alt");
        requestParams.put("CLIENT_TRANS_ID", timestamp);
        requestParams.put("LANG", "ua");
        requestParams.put("LOGIN", "7600005");
        requestParams.put("PASSWD", "7600005");
        requestParams.put("PROTO_TYPE", "keyvalue-json");
        logger.info("");

        ResponseBody response =
                given()
                        .params(requestParams.toMap())
//                        .contentType(ContentType.JSON)
                        .headers("Host", "test.alt.emict.net")
//                        .body(requestParams.toMap())
                        .when()
                        .post(TEST_GS_ALT_WEB)// Example POST method
                        .then()
                        .assertThat()
                        .statusCode(200)
                        .assertThat()
                        .body(containsString("user_id"))
                        .assertThat()
                        .body(containsString("client_id"))
                        .assertThat()
                        .body(containsString("sid"))
                        .assertThat()
                        .body(containsString("client_trans_id"))
                        .assertThat()
//                        .body()
//                        .contentType(ContentType.JSON)
                        .log().all()
                        .extract()
                        .response().getBody();
//        Assert.assertEquals("Incorrect Success Code was returned", response.htmlPath().get("client_trans_id"), requestParams.get("CLIENT_TRANS_ID"));

    }
}
