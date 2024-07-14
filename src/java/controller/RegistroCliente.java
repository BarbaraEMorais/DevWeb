//package controller;*
import entidade.Cliente;
import java.util.ArrayList;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.ClienteDAO;

/**
 *
 * @author Leonardo
 */
@WebServlet(name = "RegistroCliente", urlPatterns = {"/RegistroCliente"})
public class RegistroCliente extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

//        RequestDispatcher rd;
//        rd = request.getRequestDispatcher("/views/registro/formRegistro.jsp");
//        rd.forward(request, response);
        
        String acao = (String) request.getParameter("acao");
        Cliente cliente = new Cliente();
        ClienteDAO clienteDAO = new ClienteDAO();
        RequestDispatcher rd;
        switch (acao) {
            case "Listar":
                ArrayList<Cliente> listaClientes = clienteDAO.ListaDeClientes();
                request.setAttribute("listaClientes", listaClientes);

                rd = request.getRequestDispatcher("/views/clientes/listaClientes.jsp");
                rd.forward(request, response);

                break;
            case "Alterar":
            case "Excluir":

                // get parametro ação indicando sobre qual usuário será a ação
                int id = Integer.parseInt(request.getParameter("id"));
            {
                try {
                    cliente = clienteDAO.getCliente(id);
                } catch (Exception ex) {
                    Logger.getLogger(RegistrarController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

                request.setAttribute("cliente", cliente);
                request.setAttribute("msgError", "");
                request.setAttribute("acao", acao);

                rd = request.getRequestDispatcher("/views/clientes/formClientes.jsp");
                rd.forward(request, response);
                break;

            case "Incluir":
                request.setAttribute("cliente", cliente);
                request.setAttribute("msgError", "");
                request.setAttribute("acao", acao);

                rd = request.getRequestDispatcher("/views/clientes/formClientes.jsp");
                rd.forward(request, response);
        }

    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        String nome = request.getParameter("nome");
        String cpf = request.getParameter("cpf");
        String endereco = request.getParameter("endereco");
        String bairro = request.getParameter("bairro");
        String cidade = request.getParameter("cidade");
        String uf = request.getParameter("uf");
        String cep = request.getParameter("cep");
        String telefone = request.getParameter("telefone");
        String email = request.getParameter("email");
        String btnEnvia = request.getParameter("btnEnvia");

        RequestDispatcher rd;

        if (nome.isEmpty() || cpf.isEmpty() || endereco.isEmpty() || bairro.isEmpty() || cidade.isEmpty() || uf.isEmpty() || cep.isEmpty() || telefone.isEmpty() || email.isEmpty()) {
            Cliente cliente = new Cliente();
            switch (btnEnvia) {
                case "Alterar":
                case "Excluir":
                    try {
                    ClienteDAO clienteDAO = new ClienteDAO();
                    cliente = clienteDAO.getCliente(id);

                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                    throw new RuntimeException("Falha em uma query para cadastro de Cliente");
                }
                break;
            }

            request.setAttribute("cliente", cliente);
            request.setAttribute("acao", btnEnvia);

            request.setAttribute("msgError", "É necessário preencher todos os campos");

            rd = request.getRequestDispatcher("/views/clientes/formClientes.jsp");
            rd.forward(request, response);

        } else {
            
             Cliente cliente = new Cliente(nome, cpf, endereco, bairro, cidade, uf, cep, telefone, email);
             ClienteDAO clienteDAO = new ClienteDAO();
             cliente.setId(id);

            try {
                switch (btnEnvia) {
                    case "Incluir":
                        clienteDAO.Inserir(cliente);
                        request.setAttribute("msgOperacaoRealizada", "Inclusão realizada com sucesso");
                        break;
                    case "Alterar":
                        clienteDAO.Alterar(cliente);
                        request.setAttribute("msgOperacaoRealizada", "Alteração realizada com sucesso");
                        break;
                    case "Excluir":
                        clienteDAO.Excluir(cliente);
                        request.setAttribute("msgOperacaoRealizada", "Exclusão realizada com sucesso");
                        break;
                }

                request.setAttribute("link", "/aplicacaoMVC/RegistroCliente?acao=Listar");
                rd = request.getRequestDispatcher("/views/comum/showMessage.jsp");
                rd.forward(request, response);

            } catch (IOException | ServletException ex) {
                System.out.println(ex.getMessage());
                throw new RuntimeException("Falha em uma query para cadastro de cliente");
            } catch (Exception ex) {
                Logger.getLogger(RegistrarController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
