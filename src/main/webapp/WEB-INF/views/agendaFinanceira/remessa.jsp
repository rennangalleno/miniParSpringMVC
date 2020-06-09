<%@ include file="/WEB-INF/views/cabecalho.jsp" %>
		
<div class="container">
	<div>${sucesso}</div><br>
			
		<div class="panel panel-default">
		
			<div class="panel-heading">
				<div class="clearfix">
					<h1 class="panel-title aw-titulo-panel">Cliente</h1>
				</div>
			</div>
			
			<div class="panel-body">
			
				<div class="form-group">
				
					<label class="col-sm-1 control-label">CPF:</label>
					<div class="col-sm-3">
						<form:input disabled="true" class="form-control" path="cpf"/>
						<form:hidden path="id" value="${cliente.id}"/>
					</div>
					
					<label class="col-sm-1 control-label">Nome:</label>
					<div class="col-sm-3">
						<form:input disabled="true" class="form-control" path="nome"/>
					</div>													
				</div>
					
			</div>
<!--  			
			<div class="panel-heading">
				<div class="clearfix">
					<label class="col-sm-1 control-label">Remessa:</label>
					<label class="col-sm-1 control-label">${remessa.id}:</label>
					<label class="col-sm-1 control-label">Data Criação:</label>
					<label class="col-sm-1 control-label">${remessa.dataCriacao}</label>
					<label class="col-sm-1 control-label">Valor:</label>
					<label class="col-sm-1 control-label">${remessa.valor}</label>
				</div>
								
			</div>
			
			<div class="form-group">	
				<table class="table table-bordered table-houver">
					<c:forEach items="${remessa.lotes}" var="lote">
						<tr>
							<th>Lote</th>
							<th>${lote.id}</th>	
							<th>Valor</th>		
							<th>${lote.valor}</th>
						</tr>
						
						<c:forEach items="${lote.recebiveis}" var="recebivel">
							<tr>
								<td>${recebivel.id}</td>
								<td>${recebivel.tipoRecebivel.descTipoRecebivel}</td>
								<td>${recebivel.valor}</td>
								<td><fmt:formatDate value="${recebivel.dataCriacao}" pattern="dd/MM/yyyy"/></td>
								<td><fmt:formatDate value="${recebivel.dataVencimento}" pattern="dd/MM/yyyy"/></td>
								<td>${recebivel.situacaoRecebivel.desSituacaoRecebivel}</td>		
							</tr>
						</c:forEach>
					</c:forEach>			
				</table>													
			</div>				
				

		<button class="btn btn-primary" type="submit">Criar Remessa</button>
-->	
</div>
</div>


<%@ include file="/WEB-INF/views/rodape.jsp" %>