/*package controller;*/
import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import entidade.Compra;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.CompraDAO;

/**
 *
 * @author Leonardo
 */
@WebServlet(name = "RegistroCompra", urlPatterns = {"/RegistroCompra"})
public class RegistroCompra extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

//        RequestDispatcher rd;
//        rd = request.getRequestDispatcher("/views/registro/formRegistro.jsp");
//        rd.forward(request, response);
        
        String acao = (String) request.getParameter("acao");
        Compra compra = new Compra();
        CompraDAO compraDAO = new CompraDAO();
        RequestDispatcher rd;
        switch (acao) {
            case "Listar":
                ArrayList<Compra> listaCompras = compraDAO.ListaDeCompras();
                request.setAttribute("listaCompras", listaCompras);

                rd = request.getRequestDispatcher("/views/compras/listaCompras.jsp");
                rd.forward(request, response);

                break;
            case "Alterar":
            case "Excluir":

                // get parametro ação indicando sobre qual usuário será a ação
                int id = Integer.parseInt(request.getParameter("id"));
            {
                try {
                    compra = compraDAO.getCompra(id);
                } catch (Exception ex) {
                    Logger.getLogger(RegistroCompra.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

                request.setAttribute("compra", compra);
                request.setAttribute("msgError", "");
                request.setAttribute("acao", acao);

                rd = request.getRequestDispatcher("/views/compras/formCompras.jsp");
                rd.forward(request, response);
                break;

            case "Incluir":
                request.setAttribute("compra", compra);
                request.setAttribute("msgError", "");
                request.setAttribute("acao", acao);

                rd = request.getRequestDispatcher("/views/compras/formCompras.jsp");
                rd.forward(request, response);
        }

    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                
        int id = Integer.parseInt(request.getParameter("id"));
        
        int quantidade_compra = Integer.parseInt(request.getParameter("quantidade_compra"));  
        String data_compra = request.getParameter("data_compra");
        float valor_compra = Float.parseFloat(request.getParameter("valor_compra"));
        int id_fornecedor = Integer.parseInt(request.getParameter("id_fornecedor"));
        int id_produto = Integer.parseInt(request.getParameter("id_produto"));
        int id_funcionario = Integer.parseInt(request.getParameter("id_funcionario"));
        String btnEnviar = request.getParameter("btnEnviar");
              

        RequestDispatcher rd;

        if (data_compra.isEmpty() || quantidade_compra == -1 || valor_compra == -1.0 ||id_fornecedor == -1 ||id_produto == -1 ) {
            Compra compra = new Compra();
            switch (btnEnviar) {
                case "Alterar":
                case "Excluir":
                    try {
                    CompraDAO compraDAO = new CompraDAO();
                    compra = compraDAO.getCompra(id);

                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                    throw new RuntimeException("Falha em uma query para cadastro de Compra");
                }
                break;
            }

            request.setAttribute("compra", compra);
            request.setAttribute("acao", btnEnviar);

            request.setAttribute("msgError", "É necessário preencher todos os campos");

            rd = request.getRequestDispatcher("/views/compras/formCompras.jsp");
            rd.forward(request, response);

        } else {
            
             Compra compra = new Compra(quantidade_compra,data_compra,valor_compra,id_fornecedor,id_produto,id_funcionario);
             CompraDAO compraDAO = new CompraDAO();
             compra.setId(id);

            try {
                switch (btnEnviar) {
                    case "Incluir":
                        compraDAO.Inserir(compra);
                        request.setAttribute("msgOperacaoRealizada", "Inclusão realizada com sucesso");
                        break;
                    case "Alterar":
                        compraDAO.Alterar(compra);
                        request.setAttribute("msgOperacaoRealizada", "Alteração realizada com sucesso");
                        break;
                    case "Excluir":
                        compraDAO.Excluir(compra);
                        request.setAttribute("msgOperacaoRealizada", "Exclusão realizada com sucesso");
                        break;
                }

                request.setAttribute("link", "/aplicacaoMVC/RegistroCompra?acao=Listar");
                rd = request.getRequestDispatcher("/views/comum/showMessage.jsp");
                rd.forward(request, response);

            } catch (IOException | ServletException ex) {
                System.out.println(ex.getMessage());
                throw new RuntimeException("Falha em uma query para cadastro de Compra");
            } catch (Exception ex) {
                Logger.getLogger(RegistroCompra.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
