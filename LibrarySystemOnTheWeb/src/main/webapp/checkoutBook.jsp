<%@ include file="header.jsp" %>

<h2>Available Books</h2>
<table border="1">
    <tr>
        <th>Title</th>
        <th>Author</th>
        <th>Available Qty</th>
        <th>Action</th>
    </tr>
    <c:forEach var="book" items="${bookList}">
        <tr>
            <td>${book.title}</td>
            <td>${book.author}</td>
            <td>${book.qntyAvail}</td>
            <td>
                <c:choose>
                    <c:when test="${!book.available}">
                        Not available
                    </c:when>
                    <c:otherwise>
                        <form action="library" method="post" style="margin: 0;">
                            <input type="hidden" name="action" value="addToBasket">
                            <input type="hidden" name="bookId" value="${book.bookId}">
                            <input type="hidden" name="borrowerId" value="${borrowerId}">
                            <input type="submit" value="Add to Basket">
                        </form>
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>
    </c:forEach>
</table>

<h2>Basket</h2>
<table border="1">
    <tr>
        <th>Title</th>
        <th>Author</th>
        <th>Action</th>
    </tr>
    <c:forEach var="book" items="${basket}">
        <tr>
            <td>${book.title}</td>
            <td>${book.author}</td>
            <td>
                <form action="library" method="post" style="display:inline;">
                    <input type="hidden" name="action" value="removeFromBasket">
                    <input type="hidden" name="bookId" value="${book.bookId}">
                    <input type="hidden" name="borrowerId" value="${borrowerId}">
                    <input type="submit" value="Remove">
                </form>
            </td>
        </tr>
    </c:forEach>
</table>

<!-- Main form for completing checkout: placed after the table -->
<form action="library" method="post">
    <input type="hidden" name="action" value="completeCheckout">
    <input type="hidden" name="borrowerId" value="${borrowerId}">

    <!-- Add bookId[] for each item in the basket -->
    <c:forEach var="book" items="${basket}">
        <input type="hidden" name="bookId[]" value="${book.bookId}" />
    </c:forEach>

    <input type="submit" value="Complete Checkout">
</form>


<%@ include file="footer.jsp" %>
