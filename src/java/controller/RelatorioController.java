package controller;
import entidade.Produto;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.ProdutoDAO;

@WebServlet(name = "RelatorioController", urlPatterns = {"/RelatorioController"})
public class RelatorioController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // get parametro ação indicando o que fazer
        //Produto produto = new Produto();
        ProdutoDAO produtoDAO = new ProdutoDAO();
        RequestDispatcher rd;
                   
        ArrayList<Produto> listaProdutos = produtoDAO.ListaDeProdutos();
        request.setAttribute("listaProdutos", listaProdutos);

        rd = request.getRequestDispatcher("/views/admin/relatorios/relatorioEstoque.jsp");
        rd.forward(request, response);              

    }

}
