<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entidade.Fornecedor"%>

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

                <h3>Registro de Fornecedor</h3>
                <%
                        Fornecedor fornecedor = (Fornecedor) request.getAttribute("fornecedor");
                        String acao = (String) request.getAttribute("acao");
                        switch (acao) {
                            case "Incluir":
                                out.println("<h1>Incluir Fornecedor</h1>");
                                break;
                            case "Alterar":
                                out.println("<h1>Alterar Fornecedor</h1>");
                                break;
                            case "Excluir":
                                out.println("<h1>Excluir Fornecedor</h1>");
                                break;
                        }
                    String msgError = (String) request.getAttribute("msgError");
                    if ((msgError != null) && (!msgError.isEmpty())) {%>
                <div class="alert alert-danger" role="alert">
                    <%= msgError %> 
                </div><%}%>
                <form action="/aplicacaoMVC/RegistroFornecedor" method="POST">
                    <input type="hidden" name="id" value="<%=fornecedor.getId()%>" class="form-control">
                    <div class="mb-3">
                        <label for="nome" class="form-label">Nome</label>
                        <input type="text" name="papel" <%= acao.equals("Excluir") ? "Readonly" : ""%> value="<%=fornecedor.getRazaoSocial()%>" class="form-control" placeholder="Seu papel">
                    
                        <label for="cpf" class="form-label">CPF</label>
                        <input type="text" name="nome" <%= acao.equals("Excluir") ? "Readonly" : ""%> value="<%=fornecedor.getCnpj()%>" class="form-control" placeholder="Seu nome">
                    
                        <label for="cpf" class="form-label">Endereço</label>
                        <input type="text" name="cpf" <%= acao.equals("Excluir") ? "Readonly" : ""%> class="form-control" value="<%=fornecedor.getEndereco()%>" placeholder="999.999.999-99">
                    
                        <label for="senha" class="form-label">Bairro</label>
                        <input type="text" name="senha" <%= acao.equals("Excluir") ? "Readonly" : ""%> value="<%=fornecedor.getBairro()%>" class="form-control">
                    
                        <label for="senha" class="form-label">Cidade</label>
                        <input type="text" name="senha" <%= acao.equals("Excluir") ? "Readonly" : ""%> value="<%=fornecedor.getCidade()%>" class="form-control">
                    
                        <label for="senha" class="form-label">UF</label>
                        <input type="text" name="senha" <%= acao.equals("Excluir") ? "Readonly" : ""%> value="<%=fornecedor.getUf()%>" class="form-control">
                    
                        <label for="senha" class="form-label">CEP</label>
                        <input type="text" name="senha" <%= acao.equals("Excluir") ? "Readonly" : ""%> value="<%=fornecedor.getCep()%>" class="form-control">
                    
                        <label for="senha" class="form-label">Telefone</label>
                        <input type="text" name="senha" <%= acao.equals("Excluir") ? "Readonly" : ""%> value="<%=fornecedor.getTelefone()%>" class="form-control">
                    
                        <label for="senha" class="form-label">E-mail</label>
                        <input type="text" name="senha" <%= acao.equals("Excluir") ? "Readonly" : ""%> value="<%=fornecedor.getEmail()%>" class="form-control">
                    
                    </div>
                    <div class="row">
                        <div class="col-sm-2">
                            <input type="submit" name="btnEnvia" value="<%=acao%>" class="btn btn-primary">  
                            <a href="/aplicacaoMVC/RegistroFornecedor?acao=Listar" class="btn btn-danger">Retornar</a>

                        </div>
                    </div>                                           
                </form>
            </div>
        </div>
        <script src="views/bootstrap/bootstrap.bundle.min.js"></script>
    </body>
</html>

