<%@include file="header.jsp" %>
<section>
    <h1>A list of all members</h1>
    <table>
        <tr>
            <th>Member ID</th>
            <th>Name</th>
            <th>Email</th>
            <th>Phone #</th>
            <th>Start Date</th>
        </tr>
        <c:forEach var="member" items="${memberList}">
            <tr>
                <td>${member.member_id}</td>
                <td>${member.name}</td>
                <td>${member.email}</td>
                <td>${member.phone}</td>
                <td>${member.startDate}</td>
                <td>
                    <form action="library" method="post">
                        <input type="hidden" name="action" value="startCheckout"/>
                        <input type="hidden" name="memberId" value="${member.member_id}"/>
                        <input type="submit" value="Check Out Book"/>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</section>


<%@include file="footer.jsp" %>
