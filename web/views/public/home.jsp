<%@page import="java.util.ArrayList"%>
<%@page import="entidade.Produto"%>
<%@page import="entidade.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" href="#">
        <title>Home</title>
        <link href="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.min.css"  rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <jsp:include page="../comum/menu.jsp"/>
            <div class="mt-5">
                <%Usuario usuarioLogado = (Usuario) session.getAttribute("usuario");
                    if(usuarioLogado==null){
                %>
                <h3>Seja Bem-vindo ao sistema de vendas!</h3>
                <h4>Aqui comentamos sobre vários assuntos.</h4>
                
                <%}%>
                <%  
                    if(usuarioLogado!=null){
                    out.println("<h2>Olá " + usuarioLogado.getNome() + ", seja bem-vindo!</h2>");
                    if ("0".equals(usuarioLogado.getPapel())){
                        out.println("<h4>Cargo: Administrador</h4>");
                        out.println("<p><br><br>Você conseguirá fazer os cadastros de seus funcionários e analisar relatórios de estoque e venda, basta seguir as indicações nos links do menu superior.</p>");
                        
                    }
                    else if ("1".equals(usuarioLogado.getPapel())){out.println("<h4>Cargo: Vendedor</h4");}
                    else if ("2".equals(usuarioLogado.getPapel())){out.println("<h4>Cargo: Comprador</h4");}
                    }
                %>
                
            </div>
        </div>
       <script src="../bootstrap/bootstrap.bundle.min.js"></script>
    </body>
</html>

