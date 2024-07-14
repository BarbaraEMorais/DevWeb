/*package controller;*/

import java.util.ArrayList;
import entidade.Usuario;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.UsuarioDAO;

/**
 *
 * @author Leonardo
 */
@WebServlet(name = "RegistrarController", urlPatterns = {"/RegistrarController"})
public class RegistrarController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

//        RequestDispatcher rd;
//        rd = request.getRequestDispatcher("/views/registro/formRegistro.jsp");
//        rd.forward(request, response);
        
        String acao = (String) request.getParameter("acao");
        Usuario usuario = new Usuario();
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        RequestDispatcher rd;
        switch (acao) {
            case "Listar":
                ArrayList<Usuario> listaUsuarios = usuarioDAO.ListaDeUsuarios();
                request.setAttribute("listaUsuarios", listaUsuarios);

                rd = request.getRequestDispatcher("/views/registro/listaUsuarios.jsp");
                rd.forward(request, response);

                break;
            case "Alterar":
            case "Excluir":

                // get parametro ação indicando sobre qual usuário será a ação
                int id = Integer.parseInt(request.getParameter("id"));
            {
                try {
                    usuario = usuarioDAO.getUsuario(id);
                } catch (Exception ex) {
                    Logger.getLogger(RegistrarController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

                request.setAttribute("usuario", usuario);
                request.setAttribute("msgError", "");
                request.setAttribute("acao", acao);

                rd = request.getRequestDispatcher("/views/registro/formRegistro.jsp");
                rd.forward(request, response);
                break;

            case "Incluir":
                request.setAttribute("usuario", usuario);
                request.setAttribute("msgError", "");
                request.setAttribute("acao", acao);

                rd = request.getRequestDispatcher("/views/registro/formRegistro.jsp");
                rd.forward(request, response);
        }

    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        String nome = request.getParameter("nome");
        String cpf = request.getParameter("cpf");
        String papel = request.getParameter("papel");
        String senha = request.getParameter("senha");
        String btnEnviar = request.getParameter("btnEnviar");

        RequestDispatcher rd;

        if (nome.isEmpty() || cpf.isEmpty() || papel.isEmpty() || senha.isEmpty()) {
            Usuario usuario = new Usuario();
            switch (btnEnviar) {
                case "Alterar":
                case "Excluir":
                    try {
                    UsuarioDAO usuarioDAO = new UsuarioDAO();
                    usuario = usuarioDAO.getUsuario(id);

                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                    throw new RuntimeException("Falha em uma query para cadastro de usuario");
                }
                break;
            }

            request.setAttribute("usuario", usuario);
            request.setAttribute("acao", btnEnviar);

            request.setAttribute("msgError", "É necessário preencher todos os campos");

            rd = request.getRequestDispatcher("/views/registro/formRegistro.jsp");
            rd.forward(request, response);

        } else {
            
             Usuario usuario = new Usuario(nome,cpf,papel,senha);
             UsuarioDAO usuarioDAO = new UsuarioDAO();

            try {
                switch (btnEnviar) {
                    case "Incluir":
                        usuarioDAO.Inserir(usuario);
                        request.setAttribute("msgOperacaoRealizada", "Inclusão realizada com sucesso");
                        break;
                    case "Alterar":
                        usuarioDAO.Alterar(usuario);
                        request.setAttribute("msgOperacaoRealizada", "Alteração realizada com sucesso");
                        break;
                    case "Excluir":
                        usuarioDAO.Excluir(usuario);
                        request.setAttribute("msgOperacaoRealizada", "Exclusão realizada com sucesso");
                        break;
                }

                request.setAttribute("link", "/aplicacaoMVC/RegistrarController?acao=Listar");
                rd = request.getRequestDispatcher("/views/comum/showMessage.jsp");
                rd.forward(request, response);

            } catch (IOException | ServletException ex) {
                System.out.println(ex.getMessage());
                throw new RuntimeException("Falha em uma query para cadastro de usuario");
            } catch (Exception ex) {
                Logger.getLogger(RegistrarController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
