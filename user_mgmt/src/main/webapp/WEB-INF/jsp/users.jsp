<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>User Management</title>
<style>
.error {
	color: #ff0000;
	font-weight: normal;
}
</style>
<link href='<c:url value="/bootstrap-3.3.5/css/bootstrap.min.css"/>' rel="stylesheet">
<script src='<c:url value="/js/user.js"/>'></script>
<script src='<c:url value="/js/jquery-1.11.3.min.js"/>'></script>
<script src='<c:url value="/bootstrap-3.3.5/js/bootstrap.min.js"/>'></script>
</head>
<body>
	<div class="container">
		<h2>
			<spring:message code="user.form.label.msg" />
		</h2>
		<a href='<c:url value="/users/add.html"/>'>+ Add User</a><br /> <br />
		<c:if test="${not empty users}">
			<div class="col-lg-12">
				<table class="table" id="table">
					<thead>
						<tr>
							<th><spring:message code="user.form.sno" /></th>
							<th><spring:message code="user.form.firstName" /></th>
							<th><spring:message code="user.form.lastName" /></th>
							<th><spring:message code="user.form.sex" /></th>
							<th><spring:message code="user.form.phone" /></th>
							<th><spring:message code="user.form.email" /></th>
							<th><spring:message code="user.form.address" /></th>
							<th><spring:message code="user.form.action" /></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${users}" var="user" varStatus="sno">
							<tr>
								<td>${sno.count}</td>
								<td>${user.firstName}</td>
								<td>${user.lastName}</td>
								<td>${user.sex}</td>
								<td>${user.phone}</td>
								<td>${user.email}</td>
								<td>${user.address}</td>
								<td><a href='<c:url value="/users/${user.id}.html"/>'> <img alt="Update" src='<c:url value="/images/edit.png"/>' />
								</a>&nbsp;<a href="javascript:void(0);" onclick="ConfirmDelete('<c:url value="/users/delete/${user.id}.html"/>')"><img alt="Update"
										src='<c:url value="/images/delete.png"/>' /></a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</c:if>
		<c:if test="${not empty userForm.action}">
			<div class="container">
				<c:url var="actionUrl" value="/users/add-update.html"></c:url>
				<form:form action="${actionUrl}" method="post" cssClass="form-horizontal" role="form" modelAttribute="userForm">
					<h2>${userForm.label}</h2>
					<form:hidden path="userId" />
					<form:hidden path="action" />
					<div class="form-group">
						<label for="firstName" class="col-sm-3 control-label"><spring:message code="user.form.firstName" /></label>
						<div class="col-sm-3">
							<form:input path="firstName" cssClass="form-control" />
						</div>
						<div class="col-sm-3">
							<form:errors path="firstName" cssClass="error" />
						</div>
					</div>
					<div class="form-group">
						<label for="lastName" class="col-sm-3 control-label"><spring:message code="user.form.lastName" /></label>
						<div class="col-sm-3">
							<form:input path="lastName" cssClass="form-control" />
						</div>
						<div class="col-sm-3">
							<form:errors path="lastName" cssClass="error" />
						</div>
					</div>
					<div class="form-group">
						<label for="sex" class="col-sm-3 control-label"> <spring:message code="user.form.sex" /></label>
						<div class="col-sm-3">
							<div class="row">
								<div class="col-sm-3">
									<label class="radio-inline"> <form:radiobutton path="sex" value="Male" />Male
									</label>
								</div>
								<div class="col-sm-3">
									<label class="radio-inline"> <form:radiobutton path="sex" value="Female" />Female
									</label>
								</div>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label for="email" class="col-sm-3 control-label"><spring:message code="user.form.email" /></label>
						<div class="col-sm-3">
							<form:input path="email" cssClass="form-control" />
						</div>
						<div class="col-sm-3">
							<form:errors path="email" cssClass="error" />
						</div>
					</div>
					<div class="form-group">
						<label for="phone" class="col-sm-3 control-label"><spring:message code="user.form.phone" /></label>
						<div class="col-sm-3">
							<form:input path="phone" cssClass="form-control" />
						</div>
						<div class="col-sm-3">
							<form:errors path="phone" cssClass="error" />
						</div>
					</div>
					<div class="form-group">
						<label for="address" class="col-sm-3 control-label"><spring:message code="user.form.address" /></label>
						<div class="col-sm-3">
							<form:textarea path="address" cssClass="form-control" />
						</div>
						<div class="col-sm-3">
							<form:errors path="address" cssClass="error" />
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-9 col-sm-offset-3">
							<button type="submit" class="btn">Submit</button>
							<button type="button" class="btn" onclick="userForm.reset()">Reset</button>
							<button type="button" class="btn" onclick="retunToUrl('<c:url value="/users.html"/>')">Cancel</button>
						</div>
					</div>
				</form:form>
			</div>
		</c:if>
	</div>
</body>
</html>