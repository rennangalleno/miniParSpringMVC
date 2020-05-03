<%@ include file="/WEB-INF/views/cabecalho.jsp" %>

<div class="container">
	<form:form class="form-horizontal" action="/minipar/pagador/edita" method="POST" modelAttribute="pagador">
		
		<div class="panel panel-default">
	
			<div class="panel-heading">
		   		<div class="clearfix">
		   			<h1 class="panel-title aw-titulo-panel">Alterar pagador</h1>
		   		</div>
		   	</div>
		   	
			<div class="panel-body">
			
					<div class="form-group">
						<label class="col-sm-1 control-label">Id:</label>
							<div class="col-sm-1"><form:input disabled="true" class="form-control" path="id"/></div>
							<form:hidden path="id" value="${pagador.id}"/>	
					</div>
			
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
	</form:form>
		
</div>
	
<%@ include file="/WEB-INF/views/rodape.jsp" %>