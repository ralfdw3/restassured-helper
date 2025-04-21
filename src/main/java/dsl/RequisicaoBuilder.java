package dsl;

import java.util.Map;

public class RequisicaoBuilder {
    private final String path;
    private final Map<String, String> headers;

    public RequisicaoBuilder(final String path, final Map<String, String> headers) {
        this.path = path;
        this.headers = headers;
    }

    public static RequisicaoBuilder paraPath(final String path) {
        return new RequisicaoBuilder(path, null);
    }

    public static RequisicaoBuilder comHeaders(final Map<String, String> headers) {
        return new RequisicaoBuilder(null, headers);
    }

    public Requisicao comEntrada(final Object body) {
        return new Requisicao(this.path, body, this.headers);
    }
}