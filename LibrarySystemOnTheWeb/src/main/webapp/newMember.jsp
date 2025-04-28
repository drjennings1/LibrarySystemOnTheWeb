<%@ include file="header.jsp" %>
<section class="content-wrapper">
    
<h1 class="text-center">Add a New Member</h1>
<div class="row">
    <div class="col-10 col-md-6 offset-md-2">
        <form action="library" method="post">
            <input type="hidden" name="action" value="addBorrower" />
            <div class="form-row mb-3" >
                    <label class="form-label">Name:</label>
                    <input type="text" name="name" required />
            </div>
            <div class="form-row mb-3">
                    <label class="form-label">Email:</label>
                    <input type="text" name="email" required />
            </div>
            <div class="form-row mb-3">
                    <label class="form-label">Phone:</label>
                    <input type="text" name="phone" required />
            </div>
                    <input type="submit" value="Add Member" class="btn btn-success" />
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
