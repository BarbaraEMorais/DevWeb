<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entidade.Compra"%>

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

                <h3>Registro de Compras</h3>
                <%
                        Compra compra = (Compra) request.getAttribute("compra");
                        String acao = (String) request.getAttribute("acao");
                        switch (acao) {
                            case "Incluir":
                                out.println("<h4>Incluir Compra</h4>");
                                break;
                            case "Alterar":
                                out.println("<h4>Alterar Compra</h4>");
                                break;
                            case "Excluir":
                                out.println("<h4>Excluir Compra</h4>");
                                break;
                        }
                    String msgError = (String) request.getAttribute("msgError");
                    if ((msgError != null) && (!msgError.isEmpty())) {%>
                <div class="alert alert-danger" role="alert">
                    <%= msgError%>
                </div><%}%>
                <form action="/aplicacaoMVC/RegistroCompra" method="POST">
                    <input type="hidden" name="id" value="<%=compra.getId()%>" class="form-control">
                    <div class="mb-3">
                        <label for="quantidade_compra" class="form-label">Quantidade de Compras</label>
                        <input required type="number" name="quantidade_compra" <%= acao.equals("Excluir") ? "Readonly" : ""%> value="<%=compra.getQuantidade_compra()%>" class="form-control">
                    
                        <label for="data_compra" class="form-label">Data da Compra</label>
                        <input required type="text" name="data_compra" <%= acao.equals("Excluir") ? "Readonly" : ""%> value="<%=compra.getData_compra()%>" class="form-control" >
                    
                        <label for="valor_compra" class="form-label">Valor</label>
                        <input required type="number" name="valor_compra" <%= acao.equals("Excluir") ? "Readonly" : ""%> class="form-control" value="<%=compra.getValor_compra()%>" >
                    
                        <label for="id_fornecedor" class="form-label">ID Fornecedor</label>
                        <input required type="number" name="id_fornecedor" <%= acao.equals("Excluir") ? "Readonly" : ""%> value="<%=compra.getId_fornecedor()%>" class="form-control">
                    
                        <label for="id_produto" class="form-label">ID produto</label>
                        <input required type="number" name="id_produto" <%= acao.equals("Excluir") ? "Readonly" : ""%> value="<%=compra.getId_produto()%>" class="form-control">
                    
                        <label for="id_funcionario" class="form-label">ID funcionario</label>
                        <input required type="number" name="id_funcionario" <%= acao.equals("Excluir") ? "Readonly" : ""%> value="<%=compra.getId_funcionario()%>" class="form-control">
                    
                    </div>
                    <div class="row">
                        <div class="col-sm-2">
                            <input type="submit" name="btnEnviar" value="<%=acao%>" class="btn btn-primary">  
                            <a href="/aplicacaoMVC/RegistroCompra?acao=Listar" class="btn btn-danger">Retornar</a>

                        </div>
                    </div>                                           
                </form> 
            </div>
        </div>
        <script src="views/bootstrap/bootstrap.bundle.min.js"></script>
    </body>
</html>

