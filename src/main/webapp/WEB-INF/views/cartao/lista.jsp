<%@ include file="/WEB-INF/views/cabecalho.jsp" %>
		
<div class="container">
	<div>${sucesso}</div><br>
	
	<form class="form-horizontal"  action="/minipar/cartao/consulta?${cliente.id}, ${bandeira.id}" method="GET">
		
		<div class="panel panel-default">
		
			<div class="panel-heading">
				<div class="clearfix">
					<h1 class="panel-title aw-titulo-panel">Consulta de Cartões</h1>
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
					
					<label class="col-sm-1 control-label">Bandeira:</label>
					<div class="col-sm-4">
						<select class="form-control" name="bandeiraId">
							<option value="">Selecione</option>
							<c:forEach items="${bandeiras}" var="bandeira">
							   <option value="${bandeira.id}">${bandeira.nomeBandeira}</option>							
							</c:forEach>		
						</select>	
					</div>
					<button type="submit" class="btn btn-primary">Pesquisar</button>		
					
				</div>	
					
			</div>
			
		</div>		
	</form>
	
	<form action="/minipar/cartao/novo" method="get">
		<button class="btn btn-primary" type="submit">Novo</button>
	</form>
	
	<table class="table table-bordered table-striped table-houver">
		<tr>
			<th>ID</th>
			<th>Bandeira</th>
			<th>CPF</th>
			<th>Cliente</th>
			<th>Data Criação</th>
			<th>Data Vencimento</th>
			<th>Valor Nominal</th>
			<th>Alterar</th>
			<th>Excluir</th>
		</tr>
		<c:forEach items="${cartoes}" var="cartao">
		<tr>
			<td>${cartao.id}</td>
			<td>${cartao.bandeira.nomeBandeira}</td>
			<td>${cartao.cliente.cpf}</td>
			<td>${cartao.cliente.nome}</td>
			<td><fmt:formatDate value="${cartao.dataCriacao}" pattern="dd/MM/yyyy"/></td>
			<td><fmt:formatDate value="${cartao.dataVencimento}" pattern="dd/MM/yyyy"/></td>
			<td>${cartao.valor}</td>
			<td>
				<a href="/minipar/cartao/edita?id=${cartao.id}">Alterar</a>
			</td>
			<td>
				<a href="/minipar/cartao/deleta?id=${cartao.id}">Excluir</a>
			</td>
		</tr>
		</c:forEach>			
	</table>

</div>


<%@ include file="/WEB-INF/views/rodape.jsp" %>