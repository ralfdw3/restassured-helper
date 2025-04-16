package dsl;

import java.util.Map;

public class RequisicaoBuilder {
    private final String path;

    public RequisicaoBuilder(final String path) {
        this.path = path;
    }

    public static RequisicaoBuilder paraPath(final String path) {
        return new RequisicaoBuilder(path);
    }

    public Requisicao comEntrada(final Object body) {
        return new Requisicao(path, body, null);
    }

    public Requisicao comEntradaEHeaders(final Object body, final Map<String, String> headers) {
        return new Requisicao(path, body, headers);
    }
}