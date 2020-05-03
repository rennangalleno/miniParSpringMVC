<%@ include file="/WEB-INF/views/cabecalho.jsp" %>
		
<div class="container">
	<div>${sucesso}</div><br>
	
	<form class="form-horizontal"  action="/minipar/boleto/consulta?${cliente.id}, ${pagador.id}" method="GET">
		
		<div class="panel panel-default">
		
			<div class="panel-heading">
				<div class="clearfix">
					<h1 class="panel-title aw-titulo-panel">Consulta de Boletos</h1>
				</div>
			</div>
			
			<div class="panel-body">
			
				<div class="form-group">	
					
					<label class="col-sm-1 control-label">Cliente:</label>
					<div class="col-sm-4">
						<select class="form-control" name="clienteId">
							<option value="">Selecione</option>
							<c:forEach items="${clientes}" var="cliente">
							   <option value="${cliente.id}">${cliente.nome}</option>							
							</c:forEach>		
						</select>	
					</div>
					
					<label class="col-sm-1 control-label">Pagador:</label>
					<div class="col-sm-4">
						<select class="form-control" name="pagadorId">
							<option value="">Selecione</option>
							<c:forEach items="${pagadores}" var="pagador">
							   <option value="${pagador.id}">${pagador.nome}</option>							
							</c:forEach>		
						</select>	
					</div>
					<button type="submit" class="btn btn-primary">Pesquisar</button>		
					
				</div>	
					
			</div>
			
		</div>		
	</form>
	
	<form action="/minipar/boleto/novo" method="get">
		<button class="btn btn-primary" type="submit">Novo</button>
	</form>
	
	<table class="table table-bordered table-striped table-houver">
		<tr>
			<th>ID</th>
			<th>Pagador</th>
			<th>CPF</th>
			<th>Cliente</th>
			<th>Data Criação</th>
			<th>Data Vencimento</th>
			<th>Valor Nominal</th>
			<th>Alterar</th>
			<th>Excluir</th>
		</tr>
		<c:forEach items="${boletos}" var="boleto">
		<tr>
			<td>${boleto.id}</td>
			<td>${boleto.pagador.nome}</td>
			<td>${boleto.cliente.cpf}</td>
			<td>${boleto.cliente.nome}</td>
			<td><fmt:formatDate value="${boleto.dataCriacao}" pattern="dd/MM/yyyy"/></td>
			<td><fmt:formatDate value="${boleto.dataVencimento}" pattern="dd/MM/yyyy"/></td>
			<td>${boleto.valor}</td>
			<td>
				<a href="/minipar/boleto/edita?id=${boleto.id}">Alterar</a>
			</td>
			<td>
				<a href="/minipar/boleto/deleta?id=${boleto.id}">Excluir</a>
			</td>
		</tr>
		</c:forEach>			
	</table>

</div>


<%@ include file="/WEB-INF/views/rodape.jsp" %>