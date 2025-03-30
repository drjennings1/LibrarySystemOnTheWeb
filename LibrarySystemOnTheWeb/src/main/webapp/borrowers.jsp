<%@include file="header.jsp" %>
<section>
    <form action="newMember.jsp" method="get" style="margin-top: 20px;">
        <input type="submit" value="Add New Member" />
    </form>
</section>
<section>
    <h1>A list of all borrowers</h1>
    <table>
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
                    <form action="library" method="post">
                        <input type="hidden" name="action" value="startCheckout"/>
                        <input type="hidden" name="borrowerId" value="${borrower.borrowerId}"/>
                        <input type="submit" value="Check Out Book"/>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</section>


<%@include file="footer.jsp" %>
