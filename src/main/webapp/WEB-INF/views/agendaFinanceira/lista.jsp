<%@ include file="/WEB-INF/views/cabecalho.jsp" %>
		
<div class="container">
	<div>${sucesso}</div><br>
	
	<form:form class="form-horizontal" action="/minipar/agenda/consulta?${tipo.id},${bandeira.id},${pagador.id}" method="GET" modelAttribute="cliente">
		
		<div class="panel panel-default">
		
			<div class="panel-heading">
				<div class="clearfix">
					<h1 class="panel-title aw-titulo-panel">Agenda Financeira </h1>
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
					
					<label class="col-sm-1 control-label">Pagador:</label>
					<div class="col-sm-3">
						<select class="form-control" name="pagadorId">
							<option value="">Selecione</option>
							<c:forEach items="${pagadores}" var="pagador">
							   <option value="${pagador.id}">${pagador.nome}</option>							
							</c:forEach>		
						</select>	
					</div>						
										
				</div>
							
				<div class="form-group">
										
					<label class="col-sm-1 control-label">Tipo de Recebivel:</label>
					<div class="col-sm-3">
						<select class="form-control" name="tipoId">
							<option value="">Selecione</option>
							<c:forEach items="${tipos}" var="tipo">
							   <option value="${tipo.id}">${tipo.descTipoRecebivel}</option>							
							</c:forEach>		
						</select>	
					</div>
									
					<label class="col-sm-1 control-label">Bandeira:</label>
					<div class="col-sm-3">
						<select class="form-control" name="bandeiraId">
							<option value="">Selecione</option>
							<c:forEach items="${bandeiras}" var="bandeira">
							   <option value="${bandeira.id}">${bandeira.nomeBandeira}</option>							
							</c:forEach>		
						</select>	
					</div>
					
					<div><button type="submit" class="btn btn-primary">Pesquisar</button></div>		
					
				</div>
				
				<!-- 	
				<div class="form-group">	
					 					
					<label class="col-sm-1 control-label">Data Inicial:</label>
					<div class="col-sm-3"><input class="form-control" type="date" name="dataInicial"/></div>
					
					<label class="col-sm-1 control-label">Data Final:</label>
					<div class="col-sm-3"><input class="form-control" type="date" name="dataFinal"/></div>
				</div>
				-->
														
			</div>			
		</div>		
	</form:form>
	
	<form action="/minipar/agenda/antecipa?${boleto.id},${cheque.id},${cartao.id}" method="POST">
	
	<table class="table table-bordered table-striped table-houver">
		<tr>
			<th></th>
			<th>Número</th>	
			<th>Tipo Recebível</th>		
			<th>Pagador</th>
			<th>Valor Nominal</th>
			<th>Data Criação</th>			
			<th>Data Vencimento</th>			
			<th>Situação</th>
		</tr>
		
		<c:forEach items="${boletos}" var="boleto">
			<tr>
				<td><input type="checkbox" name="boletoId" value="${boleto.id}"/></td>
				<td>${boleto.id}</td>
				<td>${boleto.tipoRecebivel.descTipoRecebivel}</td>
				<td>${boleto.pagador.nome}</td>
				<td>${boleto.valor}</td>
				<td><fmt:formatDate value="${boleto.dataCriacao}" pattern="dd/MM/yyyy"/></td>
				<td><fmt:formatDate value="${boleto.dataVencimento}" pattern="dd/MM/yyyy"/></td>
				<td>${boleto.situacaoRecebivel.desSituacaoRecebivel}</td>		
			</tr>
		</c:forEach>
		
		<c:forEach items="${cheques}" var="cheque">
			<tr>
				<td><input type="checkbox" name="chequeId" value="${cheque.id}"/></td>
				<td>${cheque.id}</td>
				<td>${cheque.tipoRecebivel.descTipoRecebivel}</td>
				<td>${cheque.pagador.nome}</td>
				<td>${cheque.valor}</td>
				<td><fmt:formatDate value="${cheque.dataCriacao}" pattern="dd/MM/yyyy"/></td>
				<td><fmt:formatDate value="${cheque.dataVencimento}" pattern="dd/MM/yyyy"/></td>				
				<td>${cheque.situacaoRecebivel.desSituacaoRecebivel}</td>
			</tr>
		</c:forEach>
		
		<c:forEach items="${cartoes}" var="cartao">
			<tr>
				<td><input type="checkbox" name="cartaoId" value="${cartao.id}"/></td>
				<td>${cartao.id}</td>
				<td>${cartao.tipoRecebivel.descTipoRecebivel}</td>
				<td>${cartao.bandeira.nomeBandeira}</td>
				<td>${cartao.valor}</td>
				<td><fmt:formatDate value="${cartao.dataCriacao}" pattern="dd/MM/yyyy"/></td>
				<td><fmt:formatDate value="${cartao.dataVencimento}" pattern="dd/MM/yyyy"/></td>
				<td>${cartao.situacaoRecebivel.desSituacaoRecebivel}</td>
			</tr>
		</c:forEach>				
	</table>
	
	<button class="btn btn-primary" type="submit">Antecipar</button>
	
	</form>
</div>


<%@ include file="/WEB-INF/views/rodape.jsp" %>