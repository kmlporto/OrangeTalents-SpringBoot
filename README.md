# OrangeTalents-SpringBoot

### Módulo 1 - Introdução ao String Boot
* para facilitar a inicialização de um projeto Spring pode-se utilizar o initializr https://start.spring.io/
* agora podemos criar um controller, anotamos com @Controller;
* podemos mapear um controller com o @RequestMappin;

### Módulo 2 - Publicando endpoints
* @ResponseBody - indica que o retorno do método deve ser serializado e devolvido no corpo da resposta;
* @RestController - indica que é um controller rest, dessa forma não precisamos usar o @ResponseBody em todos os métodos;
* para facilitar nossa vida podemos adicionar o módulo do Spring Boot, o DevTools, uma dos benefícios ao usá-lo é não precisar ficar reiniciando a aplicação toda vez que é feita uma alteração no código;
* Data Transfer Object(DTO) - Objetos usados para transferência de dados, muito útil quando não queremos exibir todos os dados de uma entidade, ou quando queremos exibir dados a mais;
* agora sempre que vamos devolver ou receber dados para o usuário vamos fazer usos de dto's;

### Módulo 3 - Usando Spring Data
* para usar spring data precisamos realizar alguns passos:
  * adicionar a dependência no pom.xml, depois configurar o datasource, jpa e o banco que vamos utilizar no arquivo application.yml;
  * transformar as classes de domínio em entidades reconhecidas pelo jpa; 
  * criar repositórios para assim conseguir acessar dados mais fácil que usando EntityManager;
* podemos criar um arquivo para população de dados no banco, chamado data.sql;
* para criar um repositório devemos criar uma interface, que herda da interface JPARepository do Spring Data JPA;
* existem formas de gerar consultas nos repositórios:
  * usando padrão spring data - deve seguir o padrão de nomenclatura do framework;
  * usando jpql - deve fazer uso da anotação @Query e @Param para passar os parâmetros;

### Módulo 4 - Trabalhando com POST
* agora que tem dois tipos de verbo para a rota de tópicos, vamos usar:
  * usar o @RequestMapping na classe de controller;
  * usar o @PostMapping e @GetMapping nos métodos;
* para receber parâmetros enviados via body de uma requisição post, precisamos usar a anotação @RequestBody;
* quando criamos um objeto no banco de dados é interessante que o retorno da requisição seja o status CREATED * 201;
  * dessa forma precisamos alterar o retorno da requisição, pois o status default é OK - 200;
  * uma forma de fazer isso é através do objeto ResponseEntity;
  
### Módulo 5 - Validação com Bean Validation
* para usar o validation é necessário adicionar este módulo no arquivo pom.xml;
  * artifacId = spring-boot-starter-validation;
* então podemos fazer uso de algumas anotações para validar os dados, como por exemplo @NotNull, @NotEmpty, @Size;
  * para essas validações refletirem no dto passado por parâmetro da requisição, é necessário usar o @Valid;
* quando uma requisição não é válida, a resposta devolvida para o cliente é bem extensa, então podemos fazer um tratamento dos campos que possuem erro;
  * dessa forma criamos uma classe e anotamos com @RestControllerAdvice - componente especializado em tratar exceções;
  * o tipo de exceção que queremos tratar é a MethodArgumentNotValidException, que é a exceção lançada quando alguma validação de um argumento anotado com @Valid falha;
  * então no método que irá tratar a exceção precisamos indicar isso através de @ExceptionHandler e para manter o status como BadRequest, usamos o @ResponseStatus(code=HttpStatus.BAD_REQUEST);
  
### Módulo 6 - Métodos PUT, DELETE e tratamento de erros
* primeiro método a ser criado deve ser o consultar, outro GET, então usamos @GetMapping("/{id}"), para distinguir do outro get que já existe e para receber via uri o id do tópico que queremos consultar;
  * a forma de resgatar esse id passado via uri é usando @PathVariable;
* o put é um verbo bastante usado para alteração de dados, porém não existe apenas este. Existe também o PATCH;
* o delete é um verbo usado para remover um registro do banco;

# OrangeTalents-SpringBoot - Segurança da API, Cache e Monitoramento

### Módulo 1 - Paginação e ordenação de recursos
* quando o devolvemos uma lista de todos os elementos existentes no banco pode causar problemas quando existir muitos dados cadastrados;
* a forma de resolver esse problema é usando a paginação, onde escolhemos quantos elementos vamos devolver por vez e qual página queremos pegar;
* além de devolver os dados pela página, é devolvido o total de páginas, total de elementos, entre outras informações;
* é possível também, ordenar os dados de acordo com um de seus atributos;
* para nos ajudar com a paginação usamos o objeto Page e interface Pageable;
* através do @PageableDefault podemos deixar uma configuração de paginação default, para quando não for passado nada na requisição;

### Módulo 2 - Melhorando desempenho com Spring Cache
* para melhorar a performance da aplicação podemos fazer uso de caches;
* adicionar no pom o módulo do spring boot de cache: o spring-boot-starter-cache;
* caso nossa api fosse para produção seria necessário utilizar uma ferramenta/provedor de cache, como exemplo o redis, mas como estamos apenas estudando não vamos fazer isso. Dessa forma utilizaremos o cache em memória que é utilizado pelo spring quando não configuramos nenhum;
* para habilitar o cache precisamos também utilizar @EnableCaching na classe de aplicação;
* nos métodos que altera nossos dados de alguma forma, seja excluindo ou alterando, é necessário colocar @CacheEvict, passando qual o cache que devo invalidar, senão fica dados inconsistentes na listagem;
* cache deve ser usado em tabelas que raramente são atualizadas, pois também temos um custo para gerencia-los;

### Módulo 3 - Proteção com Srping Security
* para restringir quem poderá consumir os endpoints vamos fazer uso de autenticação;
* então adicionamos no pom o módulo do sprint boot de segurança: o spring-boot-starter-security;
* para habilitar e configurar o controle de autenticação e autorização do projeto, devemos criar uma classe para e anotar o @Configuration e @EnableWebSecurity, e então adicionamos a herança da classe abstrata WebSecurityConfigurerAdapter para poder sobrescrever alguns destes métodos;
* existem três métodos configure() que podemos sobrescrever, uma delas é para configuração de arquivos estáticos, outra para autorizações, e outra para autenticação;
* para liberar acesso a algum endpoint da nossa api, devemos chamar o método http.authorizeRequests().antMatchers().permitAll() dentro do método configure(HttpSecurity http) que está na classe
* o método anyRequest().authenticated() indica ao Spring Security para bloquear todos os endpoints que não foram liberados anteriormente com o método permitAll();
* para implementar o controle de autenticação na api, devemos implementar a interface UserDetails na classe de Usuario e também implementar a interface GrantedAuthority na classe Perfil;
  * na classe Usuario devemos adicionar o atributo List<Perfil> com relacionamento ManyToMany;
* para o spring security gerar automaticamente um formulário de login, devemos chamar o método and().formLogin(), dentro do método configure(HttpSecurity http), que está na classe SecurityConfigurations;
* devemos criar uma classe de serviço responsável por autenticar o usuário, nela devemos implementar a interface UserDetailsService e então sobrescrever o método loadUserByUsername;
  * nesse método devemos acessar o repositório de usuário para resgata-lo;
* UserDetailsService - indica ao Spring Security que essa é a classe service que executa a lógica de autenticação;
* devemos indicar ao spring security qual o algoritmo de hashing de senha que utilizaremos na api, chamando o método passwordEncoder(), dentro do método configure(AuthenticationManagerBuilder auth), que está na classe de configurações de autenticação e autorização;

### Módulo 4 - Gerando token com JWT
* a autenticação via login não é uma boa prática para api rest, pois não é stateless;
* então vamos realizar a autenticação via tokens, existe uma biblioteca java que segue o modelo do JSON web token, chamada jjwt;
* para indicar que não vamos mais usar a autenticação via formulário, removemos a linha and().formLogin() e para configurar autenticação stateless adicionamos na classe de configuração de segurança sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
* desabilitamos também a validação csrf (cross-site request forgery): .and().csrf().disable();
* é necessário criar uma classe que cuide da autenticação AutenticacaoController que será um controller e terá a rota "/auth";
  * criamos um método autenticar(), que receberá uma requisição do tipo Post, nesse método recebemos um objeto LoginForm, que terá dados de senha e email do usuário;
  * esse método irá chamar o método authenticate para uma instância da classe AuthenticationManager;
* para injetar o AuthenticationManager no controller, devemos criar um método anotado com @Bean, na classe SecurityConfigutarions, que retorna uma chamada ao método super.athenticationManager();
* para criar o token JWT devemos utilizar a classe Jwts;
* ao criar o token foi enviado juntamente outra informação chamada Bearer, que é um dos mecanismos de autenticação utilizados no protocolo HTTP, tal como o Basic e o Digest;

### Módulo 5 - Autenticação via JWT
* para ser realizado uma autenticação as requisições dos endpoints criados na api é necessário adicionar um filtro, para que passe por o trecho do código que autentique, antes de executar tudo o que é feito em cada método do endpoint;
  * então criamos uma classe  AutenticacaoViaTokenFilter que vai herdar da classe abstrata OncePerRequestFilter, e então sobrescrevemos o método doFilterInternal(), dai conseguimos acessar a requisição e então recuperar o token passado;
  * para registrar o filtro é necessário adicionar na classe SecurityConfigurations a linha addFilterBefore(new AutenticacaoViaTokenFilter(), UsernamePasswordAuthenticationFilter.class);
  * para validar o token precisamos ter acesso à classe TokenService, então precisamos injetar essa dependência, porém, não é possível via autowired pois a injeção desta classe AutenticacaoViaTokenFilter foi feita manualmente por nós via construtor, então será necessário usar o construtor para injetar outras classes na mesma;
  * após validar o token é necessário validar o cliente via SecurityContextHolder;
  
### Módulo 6 - Monitoramento com Srping Boot Actuator
* para adicionar o Spring Boot Actuator no projeto, devemos adicioná-lo como uma dependência no arquivo pom.xml;
* para acessar as informações disponibilizadas pelo Actuator, devemos entrar no endereço http://localhost:8080/actuator;
* para liberar acesso ao Actuator no Spring Security, devemos chamar o método .antMatchers(HttpMethod.GET, "/actuator/**");
* para que o Actuator exponha mais informações sobre a API, devemos adicionar as propriedades management.endpoint.health.show-details=always e management.endpoints.web.exposure.include=* no arquivo application.properties;
* para utilizar o Spring Boot Admin, devemos criar um projeto Spring Boot e adicionar nele os módulos spring-boot-starter-web e spring-boot-admin-server;
* para trocar a porta na qual o servidor do Spring Boot Admin rodará, devemos adicionar a propriedade server.port=8081 no arquivo application.properties;
* para o Spring Boot Admin conseguir monitorar a nossa API, devemos adicionar no projeto da API o módulo spring-boot-admin-client e também adicionar a propriedade spring.boot.admin.client.url=http://localhost:8081 no arquivo application.properties;
* para acessar a interface gráfica do Spring Boot Admin, devemos entrar no endereço http://localhost:8081;

### Módulo 7 - Documentação da API com Swagger
* para documentar a nossa API Rest, podemos utilizar o Swagger, com o módulo SpringFox Swagger;
* para utilizar o SpringFox Swagger na API, devemos adicionar suas dependências no arquivo pom.xml;
* para habilitar o Swagger na API, devemos adicionar a anotação @EnableSwagger2 na classe ForumApplication;
* as configurações do Swagger devem ser feitas criando-se uma classe chamada SwaggerConfigurations e adicionando nela a anotação @Configuration;
* para configurar quais endpoints e pacotes da API o Swagger deve gerar a documentação, devemos criar um método anotado com @Bean, que devolve um objeto do tipo Docket;
* para acessar a documentação da API, devemos entrar no endereço http://localhost:8080/swagger-ui.html;
* para liberar acesso ao Swagger no Spring Security, devemos chamar o seguinte método web.ignoring().antMatchers("/**.html", "/v2/api-docs", "/webjars/**", "/configuration/**", "/swagger-resources/**"), dentro do método void configure(WebSecurity web), que está na classe SecurityConfigurations.

# OrangeTalents-SpringBoot - Profile, Testes e Deploy

### Módulo 1 - Mais segurança
* é possível restringir o acesso a determinados endpoints da aplicação, de acordo com o perfil do usuário autenticado, utilizando o método hasRole(“NOME_DO_ROLE”) nas configurações de segurança da aplicação.

### Módulo 2 - Profiles
* profiles devem ser utilizados para separar as configurações de cada tipo de ambiente, tais como desenvolvimento, testes e produção;
* existe a anotação @Profile serve para indicar ao Spring que determinada classe deve ser carregada apenas quando determinados profiles estiverem ativos;
* é possível configurar o profile ativo da aplicação por meio do arquivo application.yml spring.profiles.active: dev;

