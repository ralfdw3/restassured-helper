package dsl;

import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static dsl.AssertBodyBuilder.*;
import static dsl.Executor.realizandoPost;
import static dsl.RequisicaoBuilder.paraPath;

@WireMockTest(httpPort = 8080)
public class RestAssuredHelper {
    private final String WHATEVER_URL = "/v1/we";

    @BeforeEach
    void setUp() {
        resetAllRequests();
    }

    @Test
    void shouldDoHisThing() {
        stubFor(post(urlMatching(WHATEVER_URL))
                .willReturn(okJson("""
                        {
                          "nome": "Ralf Drehmer",
                          "idade": 29,
                          "ativo": true,
                          "enderecos": [
                            {
                              "tipo": "residencial",
                              "cidade": "Porto Alegre",
                              "estado": "RS"
                            },
                            {
                              "tipo": "comercial",
                              "cidade": "São Paulo",
                              "estado": "SP"
                            }
                          ],
                          "preferencias": {
                            "newsletter": false,
                            "notificacoes": ["email", "sms"]
                          },
                          "pontuacoes": [10.5, 9.8, 7.0],
                          "ultimosAcessos": null
                        }
                        """)));

        realizandoPost(paraPath(WHATEVER_URL)
                .comEntrada("whatever"))
                .esperoRetorno()
                .comStatus(200)
                .contem("preferencias.notificacoes[0]", Matchers.equalTo("email"))
                .contemNaString(noCampo("nome").essaDescricao("Ralf Drehmer"))
                .contemIntegerExato(noCampo("idade").esseInteiro(29))
                .contemBoolean(noCampo("ativo").esseBoolean(true))
                .contemStringExata(noObjetoDaLista("enderecos", 0, "tipo").essaDescricao("residencial"))
                .contemStringExata(noObjetoDaLista("enderecos", 0, "cidade").essaDescricao("Porto Alegre"))
                .contemStringExata(noObjetoDaLista("enderecos", 0, "estado").essaDescricao("RS"))
                .contemStringExata(noObjetoDaLista("enderecos", 1, "tipo").essaDescricao("comercial"))
                .contemStringExata(noObjetoDaLista("enderecos", 1, "cidade").essaDescricao("São Paulo"))
                .contemStringExata(noObjetoDaLista("enderecos", 1, "estado").essaDescricao("SP"))
                .contemBoolean(noObjeto("preferencias", "newsletter").esseBoolean(false))
                .contemStringExata(noObjetoDaLista("preferencias", 0, "notificacoes").essaDescricao("email"))
                .contemStringExata(noObjetoDaLista("preferencias", 1, "notificacoes").essaDescricao("sms"))
                .contemFloatExato(naLista("pontuacoes", 0).esseFloat(10.5F))
                .contemFloatExato(naLista("pontuacoes", 1).esseFloat(9.8F))
                .contemFloatExato(naLista("pontuacoes", 2).esseFloat(7.0F))
                .contemValorNulo(noCampo("ultimosAcessos"));
    }
}
