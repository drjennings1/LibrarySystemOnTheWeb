<%@include file="header.jsp" %>
<section class="content-wrapper">
    
<h1 class="text-center">Remove a Book from the Catalog</h1>
<div class="row">
    <div class="col-10 col-md-6 offset-md-2">
        <form action="library" method="post">
                <input type="hidden" name="action" value="removeBook" />
            <div class="form-row mb-3">    
                <label class="form-label">Book ID:</label>
                <input type="number" name="bookId" required />
            </div>
            <div class="form-row mb-3">
                <label class="form-label">Title:</label>
                <input type="text" name="title" required /><br>
            </div>
                <input type="submit" value="Delete Book" class="btn btn-danger" />
        </form>
    </div> <!-- column div -->
</div> <!-- row div -->
</section><!-- end content wrapper -->
<%@include file="footer.jsp" %>