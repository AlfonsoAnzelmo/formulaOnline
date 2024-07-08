package unisa.it.formulaonline.gestioneDiscussione.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import unisa.it.formulaonline.gestioneDiscussione.service.GestioneDiscussioneImplementazione;
import unisa.it.formulaonline.gestioneDiscussione.service.GestioneDiscussioneService;
import unisa.it.formulaonline.model.entity.Discussione;

import java.io.IOException;

@WebServlet("/discussione")
public class VisualizzaDiscussione extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter("idDiscussione");
        String indirizzo = "/WEB-INF/discussione-inesistente.jsp";
        if(idStr!=null){
            GestioneDiscussioneService ds = new GestioneDiscussioneImplementazione();
            int idDiscussione = Integer.parseInt(idStr);
            Discussione discussione = ds.ottieniDiscussioneDaId(idDiscussione);
            if(discussione!=null){
                indirizzo = "discussione.jsp";
                req.setAttribute("discussione", discussione);
            }
        }
        RequestDispatcher rd = req.getRequestDispatcher(indirizzo);
        rd.forward(req, resp);
    }
}
