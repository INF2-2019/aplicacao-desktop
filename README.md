# Apicação Desktop

Esse repositório é dedicado a parte de `front-end` desktop da aplicação a ser der desenvolvida.

## Aviso

Esse repositório tem afazeres globais, caso queira ajudar verifique o [`TO-DO`](TO-DO.md).

## Instruções para IDEs e Editores recomendados

### Ferramentas necessárias

- `Oracle JDK 11+` ou `OpenJDK 11+` [recomendado]
- `OpenJFX 11+`, 13 recomendado
- Uma IDE recomendada

Download `OpenJFX 11+`: [aqui](https://gluonhq.com/products/javafx/)
Instruções para instalação: [aqui](https://duckduckgo.com/)

### IDE's recomendadas

- NetBeans 8.2+
- IntelliJ IDEA

### Plugins e Configurações

Recomendamos, para sua facilidade, instalar o plugin `EditorConfig` que ira padronizar o seu projeto "automaticamente" baseado no arquivo `.editorconfig` em `app`. [Tutorial para instalação no NetBeans](https://inf2-2019.github.io/help/editorconfig/).

Serão usados:

- Indentação por `TAB`
- Charset `UTF-8`
- Fim de linha `LF`
- Uma linha em branco no fim de cada arquivo
- Remoção automática de espaços no fim da linha

#### `.editorconfig`

```ini
root = true

[*]
indent_style = tab
charset = utf-8
trim_trailing_whitespace = true
end_of_line = lf
insert_final_newline = true
```

## Como _buildar_ o projeto

Utilizar a ferramenta de _build_ padrão da IDE utilizada. Mais instruções serão adicionadas futuramente.

## Sobre os bancos de dados

Os bancos de dados seguem um padrão semelhante ao documento do projeto, futuramente serão adicionados os `dumps` e um esquema

## Bibliotecas

As bibliotecas usadas atualmente são:

- `OpenJFX`

## Documentações e links úteis

A documentação estará disponível na pasta `docs`.

`OpenJFX 13`: [aqui](https://openjfx.io/javadoc/13/)
`OpenJFX 11`: [aqui](https://openjfx.io/javadoc/11/)
