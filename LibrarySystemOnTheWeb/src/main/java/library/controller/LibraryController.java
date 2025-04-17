package library.controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import library.data.BookDb;
import library.data.BorrowedBookDb;
import library.data.BorrowerDb;
import library.model.Book;
import library.model.BorrowedBook;
import library.model.Borrower;

/**
 *
 * @author dyl4n
 */
@WebServlet(urlPatterns = {"/library"})
public class LibraryController extends HttpServlet {

    
    // Can't seem to find the server log, so must write errors to a txt file
    // temporary solution
//    private void logToFile(String message) {
//        try (FileWriter writer = new FileWriter("C:/temp/library-log.txt", true)) {
//            writer.write(message + "\n");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }


    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        String url = "/index.jsp"; // default, will change
        
        if (action == null){
            action = "home";
        }
        
        switch(action){
            case "books":
                url = showAvailableBooks(request);
                break;
            case "borrowers":
                url = showAllMembers(request);
                break;
            case "checkout":
                url = loadCheckoutPage(request);
                break;
            case "return":
                url = loadReturnPage(request);
                break;
            case "changeQnty":
                url = loadChangeQntyPage(request);
                break;
            case "stats":
                break;
            default:
                url = "/index.jsp";
                break;
        }
        
        getServletContext().getRequestDispatcher(url).forward(request, response);
    }

    
    
    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        String url = "";
        
        switch(action){
            case "addBook":
                addBook(request, response);
                return;
            case "addBorrower":
                addBorrower(request, response);
                return;
            case "addToBasket":
                addToBasket(request, response);
                return;
            case "removeFromBasket":
                removeFromBasket(request, response);
                return;
            case "completeCheckout":
                completeCheckout(request, response);
                return;
            case "markReturned":
                markBookReturned(request, response);
                return;
            case "removeBook":
                removeBook(request, response);
                return;
            case "changeQnty":
                changeQnty(request, response);
                return;
        }
        response.sendRedirect("index.jsp");
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    
//-----------------Library book methods----------------------//
    private String showAvailableBooks(HttpServletRequest request) {
        List<Book> books = BookDb.selectAll();
        request.setAttribute("bookList", books);
        return "/books.jsp";
    }
    
    private void addBook(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String genre = request.getParameter("genre");
        int qntyAvail = Integer.parseInt(request.getParameter("qntyAvail"));
        
        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);
        book.setGenre(genre);
        book.setQntyAvail(qntyAvail);
        BookDb.insert(book);
        
        response.sendRedirect("library?action=books");
    }
    
    private void removeBook (HttpServletRequest request, HttpServletResponse response) throws IOException{
        int bookId = Integer.parseInt(request.getParameter("bookId"));
        String title = request.getParameter("title");
        
        Book book = new Book();
        book.setBookId(bookId);
        book.setTitle(title);
        
        BookDb.remove(book);
        response.sendRedirect("library?action=books");
    }
    
    private String loadChangeQntyPage(HttpServletRequest request){
        int bookId = Integer.parseInt(request.getParameter("bookId"));
        String title = request.getParameter("title");
        request.setAttribute("bookId", bookId);
        request.setAttribute("title", title);
        
        return "/changeQnty.jsp";
    }
    
    private void changeQnty(HttpServletRequest request, HttpServletResponse response) throws IOException{
        int bookId = Integer.parseInt(request.getParameter("bookId"));
        int qnty = Integer.parseInt(request.getParameter("avail_qnty"));
        BookDb.changeQnty(bookId, qnty);
        
        response.sendRedirect("library?action=books");
    }

//--------------------Member/Borrower methods----------------//
    private String showAllMembers(HttpServletRequest request) {
        List<Borrower> borrowers = BorrowerDb.selectAll();
        request.setAttribute("borrowerList", borrowers);
        return "/borrowers.jsp";
    }
    
    private void addBorrower(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        java.sql.Date today = new java.sql.Date(System.currentTimeMillis());
        
        Borrower borrower = new Borrower();
        borrower.setName(name);
        borrower.setEmail(email);
        borrower.setPhone(phone);
        borrower.setMembershipDate(today);
        BorrowerDb.insert(borrower);
        
        response.sendRedirect("library?action=borrowers");
    }
    
//--------------------Checkout methods----------------//
    
    private String loadCheckoutPage(HttpServletRequest request){
        int borrowerId = Integer.parseInt(request.getParameter("borrowerId"));
        List<Book> books = BookDb.selectAll();
        
        HttpSession session = request.getSession();
        List<Book> basket = (List<Book>) session.getAttribute("basket");
        
        request.setAttribute("borrowerId", borrowerId);
        request.setAttribute("bookList", books);
        request.setAttribute("basket", basket);
        return "/checkoutBook.jsp";
    }
    
    private void addToBasket(HttpServletRequest request, HttpServletResponse response) throws IOException{
        int bookId = Integer.parseInt(request.getParameter("bookId"));
        int borrowerId = Integer.parseInt(request.getParameter("borrowerId"));
        Book bookToAdd = BookDb.selectBookById(bookId);
        
        HttpSession session = request.getSession();
        List<Book> basket = (List<Book>) session.getAttribute("basket");
        
        if (basket == null) { // creates new basket object if it doesn't exist
            basket = new ArrayList<>();
        }
        
        // preventing 2 copies from being allowed in the basket
        boolean alreadyInBasket = false;
        for (Book b : basket){
            if (b.getBookId() == bookId){
                alreadyInBasket = true;
                break;
            }
        }
        if (!alreadyInBasket){
            basket.add(bookToAdd);
        }
        
        session.setAttribute("basket", basket);
        request.setAttribute("borrowerId", borrowerId);
        request.setAttribute("bookList", BookDb.selectAll());
        request.setAttribute("basket", basket);
        response.sendRedirect("library?action=checkout&borrowerId=" + borrowerId);
    }
    
    private void removeFromBasket(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int bookId = Integer.parseInt(request.getParameter("bookId"));
        int borrowerId = Integer.parseInt(request.getParameter("borrowerId"));

        HttpSession session = request.getSession();
        List<Book> basket = (List<Book>) session.getAttribute("basket");

        if (basket != null) {
            basket.removeIf(book -> book.getBookId() == bookId);
            session.setAttribute("basket", basket); // Update the basket
        }
        // Redirect back to checkout page
        response.sendRedirect("library?action=checkout&borrowerId=" + borrowerId);
}

    private void completeCheckout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int borrowerId = Integer.parseInt(request.getParameter("borrowerId"));
        String[] bookIds = request.getParameterValues("bookId[]");

        if (bookIds != null && bookIds.length > 0) {
            for (String bookIdStr : bookIds) {
                int bookId = Integer.parseInt(bookIdStr);
                BorrowedBookDb.insert(borrowerId, bookId);
                BookDb.reduceQnty(bookId);
            }
        }

        // Clear basket from session
        HttpSession session = request.getSession();
        session.removeAttribute("basket");

        response.sendRedirect("library?action=borrowers");
    }
    
//--------------------Return methods----------------//
    private String loadReturnPage(HttpServletRequest request){
        int borrowerId = Integer.parseInt(request.getParameter("borrowerId"));
        List<BorrowedBook> checkedOut = BorrowedBookDb.selectCurrentCheckouts(borrowerId);
        
        request.setAttribute("checkedOutList", checkedOut);
        request.setAttribute("borrowerId", borrowerId);
        
        return "/returnBooks.jsp";
    }
    
    private void markBookReturned(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int borrowerId = Integer.parseInt(request.getParameter("borrowerId"));
        int bookId = Integer.parseInt(request.getParameter("bookId"));

        BorrowedBookDb.markReturned(borrowerId, bookId);
        BookDb.increaseQnty(bookId);

        response.sendRedirect("library?action=return&borrowerId=" + borrowerId);
    }
}
