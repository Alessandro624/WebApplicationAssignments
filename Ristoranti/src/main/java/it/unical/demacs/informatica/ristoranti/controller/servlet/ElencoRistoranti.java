package it.unical.demacs.informatica.ristoranti.controller.servlet;

import it.unical.demacs.informatica.ristoranti.model.Ristorante;
import it.unical.demacs.informatica.ristoranti.persistence.DBManager;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/ristoranti")
public class ElencoRistoranti extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*
        creazione della pagina
        PrintWriter printWriter = resp.getWriter();
        printWriter.println("<html><strong>Funziona</strong></html>");
        */
        String username = (String) req.getSession(true).getAttribute("username");
        if (username != null) {
            // dopo la query al database
            List<Ristorante> ristoranti = new ArrayList<>();
            Ristorante r1 = new Ristorante("Le Casette di Zio Santino", "Ristorate/Pizzeria", "Rende");
            ristoranti.add(r1);
            Ristorante r2 = new Ristorante("Fratelli La Frode", "Pizzeria", "Rende");
            ristoranti.add(r2);
            ristoranti.addAll(DBManager.getInstance().getRistoranteDAO().findAll());
            req.setAttribute("ristoranti", ristoranti);
            // il dispatcher andrebbe usato con JSP
            RequestDispatcher dispatcher = req.getRequestDispatcher("views/elenco_ristoranti.html");
            dispatcher.forward(req, resp);
        } else {
            RequestDispatcher dispatcher = req.getRequestDispatcher("views/notAuthorized.html");
            dispatcher.forward(req, resp);
        }
    }
}
