<%@page contentType="text/html" pageEncoding="UTF-8" import="entidade.Usuario" %>

<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" href="#">
        <title>Área Restrita</title>
        <link href="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.min.css"  rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <jsp:include page="../../comum/menu.jsp" />
            <div class="mt-5">

                <h2>Área Restrita</h2>
                <%
                    Usuario usuarioLogado = (Usuario) session.getAttribute("usuario");
                    out.println("<br><br>");
                    out.println("<h4>Nome: " + usuarioLogado.getNome() + "</h4>");
                    if ("0".equals(usuarioLogado.getPapel())){out.println("<h4>Cargo: Administrador</h4");}
                    else if ("1".equals(usuarioLogado.getPapel())){out.println("<h4>Cargo: Vendedor</h4");}
                    else if ("2".equals(usuarioLogado.getPapel())){out.println("<h4>Cargo: Comprador</h4");}
                    
                %>
               
            </div>
        </div>
        <script src="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.bundle.min.js"></script>
    </body>
</html>
