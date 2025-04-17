<%@include file="header.jsp" %>
<section>
    <h1>Remove an existing book</h1>
    <form action="library" method="post">
        <input type="hidden" name="action" value="removeBook" />

        <label>Book ID:</label>
        <input type="number" name="bookId" required /><br>

        <label>Title:</label>
        <input type="text" name="title" required /><br>

        <input type="submit" value="Delete Book" />
    </form>
</section>
<%@include file="footer.jsp" %>