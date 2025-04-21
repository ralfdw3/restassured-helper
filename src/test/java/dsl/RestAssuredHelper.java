package dsl;

import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static dsl.AssertBodyBuilder.*;
import static dsl.Executor.realizandoGet;
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
    void shouldPostHisThing() {
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
                .comStatus(200)
                .contem("preferencias.notificacoes[0]", Matchers.equalTo("email"))
                .contemNaString(noCampo("nome").aDescricao("Ralf Drehmer"))
                .contemIntegerExato(noCampo("idade").oInteiro(29))
                .contemBoolean(noCampo("ativo").oBoolean(true))
                .contemStringExata(noObjetoDaLista("enderecos", 0, "tipo").aDescricao("residencial"))
                .contemStringExata(noObjetoDaLista("enderecos", 0, "cidade").aDescricao("Porto Alegre"))
                .contemStringExata(noObjetoDaLista("enderecos", 0, "estado").aDescricao("RS"))
                .contemStringExata(noObjetoDaLista("enderecos", 1, "tipo").aDescricao("comercial"))
                .contemStringExata(noObjetoDaLista("enderecos", 1, "cidade").aDescricao("São Paulo"))
                .contemStringExata(noObjetoDaLista("enderecos", 1, "estado").aDescricao("SP"))
                .contemBoolean(noObjeto("preferencias", "newsletter").oBoolean(false))
                .contemStringExata(noObjetoDaLista("preferencias", 0, "notificacoes").aDescricao("email"))
                .contemStringExata(noObjetoDaLista("preferencias", 1, "notificacoes").aDescricao("sms"))
                .contemFloatExato(naLista("pontuacoes", 0).oFloat(10.5F))
                .contemFloatExato(naLista("pontuacoes", 1).oFloat(9.8F))
                .contemFloatExato(naLista("pontuacoes", 2).oFloat(7.0F))
                .contemValorNulo(noCampo("ultimosAcessos"));
    }

    @Test
    void shouldGetHisThing() {
        stubFor(get(urlMatching(WHATEVER_URL))
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

        realizandoGet(paraPath(WHATEVER_URL)
                .comEntrada("whatever"))
                .comStatus(200)
                .contem("preferencias.notificacoes[0]", Matchers.equalTo("email"))
                .contemNaString(noCampo("nome").aDescricao("Ralf Drehmer"))
                .contemIntegerExato(noCampo("idade").oInteiro(29))
                .contemBoolean(noCampo("ativo").oBoolean(true))
                .contemStringExata(noObjetoDaLista("enderecos", 0, "tipo").aDescricao("residencial"))
                .contemStringExata(noObjetoDaLista("enderecos", 0, "cidade").aDescricao("Porto Alegre"))
                .contemStringExata(noObjetoDaLista("enderecos", 0, "estado").aDescricao("RS"))
                .contemStringExata(noObjetoDaLista("enderecos", 1, "tipo").aDescricao("comercial"))
                .contemStringExata(noObjetoDaLista("enderecos", 1, "cidade").aDescricao("São Paulo"))
                .contemStringExata(noObjetoDaLista("enderecos", 1, "estado").aDescricao("SP"))
                .contemBoolean(noObjeto("preferencias", "newsletter").oBoolean(false))
                .contemStringExata(noObjetoDaLista("preferencias", 0, "notificacoes").aDescricao("email"))
                .contemStringExata(noObjetoDaLista("preferencias", 1, "notificacoes").aDescricao("sms"))
                .contemFloatExato(naLista("pontuacoes", 0).oFloat(10.5F))
                .contemFloatExato(naLista("pontuacoes", 1).oFloat(9.8F))
                .contemFloatExato(naLista("pontuacoes", 2).oFloat(7.0F))
                .contemValorNulo(noCampo("ultimosAcessos"));
    }
}
