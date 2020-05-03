<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<html>
<head>
	<meta charset="utf-8" />
	<c:url value="/resources/css" var="cssPath"/>
	<link rel="stylesheet" href="${cssPath}/bootstrap.min.css">
	<link rel="stylesheet" href="${cssPath}/bootstrap-theme.min.css">
	<link rel="stylesheet" href="${cssPath}/botaoLink.css">
 
	<title>Pagina Inicial</title>
</head>


<body>
	<nav class="navbar navbar-inverse">
		<div class="container">
			<div class="navbar-header">
				 <a class="navbar-brand" href="/minipar/">Pagina Inicial</a>
			</div>
			<div class="collpase navbar-collapse">
				<ul class="nav navbar-nav">
				<li><a href="/minipar/cliente/lista">Cliente</a></li>
				<li><a href="/minipar/pagador/lista">Pagador</a></li>
				<li><a href="/minipar/cartao/lista">Cartão</a></li>
				<li><a href="/minipar/cheque/lista">Cheque</a></li>
				<li><a href="/minipar/boleto/lista">Boleto</a></li>	
				<li><a href="/minipar/agenda/cliente">Agenda Financeira</a></li>
				<li><a href="#">Conta</a></li>
				</ul>
			</div>
		</div>
	</nav>