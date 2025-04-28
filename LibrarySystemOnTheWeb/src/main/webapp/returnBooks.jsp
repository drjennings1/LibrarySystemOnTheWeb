<%@include file="header.jsp" %>
<section class="content-wrapper">
<h1 class="text-center">Books Checked Out by Member</h1>
<div class="table-responsive">
<table class="table table-bordered">
    <tr>
        <th>Title</th>
        <th>Author</th>
        <th>Due Date</th>
        <th>Return</th>
    </tr>
    <c:forEach var="borrowedBook" items="${checkedOutList}">
        <tr>
            <td>${borrowedBook.book.title}</td>
            <td>${borrowedBook.book.author}</td>
            <td>${borrowedBook.returnDate}</td>
            <td>
                <form action="library" method="post">
                    <input type="hidden" name="action" value="markReturned" />
                    <input type="hidden" name="borrowerId" value="${borrowedBook.borrowerId}" />
                    <input type="hidden" name="bookId" value="${borrowedBook.bookId}" />
                    <input type="submit" value="Return" class="btn btn-danger"/>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
</div> <!-- Table responsive div -->
</section><!-- end content wrapper -->
<%@include file="footer.jsp" %>
