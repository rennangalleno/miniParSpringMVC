<%@ include file="/WEB-INF/views/cabecalho.jsp" %>


	<form:form class="form-horizontal"  action="/minipar/cliente/novo" method="POST" modelAttribute="cliente">
		<div class="container">
		
		<div>${sucesso}</div><br>
		
		<div class="panel panel-default">
	
			<div class="panel-heading">
		   		<div class="clearfix">
		   			<h1 class="panel-title aw-titulo-panel" >Novo cliente</h1>
		   		</div>
		   	</div>
		   	
			<div class="panel-body">
			
					<div class="form-group">
						<label class="col-sm-1 control-label">CPF:</label>
						<div class="col-sm-4"><form:input class="form-control" path="cpf"/></div>
						<form:errors path="cpf"/>
					</div>
					
					<div class="form-group">
						<label class="col-sm-1 control-label">Nome:</label>
						<div class="col-sm-4"><form:input class="form-control" path="nome"/></div>
						<form:errors path="nome"/>
					</div>
					
					<div class="form-group">
						<label class="col-sm-1 control-label">Nascimento:</label>
						<div class="col-sm-4"><form:input class="form-control" path="dataNascimento"/></div>
						<form:errors path="dataNascimento"/>
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
