<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entidade.Usuario"%>

<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" href="#">
        <title>Exemplo MVC</title>
        <link href="views/bootstrap/bootstrap.min.css"  rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <jsp:include page="../comum/menu.jsp" />
            <div class="col-sm-6 offset-3 mt-5">

                <h3>Registro de Usuario</h3>
                <%
                        Usuario usuario = (Usuario) request.getAttribute("usuario");
                        String acao = (String) request.getAttribute("acao");
                        switch (acao) {
                            case "Incluir":
                                out.println("<h3>Incluir Usuario</h3>");
                                break;
                            case "Alterar":
                                out.println("<h3>Alterar Usuario</h3>");
                                break;
                            case "Excluir":
                                out.println("<h3>Excluir Usuario</h3>");
                                break;
                        }
                    String msgError = (String) request.getAttribute("msgError");
                    if ((msgError != null) && (!msgError.isEmpty())) {%>
                <div class="alert alert-danger" role="alert">
                    <%= msgError%>
                </div><%}%>
                <form action="/aplicacaoMVC/RegistrarController" method="POST">
                    <input type="hidden" name="id" value="<%=usuario.getId()%>" class="form-control">
                    <div class="mb-3">
                        <label for="papel" class="form-label">Papel</label>                      
                        <input required type="text" name="papel" <%= acao.equals("Excluir") ? "Readonly" : ""%> value="<%=usuario.getPapel()%>" class="form-control" >
                    
                        <label for="cpf" class="form-label">Nome</label>
                        <input required type="text" name="nome" <%= acao.equals("Excluir") ? "Readonly" : ""%> value="<%=usuario.getNome()%>" class="form-control" >
                    
                        <label for="cpf" class="form-label">CPF</label>
                        <input required type="text" name="cpf" <%= acao.equals("Excluir") ? "Readonly" : ""%> class="form-control" value="<%=usuario.getCpf()%>" >
                    
                        <label for="senha" class="form-label">Senha</label>
                        <input required type="password" name="senha" <%= acao.equals("Excluir") ? "Readonly" : ""%> value="<%=usuario.getSenha()%>" class="form-control">
                    
                        <label for="senha" class="form-label">Redigite a senha</label>
                        <input required type="password" name="senha2" value="" class="form-control">
                    </div>
                    <div class="row">
                        <div class="col-lm-3">
                            <input type="submit" name="btnEnviar" value="<%=acao%>" class="btn btn-primary">  
                            <a href="/aplicacaoMVC/RegistrarController?acao=Listar" class="btn btn-danger">Retornar</a>

                        </div>
                    </div>                                           
                </form> 
            </div>
        </div>
        <script src="views/bootstrap/bootstrap.bundle.min.js"></script>
    </body>
</html>

