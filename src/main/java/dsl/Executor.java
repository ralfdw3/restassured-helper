package dsl;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static java.util.Objects.isNull;

public class Executor {
    private final Response resposta;

    public Executor(final Requisicao requisicao) {
        this.resposta = given()
                .spec(definirHeaders(requisicao))
                .contentType(ContentType.JSON)
                .body(requisicao.body())
                .when()
                .post(requisicao.path());
    }

    private RequestSpecification definirHeaders(final Requisicao requisicao) {
        Map<String, String> headers = requisicao.headers();

        return isNull(headers) || headers.isEmpty() ? requestSpecificationDefault() : construirHeaders(headers);
    }

    private RequestSpecification requestSpecificationDefault() {
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

    public static Executor realizandoPost(final Requisicao requisicao) {
        return new Executor(requisicao);
    }

    public AssercoesRequisicao esperoRetorno() {
        return new AssercoesRequisicao(resposta);
    }
}
