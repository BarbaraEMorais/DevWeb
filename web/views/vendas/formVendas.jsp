<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entidade.Venda"%>

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

                <h3>Registro de Vendas</h3>
                <%
                        Venda venda = (Venda) request.getAttribute("venda");
                        String acao = (String) request.getAttribute("acao");
                        switch (acao) {
                            case "Incluir":
                                out.println("<h4>Incluir Venda</h4>");
                                break;
                            case "Alterar":
                                out.println("<h4>Alterar Venda</h4>");
                                break;
                            case "Excluir":
                                out.println("<h4>Excluir Venda</h4>");
                                break;
                        }
                    String msgError = (String) request.getAttribute("msgError");
                    if ((msgError != null) && (!msgError.isEmpty())) {%>
                <div class="alert alert-danger" role="alert">
                    <%= msgError%>
                </div><%}%>
                <form action="/aplicacaoMVC/RegistroVenda" method="POST">
                    <input type="hidden" name="id" value="<%=venda.getId()%>" class="form-control">
                    <div class="mb-3">
                        <label for="quantidade_venda" class="form-label">Quantidade de vendas</label>
                        <input type="number" name="quantidade_venda" <%= acao.equals("Excluir") ? "Readonly" : ""%> value="<%=venda.getQuantidade_venda()%>" class="form-control" >
                    
                        <label for="data_venda" class="form-label">Data da venda</label>
                        <input type="text" name="data_venda" <%= acao.equals("Excluir") ? "Readonly" : ""%> value="<%=venda.getData_venda()%>" class="form-control" >
                    
                        <label for="valor_venda" class="form-label">Valor</label>
                        <input type="number" name="valor_venda" <%= acao.equals("Excluir") ? "Readonly" : ""%> class="form-control" value="<%=venda.getValor_venda()%>" >
                    
                        <label for="id_cliente" class="form-label">ID cliente</label>
                        <input type="number" name="id_cliente" <%= acao.equals("Excluir") ? "Readonly" : ""%> value="<%=venda.getId_cliente()%>" class="form-control">
                    
                        <label for="id_produto" class="form-label">ID produto</label>
                        <input type="number" name="id_produto" <%= acao.equals("Excluir") ? "Readonly" : ""%> value="<%=venda.getId_produto()%>" class="form-control">
                    
                        <label for="id_funcionario" class="form-label">ID funcionario</label>
                        <input type="number" name="id_funcionario" <%= acao.equals("Excluir") ? "Readonly" : ""%> value="<%=venda.getId_funcionario()%>" class="form-control">
                    
                    </div>
                    <div class="row">
                        <div class="col-sm-2">
                            <input type="submit" name="btnEnviar" value="<%=acao%>" class="btn btn-primary">  
                            <a href="/aplicacaoMVC/RegistroVenda?acao=Listar" class="btn btn-danger">Retornar</a>

                        </div>
                    </div>                                           
                </form> 
            </div>
        </div>
        <script src="views/bootstrap/bootstrap.bundle.min.js"></script>
    </body>
</html>

