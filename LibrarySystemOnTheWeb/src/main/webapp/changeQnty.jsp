<%@include file="header.jsp" %>
<section class="content-wrapper">
    <h1> Changing quantity available for: ${title} (ID: ${bookId})</h1>
<div class="row">
    <div class="col-10 col-md-6 offset-md-2">
        <form action="library" method="post" style="margin-top: 20px;">
            <input type="hidden" name="action" value="changeQnty"/>
            <input type="hidden" name="bookId" value="${bookId}"/>
            <label class="form-label">New Quantity:</label>
            <input type="number" name="avail_qnty" required class="form-control"/>
            <input type="submit" value="Change" class="btn btn-danger"/>
        </form>
        <c:if test="${not empty error}">
            <div class="alert alert-danger" role="alert">
                ${error}
            </div>
        </c:if>
    </div> <!-- column div -->
</div> <!-- row div -->
</section><!-- end content wrapper -->
<%@include file="footer.jsp" %>