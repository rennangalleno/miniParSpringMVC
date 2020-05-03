<%@ include file="/WEB-INF/views/cabecalho.jsp" %>
		
<div class="container">

	<div>${sucesso}</div><br>
	
	<form class="form-horizontal"  action="/minipar/pagador/consulta?${pagador.cpf},${pagador.nome}" method="GET">
		
		<div class="panel panel-default">
			<div class="panel-heading">
				<div class="clearfix">
					<h1 class="panel-title aw-titulo-panel">Consulta de pagadores</h1>
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
	
	<form action="/minipar/pagador/novo" method="get">
		<button class="btn btn-primary" type="submit">Novo</button>
	</form>
	
	<table class="table table-bordered table-striped table-houver">
		<tr>
			<th>ID</th>
			<th>CPF</th>
			<th>Nome</th>
			<th>Data Nascimento</th>
			<th>Alterar</th>
			<th>Excluir</th>
		</tr>
		<c:forEach items="${pagadores}" var="pagador">
		<tr>
			<td>${pagador.id}</td>
			<td>${pagador.cpf}</td>
			<td>${pagador.nome}</td>
			<td><fmt:formatDate value="${pagador.dataNascimento}" pattern="dd/MM/yyyy"/></td>
			<td>
				<a href="/minipar/pagador/edita?id=${pagador.id}">Alterar</a>
			</td>
			<td>
				<a href="/minipar/pagador/deleta?id=${pagador.id}">Excluir</a>
			</td>
		</tr>
		</c:forEach>			
	</table>

</div>


<%@ include file="/WEB-INF/views/rodape.jsp" %>