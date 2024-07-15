<%@page import="entidade.Usuario"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" href="#">
        <title>Lista Registros</title>
         <link href="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.min.css"  rel="stylesheet">

    </head>
    <body>
        <div class="container">
             <jsp:include page="../comum/menu.jsp" />
            <div class="mt-5">

                <h1>Área Restrita</h1>
                <h2>Lista de Registros</h2>

                <a href="/aplicacaoMVC/RegistrarController?acao=Incluir" class="mb-2 btn btn-primary">Incluir</a>
                <div class="table-responsive">
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <th scope="col">Nome</th>
                                <th scope="col">Cpf</th>
                                <th scope="col">Papel</th>
                                <th scope="col">Ações</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                //falta diferenciar o tipo de lista de acordo com papel
                                ArrayList<Usuario> listaUsuarios = (ArrayList<Usuario>) request.getAttribute("listaUsuarios");
                                
                                String papel = "";
                                
                                for (Usuario usuario : listaUsuarios) {
                                         
                                        
                                        if("0".equals(usuario.getPapel())){
                                            papel = "Administrador";
                                        }
                                        else if("1".equals(usuario.getPapel())){
                                            papel = "Vendedor";
                                        }
                                        else {
                                            papel = "Comprador";
                                        }
                                        
                                        out.println("<tr>");
                                        //out.println("<th>" + usuario.getId() + "</th>");
                                        out.println("<td>" + usuario.getNome()+ "</td>");
                                        out.println("<td>" + usuario.getCpf()+ "</td>");
                                        out.println("<td>" + papel + "</td>");
                                
                                    
                                    %>
                            <td>
                            <a href="/aplicacaoMVC/RegistrarController?acao=Alterar&id=<%=usuario.getId()%>" class="btn btn-warning">Alterar</a>
                            <a href="/aplicacaoMVC/RegistrarController?acao=Excluir&id=<%=usuario.getId()%>" class="btn btn-danger">Excluir</a></td>

                            <%   out.println("</tr>");
                                }
                            %>

                        </tbody>
                    </table>
                </div>
            </div>
        </div>

        <script src="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.bundle.min.js"></script>

    </body>
</html>

