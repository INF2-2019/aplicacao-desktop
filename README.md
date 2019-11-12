# Apicação Desktop

Esse repositório é dedicado a parte de `front-end` desktop da aplicação a ser der desenvolvida.

## Aviso

Esse repositório tem afazeres globais, caso queira ajudar verifique o [`TO-DO`](TO-DO.md).

## Instruções para IDEs e Editores recomendados

### Ferramentas necessárias

- `Oracle JDK 7/8` ou `OpenJDK 7/8` [recomendado]
- Uma IDE recomendada

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

Utilizar a ferramenta de _build_ padrão da IDE utilizada.

## Bibliotecas

As bibliotecas usadas atualmente são:

- `JavaFX 8`

## Documentações e links úteis

A documentação estará disponível na pasta `docs`.

`JavaFX 8`: [aqui](https://docs.oracle.com/javase/8/javafx/api/toc.htm)

## Padronizações

- **Telas principais**: 

	- Dimensões: 1280px x 720px
	
- **Modais**:
	
	- Dimensões: Ficarão a critério dos grupos de acordo com suas respectivas necessidades, respeitando o tamanho limite de 1280px x 720px
	
- **CSS**:
	
	- Todas as fontes deverão ser Montserrat
	- Ao adicionar a referência à fonte no _início_ do arquivo: `@import url('https://fonts.googleapis.com/css?family=Montserrat&display=swap');`
	- Ao adicionar a fonte em classes, IDs e afins:`-fx-font-family: 'Montserrat', sans-serif;`

## FAQ

Se você está enfrentando o erro [desta imagem](http://prntscr.com/pvwrx3), siga os passos para solucioná-lo:

1. Encontre o caminho da sua **JRE** (padrão: `C:\Program Files\Java\jre-versão`)
	- [Neste exemplo](http://prntscr.com/pvwubc) o endereço seria `C:\Program Files\Java\jre1.8.0_111`
2. Localize e abra o arquivo app/nbproject/project.properties
3. Localize a linha onde se encontra escrito `platform.active=default_platform`, e insira uma linha logo abaixo
4. Na nova linha, escreva `platforms.default_platform.home=` + o endereço encontrado no passo 1
    - Seguindo o exemplo do passo 1 o resultado seria `platforms.default_platform.home=C:\Program Files\Java\jre1.8.0_111`
    - Se não funcionar tente inverter as contra-barras ("\\") em barras ("/")
