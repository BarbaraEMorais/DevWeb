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
@WebServlet(name = "RegistroVenda", urlPatterns = {"/RegistroVenda"})
public class RegistroVenda extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

//        RequestDispatcher rd;
//        rd = request.getRequestDispatcher("/views/registro/formRegistro.jsp");
//        rd.forward(request, response);
        
        String acao = (String) request.getParameter("acao");
        Venda venda = new Venda();
        VendaDAO vendaDAO = new VendaDAO();
        RequestDispatcher rd;
        switch (acao) {
            case "Listar":
                ArrayList<Venda> listaVendas = vendaDAO.ListaDeVendas();
                request.setAttribute("listaVendas", listaVendas);

                rd = request.getRequestDispatcher("/views/vendas/listaVendas.jsp");
                rd.forward(request, response);

                break;
            case "Alterar":
            case "Excluir":

                // get parametro ação indicando sobre qual usuário será a ação
                int id = Integer.parseInt(request.getParameter("id"));
            {
                try {
                    venda = vendaDAO.getVenda(id);
                } catch (Exception ex) {
                    Logger.getLogger(RegistroVenda.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

                request.setAttribute("venda", venda);
                request.setAttribute("msgError", "");
                request.setAttribute("acao", acao);

                rd = request.getRequestDispatcher("/views/vendas/formVendas.jsp");
                rd.forward(request, response);
                break;

            case "Incluir":
                request.setAttribute("venda", venda);
                request.setAttribute("msgError", "");
                request.setAttribute("acao", acao);

                rd = request.getRequestDispatcher("/views/vandas/formVendas.jsp");
                rd.forward(request, response);
        }

    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        int id = Integer.parseInt(request.getParameter("id"));
        //System.out.println(id);
        int quantidade_venda = Integer.parseInt(request.getParameter("quantidade_venda"));
        Date data_venda;
        try {
            data_venda = dateFormat.parse(request.getParameter("data_venda"));
        } catch (ParseException ex) {
            Logger.getLogger(RegistroVenda.class.getName()).log(Level.SEVERE, null, ex);
        }
        float valor_venda = Float.parseFloat(request.getParameter("valor_venda"));
        int id_cliente = Integer.parseInt(request.getParameter("id_cliente"));
        int id_produto = Integer.parseInt(request.getParameter("id_produto"));
        int id_funcionario = Integer.parseInt(request.getParameter("id_funcionario"));
        String btnEnviar = request.getParameter("btnEnviar");

        RequestDispatcher rd;

        if (data_venda == null ||quantidade_venda == -1 || valor_venda == -1.0 ||id_cliente == -1 ||id_produto == -1 ) {
            Venda venda = new Venda();
            switch (btnEnviar) {
                case "Alterar":
                case "Excluir":
                    try {
                    VendaDAO vendaDAO = new VendaDAO();
                    venda = vendaDAO.getVenda(id);

                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                    throw new RuntimeException("Falha em uma query para cadastro de Venda");
                }
                break;
            }

            request.setAttribute("venda", venda);
            request.setAttribute("acao", btnEnviar);

            request.setAttribute("msgError", "É necessário preencher todos os campos");

            rd = request.getRequestDispatcher("/views/vendas/formVendas.jsp");
            rd.forward(request, response);

        } else {
            
             Venda venda = new Venda(quantidade_venda,data_venda,valor_venda,id_cliente,id_produto,id_funcionario);
             VendaDAO vendaDAO = new VendaDAO();
             venda.setId(id);

            try {
                switch (btnEnviar) {
                    case "Incluir":
                        vendaDAO.Inserir(venda);
                        request.setAttribute("msgOperacaoRealizada", "Inclusão realizada com sucesso");
                        break;
                    case "Alterar":
                        vendaDAO.Alterar(venda);
                        request.setAttribute("msgOperacaoRealizada", "Alteração realizada com sucesso");
                        break;
                    case "Excluir":
                        vendaDAO.Excluir(venda);
                        request.setAttribute("msgOperacaoRealizada", "Exclusão realizada com sucesso");
                        break;
                }

                request.setAttribute("link", "/aplicacaoMVC/RegistroVenda?acao=Listar");
                rd = request.getRequestDispatcher("/views/comum/showMessage.jsp");
                rd.forward(request, response);

            } catch (IOException | ServletException ex) {
                System.out.println(ex.getMessage());
                throw new RuntimeException("Falha em uma query para cadastro de Venda");
            } catch (Exception ex) {
                Logger.getLogger(RegistroVenda.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
