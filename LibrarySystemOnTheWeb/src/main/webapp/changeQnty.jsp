<%@include file="header.jsp" %>

    <form action="library" method="post" style="margin-top: 20px;">
        <input type="hidden" name="action" value="changeQnty"/>
        <input type="hidden" name="bookId" value="${bookId}"/>
        <label>Enter new quantity for ${title} (id: ${bookId}):</label>
        <input type="number" name="avail_qnty" required/>
        <input type="submit" value="Change"/>
    </form>
<%@include file="footer.jsp" %>