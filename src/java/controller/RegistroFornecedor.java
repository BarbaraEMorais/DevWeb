/*package controller;*/

import java.util.ArrayList;
import entidade.Fornecedor;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.FornecedorDAO;

/**
 *
 * @author Bárbara
 */
@WebServlet(name = "RegistroFornecedor", urlPatterns = {"/RegistroFornecedor"})
public class RegistroFornecedor extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

//        RequestDispatcher rd;
//        rd = request.getRequestDispatcher("/views/registro/formRegistro.jsp");
//        rd.forward(request, response);
        
        String acao = (String) request.getParameter("acao");
        Fornecedor fornecedor = new Fornecedor();
        FornecedorDAO fornecedorDAO = new FornecedorDAO();
        RequestDispatcher rd;
        switch (acao) {
            case "Listar":
                ArrayList<Fornecedor> listaFornecedores = fornecedorDAO.ListaDeFornecedores();
                System.out.println(listaFornecedores);
                request.setAttribute("listaFornecedores", listaFornecedores);

                rd = request.getRequestDispatcher("/views/fornecedores/listaFornecedores.jsp");
                rd.forward(request, response);

                break;
            case "Alterar":
            case "Excluir":

                // get parametro ação indicando sobre qual usuário será a ação
                int id = Integer.parseInt(request.getParameter("id"));
            {
                try {
                    fornecedor = fornecedorDAO.getFornecedor(id);
                } catch (Exception ex) {
                    Logger.getLogger(RegistrarController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

                request.setAttribute("fornecedor", fornecedor);
                request.setAttribute("msgError", "");
                request.setAttribute("acao", acao);

                rd = request.getRequestDispatcher("/views/fornecedores/formFornecedor.jsp");
                rd.forward(request, response);
                break;

            case "Incluir":
                request.setAttribute("fornecedor", fornecedor);
                request.setAttribute("msgError", "");
                request.setAttribute("acao", acao);

                rd = request.getRequestDispatcher("/views/fornecedores/formFornecedor.jsp");
                rd.forward(request, response);
        }

    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        //System.out.println(id);
        String razao_social = request.getParameter("razao_social");
        String cnpj = request.getParameter("cnpj");
        String endereco = request.getParameter("endereco");
        String bairro = request.getParameter("bairro");
        String cidade = request.getParameter("cidade");
        String uf = request.getParameter("uf");
        String cep = request.getParameter("cep");
        String telefone = request.getParameter("telefone");
        String email = request.getParameter("email");
        String btnEnviar = request.getParameter("btnEnviar");

        RequestDispatcher rd;

        if (razao_social.isEmpty() || cnpj.isEmpty() || endereco.isEmpty() || bairro.isEmpty() || cidade.isEmpty() || uf.isEmpty() || cep.isEmpty() || telefone.isEmpty() || email.isEmpty()) {
            Fornecedor fornecedor = new Fornecedor();
            switch (btnEnviar) {
                case "Alterar":
                case "Excluir":
                    try {
                    FornecedorDAO fornecedorDAO = new FornecedorDAO();
                    fornecedor = fornecedorDAO.getFornecedor(id);

                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                    throw new RuntimeException("Falha em uma query para cadastro de Fornecedor");
                }
                break;
            }

            request.setAttribute("fornecedor", fornecedor);
            request.setAttribute("acao", btnEnviar);

            request.setAttribute("msgError", "É necessário preencher todos os campos");

            rd = request.getRequestDispatcher("/views/fornecedores/formFornecedor.jsp");
            rd.forward(request, response);

        } else {
            
             Fornecedor fornecedor = new Fornecedor(razao_social, cnpj, endereco, bairro, cidade, uf, cep, telefone, email);
             FornecedorDAO fornecedorDAO = new FornecedorDAO();
             fornecedor.setId(id);

            try {
                switch (btnEnviar) {
                    case "Incluir":
                        fornecedorDAO.Inserir(fornecedor);
                        request.setAttribute("msgOperacaoRealizada", "Inclusão realizada com sucesso");
                        break;
                    case "Alterar":
                        fornecedorDAO.Alterar(fornecedor);
                        request.setAttribute("msgOperacaoRealizada", "Alteração realizada com sucesso");
                        break;
                    case "Excluir":
                        fornecedorDAO.Excluir(fornecedor);
                        request.setAttribute("msgOperacaoRealizada", "Exclusão realizada com sucesso");
                        break;
                }

                request.setAttribute("link", "/aplicacaoMVC/RegistroFornecedor?acao=Listar");
                rd = request.getRequestDispatcher("/views/comum/showMessage.jsp");
                rd.forward(request, response);

            } catch (IOException | ServletException ex) {
                System.out.println(ex.getMessage());
                throw new RuntimeException("Falha em uma query para cadastro de Fornecedor");
            } catch (Exception ex) {
                Logger.getLogger(RegistrarController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
