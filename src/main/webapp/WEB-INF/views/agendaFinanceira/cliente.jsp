<%@ include file="/WEB-INF/views/cabecalho.jsp" %>
		
<div class="container">
	
	<form class="form-horizontal"  action="/minipar/agenda/lista?${cliente.id}" method="GET">
		
		<div class="panel panel-default">
		
			<div class="panel-heading">
				<div class="clearfix">
					<h1 class="panel-title aw-titulo-panel">Escolher Cliente</h1>
				</div>
			</div>
			
			<div class="panel-body">
			
				<div class="form-group">	
													
					<label class="col-sm-1 control-label mr-1">Cliente:</label>
					<div class="col-sm-3">
						<select class="form-control" name="clienteId">
							<option value="">Selecione</option>
							<c:forEach items="${clientes}" var="cliente">
							   <option value="${cliente.id}">${cliente.nome}</option>							
							</c:forEach>		
						</select>	
					</div>
					<!-- 															
					<label class="col-sm-1 control-label">Conta Corrente:</label>
					<div class="col-sm-3"><input class="form-control" type="text" name="cliente.conta"/></div>
					 -->
					<div><button type="submit" class="btn btn-primary">Avançar</button></div>			
				</div>
					
											
			</div>
			
		</div>		
	</form>
</div>


<%@ include file="/WEB-INF/views/rodape.jsp" %>