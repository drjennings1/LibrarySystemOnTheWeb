<%@include file="header.jsp" %>
<section class="content-wrapper">
    <div class="row mb-3">
        <div class="col-12 col-md-6 mb-2">
            <form action="newBook.jsp" method="get">
                <input type="submit" value="Add New Book to Library" class="btn btn-success w-100-mobile" />
            </form>
        </div>
        <div class="col-12 col-md-6 mb-2">
            <form action="removeBook.jsp" method="get">
                <input type="submit" value="Remove a Book from the Library" class="btn btn-danger w-100-mobile" />
            </form>
        </div>
    </div>
    
    <h1 class="text-center"> All Books in the Library </h1>
    <div class="table-responsive">
    <table class="table table-bordered">
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
                        <img src="images/notAvailable.png" class="img-fluid" height="20" width="20"/>
                    </c:when>
                    <c:otherwise>
                        <img src="images/available.png" class="img-fluid" height="20" width="20"/>
                    </c:otherwise>
                </c:choose>
            </td>
            <td>
                <form action="library" method="get">
                    <input type="hidden" name="action" value="changeQnty"/>
                    <input type="hidden" name="bookId" value="${book.bookId}"/>
                    <input type="hidden" name="title" value="${book.title}"/>
                    <input type ="submit" value="Change Quantity" class="btn btn-warning"/>
                </form>
            </td>
        </tr>
        </c:forEach>
    </table>
    </div> <!-- Table responsive div -->
</section><!-- end content wrapper -->
<%@include file="footer.jsp" %>
