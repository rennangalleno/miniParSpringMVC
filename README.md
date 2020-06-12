# Resumo
Após finalizar o curso de formação Java do Alura, fiz este projeto para aplicar todos os conhecimentos adquiridos no curso. 
O projeto trata sobre um mini sistema que gerencia e antecipa recebíveis. Para tanto, foram criados as seguintes funcionalidades:
- CRUD de cliente;
- CRUD de Pagador;
- CRUD de Cartão; 
- CRUD de Boleto;
- CRUD de Cheque;
- Consulta de Recebíveis;
- Antecipação de Recebíveis e crédito na conta corrente;
- Consulta do saldo da conta corrente;

# Front
Foi utilizado para o front-end as tecnologias: HTML5, CSS3 e Bootstrap 3.3.7 nos JSP's. 
<br> Conta com elementos como: nav-bar, drop-downs, tabelas, painéis, botões e links interativos. 
<br> Uso do arquivo "pageTemplate.tag" para evitar repetição de elementos HTML nas diferentes views. 
<br> Tags do Spring e JSTL para build de url e execução de controle de fluxo dentro dos JSP's.

# Back
Projeto construído no padrão MVC com o Spring framework, Maven no controle de dependências, Hibernate como implementação do JPA, Sistema Gerenciador de Banco de Dados o MySQL8, Javax Validation para validações de dados e o Apache Tomcat 9 como service container.

# Estrutura do Projeto
No projeto a configuração da persistência de dados está na classe JPAConfigurationDEV, administrada pelo Spring e não mais num arquivo '.xml'. 
<br> A lógica de acesso aos dados está contida nos DAO's nos quais o Spring injeta o EntityManager e permite transações.
<br> As queries simples são escritas em JPQL e a mais complexa montada com a API Criteria.




