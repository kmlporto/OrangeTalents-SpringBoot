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
  
