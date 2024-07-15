/*package controller;*/
import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import entidade.Venda;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.VendaDAO;

/**
 *
 * @author Leonardo
 */
@WebServlet(name = "RelatorioVendaController", urlPatterns = {"/RelatorioVendaController"})
public class RelatorioVendaController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        VendaDAO vendaDAO = new VendaDAO();
        RequestDispatcher rd;
        
        ArrayList<Venda> listaVendas = vendaDAO.ListaDeVendas();
        request.setAttribute("listaVendas", listaVendas);

        rd = request.getRequestDispatcher("/views/admin/relatorios/relatorioVenda.jsp");
        rd.forward(request, response);

                
    }

}
    
    