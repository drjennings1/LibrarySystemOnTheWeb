<%@ include file="header.jsp" %>
<section class="content-wrapper">
<h1 class="text-center">Add a New Title to the Catalog</h1>

<div class="row">
    <div class="col-10 col-md-6 offset-md-2">
        <form action="library" method="post">
            <input type="hidden" name="action" value="addBook" />

            <div class="form-row mb-3">
                <label class="form-label">Title:</label>
                <input type="text" name="title" required class="form-control" />
            </div>
            <div class="form-row mb-3"> 
                <label class="form-label">Author:</label>
                <input type="text" name="author" required class="form-control"/>
            </div>
            <div class="form-row mb-3">
                <label class="form-label">Genre:</label>
                <input type="text" name="genre" required class="form-control"/>
            </div>
            <div class="form-row mb-3">
                <label class="form-label">Quantity:</label>
                <input type="number" name="qntyAvail" required class="form-control"/>
            </div>
            <input type="submit" value="Save Book" class="btn btn-success" />
        </form>
    </div> <!-- column div -->
</div> <!-- row div-->
<c:if test="${not empty error}">
    <div class="alert alert-danger" role="alert">
            ${error}
    </div>
</c:if>
</section><!-- end content wrapper -->


<%@ include file="footer.jsp" %>
