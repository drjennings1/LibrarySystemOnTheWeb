<%@include file="header.jsp" %>
<section>
    <h1> Add a book to the database below:</h1>
    <form action="newBook.jsp" method="get" style="margin-top: 20px;">
        <input type="submit" value="Add New Book" />
    </form>
</section>
<section> 
    <h1> All Books in the Library </h1>
    <table>
        <tr>
            <th>Book ID</th>
            <th>Title</th>
            <th>Author</th>
            <th>Genre</th>
            <th>Quantity</th>
            <th>Available</th>
        </tr>
        <c:forEach var="book" items="${bookList}">
        <tr>
            <td>${book.bookId}</td>
            <td>${book.title}</td>
            <td>${book.author}</td>
            <td>${book.genre}</td>
            <td>${book.qntyAvail}</td>
            <td class="noneAvailable">
                <c:choose>
                    <c:when test="${!book.available}">
                        <img src="images/noneAvailable.png"/>
                    </c:when>
                    <c:otherwise>
                        &nbsp;
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>
        </c:forEach>
    </table>
</section>
<%@include file="footer.jsp" %>
