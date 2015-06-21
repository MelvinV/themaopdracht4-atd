/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import domain.Person;
import domain.Reservation;
import domain.validate.ErrorList;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import service.ReservationService;

/**
 *
 * @author Nigel
 */
public class WEB_parkeren extends HttpServlet {
    @Inject
    private ReservationService reservations;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        if(request.getParameter("placeReservation") != null){
        Reservation newR = new Reservation();
        
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.GERMANY);
        try {
            cal.setTime(sdf.parse(request.getParameter("arrivalDate")));
        } catch (ParseException ex) {
            Logger.getLogger(CMS_Werkplaats_Toevoegen.class.getName()).log(Level.SEVERE, null, ex);
        }
        newR.setArrivalDate(cal);
        
        try {
            cal.setTime(sdf.parse(request.getParameter("pickupDate")));
        } catch (ParseException ex) {
            Logger.getLogger(CMS_Werkplaats_Toevoegen.class.getName()).log(Level.SEVERE, null, ex);
        }
        newR.setPickupDate(cal);
        
        newR.setThePerson((Person)request.getSession().getAttribute("user"));
        
        ErrorList eL = newR.validate();
        
        if(eL.hasError()) {
            eL.setAttributes(request);
        }
        
        if(eL.isValid()) {
            reservations.create(newR);
        }} 
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pageParts/WEB_Parkeren.jsp");
        rd.forward(request, response);
        }

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
        processRequest(request, response);
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
        processRequest(request, response);
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

}
