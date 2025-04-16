package dsl;

import io.restassured.response.Response;
import lombok.RequiredArgsConstructor;
import org.hamcrest.Matcher;

import static org.hamcrest.Matchers.*;

@RequiredArgsConstructor
public class AssercoesRequisicao {
    private final Response resposta;

    public AssercoesRequisicao comStatus(final Integer status) {
        resposta.then().statusCode(status);
        return this;
    }

    public AssercoesRequisicao contemStringExata(final AssertBodyBuilder bodyAssert) {
        resposta.then().body(bodyAssert.getTag(), equalTo(bodyAssert.getCampoString()));
        return this;
    }

    public AssercoesRequisicao contemIntegerExato(final AssertBodyBuilder bodyAssert) {
        resposta.then().body(bodyAssert.getTag(), equalTo(bodyAssert.getCampoInteiro()));
        return this;
    }

    public AssercoesRequisicao contemDoubleExato(final AssertBodyBuilder bodyAssert) {
        resposta.then().body(bodyAssert.getTag(), equalTo(bodyAssert.getCampoDouble()));
        return this;
    }

    public AssercoesRequisicao contemLongExato(final AssertBodyBuilder bodyAssert) {
        resposta.then().body(bodyAssert.getTag(), equalTo(bodyAssert.getCampoLong()));
        return this;
    }

    public AssercoesRequisicao contemFloatExato(final AssertBodyBuilder bodyAssert) {
        resposta.then().body(bodyAssert.getTag(), equalTo(bodyAssert.getCampoFloat()));
        return this;
    }

    public AssercoesRequisicao contemBoolean(final AssertBodyBuilder bodyAssert) {
        resposta.then().body(bodyAssert.getTag(), equalTo(bodyAssert.getCampoBoolean()));
        return this;
    }

    public AssercoesRequisicao contemNaString(final AssertBodyBuilder bodyAssert) {
        resposta.then().body(bodyAssert.getTag(), containsString(bodyAssert.getCampoString()));
        return this;
    }

    public AssercoesRequisicao contemValorNulo(final AssertBodyBuilder bodyAssert) {
        resposta.then().body(bodyAssert.getTag(), nullValue());
        return this;
    }

    public AssercoesRequisicao contem(final String tag, final Matcher<?> matchers) {
        resposta.then().body(tag, matchers);
        return this;
    }

    public <T> T extrairSaida(Class<T> classe) {
        return resposta.as(classe);
    }
}
