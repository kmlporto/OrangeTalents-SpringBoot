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
