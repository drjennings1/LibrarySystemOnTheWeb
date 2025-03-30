package library.controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.FileWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import library.data.BookDb;
import library.data.BorrowerDb;
import library.model.Book;
import library.model.Borrower;

/**
 *
 * @author dyl4n
 */
@WebServlet(urlPatterns = {"/library"})
public class LibraryController extends HttpServlet {


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
            case "borrow":
                break;
            case "return":
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

    
//-----------------Book methods----------------------//
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

//--------------------Member/Borrower methods----------------//
    private String showAllMembers(HttpServletRequest request) {
        List<Borrower> borrowers = BorrowerDb.selectAll();
        request.setAttribute("borrowerList", borrowers);
        return "/borrowers.jsp";
    }
    
    private void addBorrower(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String name = request.getParameter("title");
        String email = request.getParameter("author");
        String phone = request.getParameter("genre");
        java.sql.Date today = new java.sql.Date(System.currentTimeMillis());
        
        Borrower borrower = new Borrower();
        borrower.setName(name);
        borrower.setEmail(email);
        borrower.setPhone(phone);
        borrower.setMembershipDate(today);
        BorrowerDb.insert(borrower);
        
        response.sendRedirect("library?action=books");
    }
    
    
}
