<%@ include file="header.jsp" %>
<section>
    <h1>Add a New Book</h1>
    <form action="library" method="post">
        <input type="hidden" name="action" value="addMember" />

        <label>Title:</label>
        <input type="text" name="name" required /><br>

        <label>Author:</label>
        <input type="text" name="email" required /><br>

        <label>Genre:</label>
        <input type="text" name="phone" required /><br>

        <input type="submit" value="Save Book" />
    </form>
</section>
<%@ include file="footer.jsp" %>
