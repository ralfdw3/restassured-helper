package dsl;

import lombok.Builder;

import java.util.Map;

@Builder
public record Requisicao(String path, Object body, Map<String, String> headers) {
}
