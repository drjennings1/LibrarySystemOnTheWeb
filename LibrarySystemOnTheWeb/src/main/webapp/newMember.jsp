<%@ include file="header.jsp" %>
<section>
    <h1>Add a New Member</h1>
    <form action="library" method="post">
        <input type="hidden" name="action" value="addBorrower" />

        <label>Name:</label>
        <input type="text" name="name" required /><br>

        <label>Email:</label>
        <input type="text" name="email" required /><br>

        <label>Phone:</label>
        <input type="text" name="phone" required /><br>

        <input type="submit" value="Add Member" />
    </form>
    
    <c:if test="${not empty error}">
    <p style="color: red;">${error}</p>
    </c:if>
        
</section>
<%@ include file="footer.jsp" %>
