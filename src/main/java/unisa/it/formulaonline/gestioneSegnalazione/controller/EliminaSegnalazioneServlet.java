package unisa.it.formulaonline.gestioneSegnalazione.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import unisa.it.formulaonline.gestioneSegnalazione.service.GestioneSegnalazioneService;
import unisa.it.formulaonline.gestioneSegnalazione.service.GestioneSegnalazioneServiceImpl;
import unisa.it.formulaonline.model.entity.Commento;
import unisa.it.formulaonline.model.entity.Lettore;
import unisa.it.formulaonline.model.entity.Segnalazione;

import java.io.IOException;

/**
 * Servlet per eliminare una segnalazione
 * prende l'idSegnalazione dalla request e controlla che sia stato un moderatore ad effettuare la richiesta
 */
@WebServlet("/eliminaSegnalazione")
public class EliminaSegnalazioneServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Lettore l = (Lettore) req.getSession().getAttribute("lettore");
        /*Se il parametro esiste e la richiesta è stata fatta da un moderatore*/
        if(req.getParameter("segnalazione")!=null
                && l.getModeratore()){
            int idS = Integer.parseInt(req.getParameter("segnalazione"));
            GestioneSegnalazioneService gs = new GestioneSegnalazioneServiceImpl();
            gs.eliminaSegnalazione(idS);
        }
    }
}
