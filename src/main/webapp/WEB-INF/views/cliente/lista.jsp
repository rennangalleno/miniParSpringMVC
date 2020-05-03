<%@ include file="/WEB-INF/views/cabecalho.jsp" %>
		
<div class="container">
	<div>${sucesso}</div><br>
	
	<form class="form-horizontal"  action="/minipar/cliente/consulta?${cliente.cpf}, ${cliente.nome}" method="GET">
		
		<div class="panel panel-default">
			<div class="panel-heading">
				<div class="clearfix">
					<h1 class="panel-title aw-titulo-panel">Consulta de clientes</h1>
				</div>
			</div>
			
			<div class="panel-body">
			
				<div class="form-group">	
					<label class="col-sm-1 control-label">CPF:</label>
					<div class="col-sm-3"><input type="text" class="form-control" name="cpf"/></div>
					
					<label class="col-sm-1 control-label">Nome:</label>
					<div class="col-sm-4"><input type="text" class="form-control" name="nome"/></div>
					
					<button type="submit" class="btn btn-primary">Pesquisar</button>				
				</div>			
			</div>
			
		</div>		
	</form>
	
	<form action="/minipar/cliente/novo" method="get">
		<button class="btn btn-primary" type="submit">Novo</button>
	</form>
	
	<table class="table table-bordered table-striped table-houver">
		<tr>
			<th class="id" >ID</th>
			<th>CPF</th>
			<th>Nome</th>
			<th>Data Nascimento</th>
			<!-- <th>Conta Corrente</th> -->
			<th>Alterar</th>
			<th>Excluir</th>
		</tr>
		<c:forEach items="${clientes}" var="cliente">
		<tr>
			<td>${cliente.id}</td>
			<td>${cliente.cpf}</td>
			<td>${cliente.nome}</td>
			<td><fmt:formatDate value="${cliente.dataNascimento}" pattern="dd/MM/yyyy"/></td>
			<!-- <td>${cliente.conta}</td> -->
			<td>
				<a href="/minipar/cliente/edita?id=${cliente.id}">Alterar</a>
			</td>
			<td>
				<a href="/minipar/cliente/deleta?id=${cliente.id}">Inativar</a>
			</td>
		</tr>
		</c:forEach>			
	</table>

</div>

	<script> 
		var titulo = document.querySelector(".id");
		titulo.textContent = "Aparecida Nutricionista";
	</script>

<%@ include file="/WEB-INF/views/rodape.jsp" %>