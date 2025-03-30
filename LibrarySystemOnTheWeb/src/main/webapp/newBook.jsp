<%@ include file="header.jsp" %>
<section>
    <h1>Add a New Book</h1>
    <form action="library" method="post">
        <input type="hidden" name="action" value="addBook" />

        <label>Title:</label>
        <input type="text" name="title" required /><br>

        <label>Author:</label>
        <input type="text" name="author" required /><br>

        <label>Genre:</label>
        <input type="text" name="genre" required /><br>

        <label>Quantity Available:</label>
        <input type="number" name="qntyAvail" required /><br>

        <input type="submit" value="Save Book" />
    </form>
</section>
<%@ include file="footer.jsp" %>
