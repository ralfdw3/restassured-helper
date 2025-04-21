package dsl;

import lombok.Getter;

@Getter
public class AssertBodyBuilder {
    private final String campo;
    private final Integer posicao;
    private final String tag;
    private final String campoFilho;

    private String campoString;
    private Integer campoInteiro;
    private Long campoLong;
    private Double campoDouble;
    private Float campoFloat;
    private Boolean campoBoolean;

    public AssertBodyBuilder(final String campo, final Integer posicao, final String campoFilho, final String tag) {
        this.campo = campo;
        this.posicao = posicao;
        this.campoFilho = campoFilho;
        this.tag = tag;
    }

    public static AssertBodyBuilder noCampo(final String campo) {
        return new AssertBodyBuilder(campo, null, null, campo);
    }

    public static AssertBodyBuilder noObjeto(final String campo, final String campoFilho) {
        final String tag = campo + "." + campoFilho;
        return new AssertBodyBuilder(campo, null, campoFilho, tag);
    }

    public static AssertBodyBuilder naLista(final String lista, final Integer posicao) {
        final String tag = lista + "[" + posicao + "]";
        return new AssertBodyBuilder(lista, posicao, null, tag);
    }

    public static AssertBodyBuilder noObjetoDaLista(final String lista, final Integer posicao, final String campoFilho) {
        final String tag = lista + "." + campoFilho + "[" + posicao + "]";
        return new AssertBodyBuilder(lista, posicao, campoFilho, tag);
    }

    public AssertBodyBuilder aDescricao(final String campoString) {
        this.campoString = campoString;
        return this;
    }

    public AssertBodyBuilder oInteiro(final Integer campoInteiro) {
        this.campoInteiro = campoInteiro;
        return this;
    }

    public AssertBodyBuilder oLong(final Long campoLong) {
        this.campoLong = campoLong;
        return this;
    }

    public AssertBodyBuilder oDouble(final Double campoDouble) {
        this.campoDouble = campoDouble;
        return this;
    }

    public AssertBodyBuilder oFloat(final Float campoFloat) {
        this.campoFloat = campoFloat;
        return this;
    }

    public AssertBodyBuilder oBoolean(final Boolean campoBoolean) {
        this.campoBoolean = campoBoolean;
        return this;
    }
}
