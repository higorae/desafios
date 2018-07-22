

# Desafio 2: Crawlers

##### Processo de resolução do desafio parte 01

Decidi pelo uso do spring como framework por ser uma ferramente que uso no meu dia-a-dia e pelas facilidades oferecidas por ela como, por exemplo, controle de depencências, autoconfigução, etc. 

Comecei o desenvolvimento pelo mapeamento da classe de dominio referente ao que deve ser exibido para o usuário.

Criei a classe `CrawlerConfig`, que ficou responsável pela configuração do selenium e do CLI utilizado na parte 01 do desafio. O CLI ele só será utilizado caso nenhuma outro CLI tenha sido configurado.

Após a criação dessa classe, criei a classe `SubredditContentReader`, que ficou responsável pela leitura dos posts de um dado subreddit, decidi utilizar um `consumer` e as facilidades que a lambda expression oferece para definir como os posts capturados e que obedecessem a um dado critério, no caso mais de 5mil upvotes, deveriam ser consumidos, ou seja, se ele seria impresso no console ou seria enviado de volta a um usuário do telegram, por exemplo.

 Na concepção da classe `SubredditContentReader`, decidi que o método read deveria lançar uma checked exception `EmptySubredditResponseException`, para os cenários onde não existisse nenhum conteúdo de um determinado subreddit ou até mesmo a subreddit em si.
 
 Outra decisão tomada foi a criação de um `CrawlerMapper`, que ficou responsável por fazer o mapeamento dos elementos web retornados pelo selenium tirando essa responsabilidade do `SubredditContentReader`.

##### Processo de resolução do desafio parte 02

A parte 02 começou com um estudo relacionado ao funcionamento da API de criação de bots do [telegram](https://core.telegram.org/bots).

Para a parte 02 foi criada uma classe de configuração específico para o bot do telegram (`TelegramBotCommandLineConfig`). Essa classe só será ativada quando for passado o API key do bot do telegram atráves da propriedade `org.telegram.bot.key`.

Para a criação do bot foi usada a biblieteca `org.telegram.telegrambots`. Ela oferece uma classe abstrata `TelegramLongPollingBot` que usei para criar a classe concreta `RedditTelegramBot`, que ficou responsável por manipular os comandos enviados pelo bot via telegram. 

## Execução

#### Parte 01 e 02

**Importante!** Para que as aplição funcione corretamente é necessário inicializar o selenium remote driver. Foi disponibilizado um arquivo docker-compose na pasta raiz do projeto crawlers. Para executa-lo é necessário usar o seguinte comando:

`docker-compose up -d`


### Parte 1

##### Para executar

`mvn spring-boot:run -Dspring-boot.run.arguments="[subreddits separados por ponto-e-virugula (ex: cats;dogs)]"`

Exemplo:

`mvn spring-boot:run -Dspring-boot.run.arguments="cats;dogs"`

### Parte 2

##### Para executar

`mvn spring-boot:run  -Dorg.telegram.bot.key=[TELEGRAM BOT API KEY]`

Em seguida interagir com o bot no telegram através do comando /nadaparafazer.