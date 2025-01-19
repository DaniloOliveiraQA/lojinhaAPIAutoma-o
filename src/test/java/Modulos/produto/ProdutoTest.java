package Modulos.produto;

import groovyjarjarantlr.Token;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.Usuariopojo;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

@DisplayName("Testes de API Rest do modulo de Produto")
public class ProdutoTest {
    private String token;

    @BeforeEach
    public void beforeEache() {
        // Configurando os dados da API Rest da lojinha
        baseURI = "http://165.227.93.41";
        basePath = "/lojinha-bugada";

        Usuariopojo usuario = new Usuariopojo();
        usuario.setUuarioLogin("admin");
        usuario.setUsuarioSenha("admin");

        // Obter o token do usuario admin
        this.token = given()
                .contentType(ContentType.JSON)
                .body(usuario)
            .when()
                .post("/v2/login")
            .then()
                .extract()
                    .path("data.token");
    }

    @Test
    @DisplayName("Validar que o valor do produto igual a 0.00 nao e permitido")
    public void testValidarLimitesZeradoProibidoValorProduto() {


       // Tentar inserir o produto com o valor 0.00 e validar que a mensagem de error foi validado
       // status code retornado foi 422

        given()
                .contentType(ContentType.JSON)
                .header("token", this.token)
                .body("{\n" +
                "  \"produtoNome\": \"Playstation 5\",\n" +
                "  \"produtoValor\": 0.00,\n" +
                "  \"produtoCores\": [\n" +
                "    \"preto\"\n" +
                "  ],\n" +
                "  \"produtoUrlMock\": \"\",\n" +
                "  \"componentes\": [\n" +
                "    {\n" +
                "      \"componenteNome\": \"Controle\",\n" +
                "      \"componenteQuantidade\": 1\n" +
                "    }\n" +
                "  ]\n" +
                "}")
    .when()
            .post("/v2/produtos\n")
    .then()
            .assertThat()
                .body("error", equalTo("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00"))
                .statusCode(422);
    }

    @Test
    @DisplayName("Validar que o valor do produto igual a 7000.01 nao e permitido")
    public void testValidarLimitesMaiorSeteMilProibidoValorProduto() {

        // Tentar inserir o produto com o valor 0.00 e validar que a mensagem de error foi validado
        // status code retornado foi 422

        given()
                .contentType(ContentType.JSON)
                .header("token", this.token)
                .body("{\n" +
                        "  \"produtoNome\": \"Playstation 5\",\n" +
                        "  \"produtoValor\": 7000.00,\n" +
                        "  \"produtoCores\": [\n" +
                        "    \"preto\"\n" +
                        "  ],\n" +
                        "  \"produtoUrlMock\": \"\",\n" +
                        "  \"componentes\": [\n" +
                        "    {\n" +
                        "      \"componenteNome\": \"Controle\",\n" +
                        "      \"componenteQuantidade\": 1\n" +
                        "    }\n" +
                        "  ]\n" +
                        "}")
            .when()
                .post("/v2/produtos\n")
            .then()
                .assertThat()
            .body("error", equalTo("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00"))
                .statusCode(422);
    }

}
