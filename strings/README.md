# Desafio 1: String Formatter

##### Processo de resolução do desafio

Foram criadas duas classes para representar o texto (Text) e as linhas que compõem esse texto (LineText).

Inicialmente criei a classe IdwallFormatter. Essa classe recebe um determinado texto e, baseado no limite de caracteres por linha, ela retorna o texto em um determinado formato. A lógica usada no algoritmo foi a de "explodir" o texto em um array de palavras e ir adicionando essas palavras até atingir o limite de caracteres naquela linha. Caso a palavra a ser adicionada em seguida faça com que o total de caracteres na linha passe do seu limite, essa palavra é adicionada em uma nova linha e, se ainda existirem palavras, elas são adicionadas seguindo o mesmo fluxo descrito anteriormente.

Para formatar o texto resolvi usar o padrão de projeto Strategy, onde foi definido dois padrões possíveis: DefaultAlignment e o JustifyAlignment. O primeiro apenas remove os espaços em branco do texto. Já o segundo justifica o texto adicionado espaços em branco entre as palavras até que chegue ao limite de caracteres por linha.

## Execução

##### Para executar

Deve-se primeiramente criar o JAR-file usando o comando abaixo:

`mvn package`

Em seguida deve-se executar o seguinte comando:

`java -jar target/StringFormatter-1.0-SNAPSHOT.jar "[TEXTO PARA SER FORMATADO]" [NÚMERO CARACTERES POR LINHA] [true ou false Caso queira justificar ou não o texto]`
 