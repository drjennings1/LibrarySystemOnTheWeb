<%@include file="header.jsp" %>

<form action="newMember.jsp" method="get" style="margin-top: 20px;">
    <input type="submit" value="Add New Member" />
</form>

<h1>Members List</h1>
<table border="1">
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
                    <input type="submit" value="Checkout"/>
                </form>
            </td>
            <td>
                <form action="library" method="get">
                    <input type="hidden" name="action" value="return"/>
                    <input type="hidden" name="borrowerId" value="${borrower.borrowerId}"/>
                    <input type="submit" value="Return"/>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>


<%@include file="footer.jsp" %>
