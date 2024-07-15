/*package controller;*/
import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import entidade.Produto;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.ProdutoDAO;

/**
 *
 * @author Leonardo
 */
@WebServlet(name = "RegistroProduto", urlPatterns = {"/RegistroProduto"})
public class RegistroProduto extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

//        RequestDispatcher rd;
//        rd = request.getRequestDispatcher("/views/registro/formRegistro.jsp");
//        rd.forward(request, response);
        
        String acao = (String) request.getParameter("acao");
        Produto produto = new Produto();
        ProdutoDAO produtoDAO = new ProdutoDAO();
        RequestDispatcher rd;
        switch (acao) {
            case "Listar":
                ArrayList<Produto> listaProdutos = produtoDAO.ListaDeProdutos();
                request.setAttribute("listaProdutos", listaProdutos);

                rd = request.getRequestDispatcher("/views/produtos/listaProdutos.jsp");
                rd.forward(request, response);

                break;
            case "Alterar":
            case "Excluir":

                // get parametro ação indicando sobre qual usuário será a ação
                int id = Integer.parseInt(request.getParameter("id"));
            {
                try {
                    produto = produtoDAO.getProduto(id);
                } catch (Exception ex) {
                    Logger.getLogger(RegistroProduto.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

                request.setAttribute("produto", produto);
                request.setAttribute("msgError", "");
                request.setAttribute("acao", acao);

                rd = request.getRequestDispatcher("/views/produtos/formProduto.jsp");
                rd.forward(request, response);
                break;

            case "Incluir":
                request.setAttribute("produto", produto);
                request.setAttribute("msgError", "");
                request.setAttribute("acao", acao);

                rd = request.getRequestDispatcher("/views/produtos/formProduto.jsp");
                rd.forward(request, response);
        }

    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                
        int id = Integer.parseInt(request.getParameter("id"));
        System.out.println(id);
        
        String nome_produto = request.getParameter("nome_produto");
        String descricao = request.getParameter("descricao"); 
        float preco_compra = Float.parseFloat(request.getParameter("preco_compra"));
        float preco_venda = Float.parseFloat(request.getParameter("preco_venda"));
        int quantidade_disponivel = Integer.parseInt(request.getParameter("quantidade_disponivel"));
        String liberado_venda =request.getParameter("liberado_venda"); 
        int id_categoria = Integer.parseInt(request.getParameter("id_categoria"));      
        String btnEnviar = request.getParameter("btnEnviar");
        
        System.out.println(quantidade_disponivel);
        System.out.println(descricao);
        System.out.println(nome_produto);
        System.out.println(preco_compra);
        System.out.println(preco_venda);
        System.out.println(id_categoria);

        RequestDispatcher rd;
        
        
        if (nome_produto.isEmpty() || descricao.isEmpty() || preco_compra == -1.0 || preco_venda == -1.0 ||quantidade_disponivel == -1 || liberado_venda.isEmpty() || id_categoria == -1 ) {
            Produto produto = new Produto();
            switch (btnEnviar) {
                case "Alterar":
                case "Excluir":
                    try {
                    ProdutoDAO produtoDAO = new ProdutoDAO();
                    produto = produtoDAO.getProduto(id);

                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                    throw new RuntimeException("Falha em uma query para cadastro de Produto");
                }
                break;
            }

            request.setAttribute("produto", produto);
            request.setAttribute("acao", btnEnviar);

            request.setAttribute("msgError", "É necessário preencher todos os campos");

            rd = request.getRequestDispatcher("/views/produtos/formProduto.jsp");
            rd.forward(request, response);

        } else {
            System.out.println("entrei aqui");
            Produto produto = new Produto(nome_produto, descricao, preco_compra, preco_venda, quantidade_disponivel, liberado_venda, id_categoria);
            ProdutoDAO produtoDAO = new ProdutoDAO();
            produto.setId(id);

            try {
                switch (btnEnviar) {
                    case "Incluir":
                        produtoDAO.Inserir(produto);
                        request.setAttribute("msgOperacaoRealizada", "Inclusão realizada com sucesso");
                        break;
                    case "Alterar":
                        produtoDAO.Alterar(produto);
                        request.setAttribute("msgOperacaoRealizada", "Alteração realizada com sucesso");
                        break;
                    case "Excluir":
                        produtoDAO.Excluir(produto);
                        request.setAttribute("msgOperacaoRealizada", "Exclusão realizada com sucesso");
                        break;
                }

                request.setAttribute("link", "/aplicacaoMVC/RegistroProduto?acao=Listar");
                rd = request.getRequestDispatcher("/views/comum/showMessage.jsp");
                rd.forward(request, response);

            } catch (IOException | ServletException ex) {
                System.out.println(ex.getMessage());
                throw new RuntimeException("Falha em uma query para cadastro de Produto");
            } catch (Exception ex) {
                Logger.getLogger(RegistroProduto.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
