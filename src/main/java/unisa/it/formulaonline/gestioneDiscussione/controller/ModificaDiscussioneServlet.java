package unisa.it.formulaonline.gestioneDiscussione.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import unisa.it.formulaonline.model.entity.Lettore;

import java.io.IOException;

@WebServlet("/modificaDiscussione")
public class ModificaDiscussioneServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String destinazione = "index.jsp";
        Lettore l = (Lettore) req.getSession().getAttribute("lettore");
        String discussione = req.getParameter("discussione");
        if(l!=null && l.getModeratore() && discussione!=null){
            destinazione ="WEB-INF/moderazione/modificadiscussione.jsp";
            RequestDispatcher rd = req.getRequestDispatcher(destinazione);
            rd.forward(req, resp);
        }
        resp.sendRedirect(destinazione);
    }
}
