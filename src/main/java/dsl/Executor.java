package dsl;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static java.util.Objects.isNull;

public class Executor {

    public static AssercoesRequisicao realizandoPost(final Requisicao requisicao) {
        return new AssercoesRequisicao(given()
                .spec(definirHeaders(requisicao))
                .contentType(ContentType.JSON)
                .body(requisicao.body())
                .when()
                .post(requisicao.path()));
    }

    public static AssercoesRequisicao realizandoGet(final Requisicao requisicao) {
        return new AssercoesRequisicao(given()
                .spec(definirHeaders(requisicao))
                .contentType(ContentType.JSON)
                .body(requisicao.body())
                .when()
                .get(requisicao.path()));
    }

    private static RequestSpecification definirHeaders(final Requisicao requisicao) {
        final Map<String, String> headers = requisicao.headers();

        return isNull(headers) || headers.isEmpty() ? requestSpecificationDefault() : construirHeaders(headers);
    }

    private static RequestSpecification requestSpecificationDefault() {
        return RestAssured
                .given()
                .headers("identidade", "consumidor_teste",
                        "intencao", "teste");
    }

    private static RequestSpecification construirHeaders(final Map<String, String> headers) {
        return RestAssured
                .given()
                .headers(headers);
    }
}
