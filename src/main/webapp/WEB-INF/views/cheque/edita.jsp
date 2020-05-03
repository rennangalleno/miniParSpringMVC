<%@ include file="/WEB-INF/views/cabecalho.jsp" %>


	<form:form class="form-horizontal"  action="/minipar/cheque/edita" method="POST" modelAttribute="cheque">
		<div class="container">
				
		<div class="panel panel-default">
	
			<div class="panel-heading">
		   		<div class="clearfix">
		   			<h1 class="panel-title aw-titulo-panel">Alterar Cheque</h1>
		   		</div>
		   	</div>
		   	
			<div class="panel-body">
			
					<div class="form-group">
						<label class="col-sm-1 control-label">Id:</label>
							<div class="col-sm-1"><form:input disabled="true" class="form-control" path="id"/></div>				
							<form:hidden path="id" value="${cheque.id}"/>	
					</div>
			
					<div class="form-group">
						<label class="col-sm-1 control-label">Pagador:</label>
						<div class="col-sm-4">
							<form:select class="form-control" path="pagador.id">
								<option value="">Selecione</option>
								<c:forEach items="${pagadores}" var="pagador">
								   <option value="${pagador.id}">${pagador.nome}</option>							
								</c:forEach>		
							</form:select>		
						</div>				
						<form:errors path="pagador.id"/>	
					</div>
					
					<div class="form-group">
						<label class="col-sm-1 control-label">Cliente:</label>
						<div class="col-sm-4">
							<form:select class="form-control" path="cliente.id">
								<option value="">Selecione</option>
								<c:forEach items="${clientes}" var="cliente">
									<option value="${cliente.id}">${cliente.nome}</option>							
								</c:forEach>		
							</form:select>		
						</div>
						<form:errors path="cliente.id"/>			 							
					</div>
					
					<div><form:hidden path="dataCriacao" value="${dataCriacao}"/></div>
					<div><form:hidden path="tipoRecebivel.id" value="${tipoRecebivel.id}"/></div>
					<div><form:hidden path="situacaoRecebivel.id" value="${situacaoRecebivel.id}"/></div>
										
					<div class="form-group">
						<label class="col-sm-1 control-label">Data de Vencimento:</label>
						<div class="col-sm-4"><form:input class="form-control" path="dataVencimento"/></div>
						<form:errors path="dataVencimento"/>
					</div>
					
					<div class="form-group">
						<label class="col-sm-1 control-label">Valor:</label>
						<div class="col-sm-4"><form:input class="form-control" path="valor"/></div>
						<form:errors path="valor"/>
					</div>
														
					<br>
					<div class="form-group">
						<div class="col-sm-offset-1 col-sm-10">
							<button type="submit" class="btn btn-primary">Salvar</button>
						</div>
					</div>
					
			</div> 
		</div>
		</div>
	
	</form:form>

	
<%@ include file="/WEB-INF/views/rodape.jsp" %>
