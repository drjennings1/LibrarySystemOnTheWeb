<%@include file="header.jsp" %>
<section class="content-wrapper">
<form action="newMember.jsp" method="get" style="margin-top: 20px;">
    <input type="submit" value="Add New Member" class="btn btn-success"  />
</form>

<h1 class="text-center">Members List</h1>
<div class="table-responsive">
<table class="table table-bordered">
    <tr>
        <th>Member ID</th>
        <th>Name</th>
        <th>Email</th>
        <th>Phone #</th>
        <th>Start Date</th>
    </tr>
    <c:forEach var="borrower" items="${borrowerList}">
        <tr>
            <td>${borrower.borrowerId}</td>
            <td>${borrower.name}</td>
            <td>${borrower.email}</td>
            <td>${borrower.phone}</td>
            <td><fmt:formatDate value="${borrower.membershipDate}" pattern="MM/dd/yyyy"/></td>
            <td>
                <form action="library" method="get">
                    <input type="hidden" name="action" value="checkout"/>
                    <input type="hidden" name="borrowerId" value="${borrower.borrowerId}"/>
                    <input type="submit" value="Checkout" class="btn btn-primary me-2 mb-2" />
                </form>
            </td>
            <td>
                <form action="library" method="get">
                    <input type="hidden" name="action" value="return"/>
                    <input type="hidden" name="borrowerId" value="${borrower.borrowerId}"/>
                    <input type="submit" value="Return" class="btn btn-primary mb-2"/>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
</div> <!-- Table responsive div -->
</section><!-- end content wrapper -->

<%@include file="footer.jsp" %>
