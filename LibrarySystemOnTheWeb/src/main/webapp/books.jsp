<%@include file="header.jsp" %>

    <form action="newBook.jsp" method="get" style="margin-top: 20px;">
        <input type="submit" value="Add New Book to Library" />
    </form>
    <form action="removeBook.jsp" method="get" style="margin-top: 20px;">
        <input type="submit" value="Remove a book from the Library" />
    </form>
    <h1> All Books in the Library </h1>
    <table border="1">
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
                        <img src="images/notAvailable.png" height="20" width="20"/>
                    </c:when>
                    <c:otherwise>
                        <img src="images/available.png" height="20" width="20"/>
                    </c:otherwise>
                </c:choose>
            </td>
            <td>
                <form action="library" method="get">
                    <input type="hidden" name="action" value="changeQnty"/>
                    <input type="hidden" name="bookId" value="${book.bookId}"/>
                    <input type="hidden" name="title" value="${book.title}"/>
                    <input type ="submit" value="Change Quantity"/>
                </form>
            </td>
        </tr>
        </c:forEach>
    </table>
<%@include file="footer.jsp" %>
