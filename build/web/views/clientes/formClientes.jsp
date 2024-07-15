<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entidade.Cliente"%>

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

                <h3>Registro de Cliente</h3>
                <%
                        Cliente cliente = (Cliente) request.getAttribute("cliente");
                        String acao = (String) request.getAttribute("acao");
                        switch (acao) {
                            case "Incluir":
                                out.println("<h4>Incluir Cliente</h4>");
                                break;
                            case "Alterar":
                                out.println("<h4>Alterar Cliente</h4>");
                                break;
                            case "Excluir":
                                out.println("<h4>Excluir Cliente</h4>");
                                break;
                        }
                    String msgError = (String) request.getAttribute("msgError");
                    if ((msgError != null) && (!msgError.isEmpty())) {%>
                <div class="alert alert-danger" role="alert">
                    <%= msgError %> 
                </div><%}%>
                <form action="/aplicacaoMVC/RegistroCliente" method="POST">
                    <input type="hidden" name="id" value="<%=cliente.getId()%>" class="form-control">
                    <div class="mb-3">
                        <label for="nome" class="form-label">Nome</label>
                        <input maxlength="50" minlength="3" required type="text" name="nome" <%= acao.equals("Excluir") ? "Readonly" : ""%> value="<%=cliente.getNome()%>" class="form-control" >
                    
                        <label for="cpf" class="form-label">CPF</label>
                        <input maxlength="14" minlength="14" required type="text" name="cpf" <%= acao.equals("Excluir") ? "Readonly" : ""%> value="<%=cliente.getCpf()%>" class="form-control">
                    
                        <label for="endereco" class="form-label">Endereço</label>
                        <input maxlength="50" minlength="3" required type="text" name="endereco" <%= acao.equals("Excluir") ? "Readonly" : ""%> class="form-control" value="<%=cliente.getEndereco()%>" >
                    
                        <label for="bairro" class="form-label">Bairro</label>
                        <input  maxlength="50" minlength="3" required type="text" name="bairro" <%= acao.equals("Excluir") ? "Readonly" : ""%> value="<%=cliente.getBairro()%>" class="form-control">
                    
                        <label for="cidade" class="form-label">Cidade</label>
                        <input maxlength="50" minlength="3" required type="text" name="cidade" <%= acao.equals("Excluir") ? "Readonly" : ""%> value="<%=cliente.getCidade()%>" class="form-control">
                    
                        <label for="uf" class="form-label">UF</label>
                        <input maxlength="2" minlength="2" required type="text" name="uf" <%= acao.equals("Excluir") ? "Readonly" : ""%> value="<%=cliente.getUf()%>" class="form-control">
                    
                        <label for="cep" class="form-label">CEP</label>
                        <input maxlength="9" minlength="3" required type="text" name="cep" <%= acao.equals("Excluir") ? "Readonly" : ""%> value="<%=cliente.getCep()%>" class="form-control">
                    
                        <label for="telefone" class="form-label">Telefone</label>
                        <input maxlength="20" type="text" name="telefone" <%= acao.equals("Excluir") ? "Readonly" : ""%> value="<%=cliente.getTelefone()%>" class="form-control">
                    
                        <label for="email" class="form-label">E-mail</label>
                        <input maxlength="50" type="text" name="email" <%= acao.equals("Excluir") ? "Readonly" : ""%> value="<%=cliente.getEmail()%>" class="form-control">
                    
                    </div>
                    <div class="row">
                        <div class="col-sm-2">
                            <input type="submit" name="btnEnvia" value="<%=acao%>" class="btn btn-primary">  
                            <a href="/aplicacaoMVC/RegistroCliente?acao=Listar" class="btn btn-danger">Retornar</a>

                        </div>
                    </div>                                           
                </form>
            </div>
        </div>
        <script src="views/bootstrap/bootstrap.bundle.min.js"></script>
    </body>
</html>

