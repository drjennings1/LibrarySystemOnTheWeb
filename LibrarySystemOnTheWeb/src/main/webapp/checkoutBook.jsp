<%@ include file="header.jsp" %>
<section class="content-wrapper">
<h2 class="text-center">Available Books</h2>
<div class="table-responsive">
<table class="table table-bordered">
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
                            <input type="submit" value="Add to Basket" class="btn btn-primary w-100-mobile">
                        </form>
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>
    </c:forEach>
</table>

</div> <!-- Table responsive div -->
<h2>Basket</h2>
<div class="table-responsive">
<table class="table table-bordered">
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
                    <input type="submit" value="Remove" class="btn btn-primary w-100-mobile">
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
</div> <!-- Table responsive div -->
<!-- Main form for completing checkout: placed after the table -->
<form action="library" method="post">
    <input type="hidden" name="action" value="completeCheckout">
    <input type="hidden" name="borrowerId" value="${borrowerId}">

    <!-- Add bookId[] for each item in the basket -->
    <c:forEach var="book" items="${basket}">
        <input type="hidden" name="bookId[]" value="${book.bookId}" />
    </c:forEach>

    <input type="submit" value="Complete Checkout" class="btn btn-success w-100-mobile">
</form>
    <c:if test="${param.error == 'copyExists'}">
        <div class="alert alert-danger" role="alert">
                You can only check out 1 of each book.
        </div>
    </c:if>
</section><!-- end content wrapper -->
<%@ include file="footer.jsp" %>
