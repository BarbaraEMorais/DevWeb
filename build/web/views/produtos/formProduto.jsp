<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entidade.Produto"%>

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

                <h3>Registro de Produto</h3>
                <%
                        Produto produto = (Produto) request.getAttribute("produto");
                        String acao = (String) request.getAttribute("acao");
                        switch (acao) {
                            case "Incluir":
                                out.println("<h1>Incluir produto</h1>");
                                break;
                            case "Alterar":
                                out.println("<h1>Alterar produto</h1>");
                                break;
                            case "Excluir":
                                out.println("<h1>Excluir produto</h1>");
                                break;
                        }
                    String msgError = (String) request.getAttribute("msgError");
                    if ((msgError != null) && (!msgError.isEmpty())) {%>
                <div class="alert alert-danger" role="alert">
                    <%= msgError%>
                </div><%}%>
                <form action="/aplicacaoMVC/RegistroProduto" method="POST">
                    <input type="hidden" name="id" value="<%=produto.getId()%>" class="form-control">
                    <div class="mb-3">
                        <label for="nome_produto" class="form-label">Nome Produto</label>
                        <input type="text" name="nome_produto" <%= acao.equals("Excluir") ? "Readonly" : ""%> value="<%=produto.getNome_produto()%>" class="form-control" placeholder="Seu papel">
                    
                        <label for="descricao" class="form-label">Descrição</label>
                        <input type="text" name="descricao" <%= acao.equals("Excluir") ? "Readonly" : ""%> value="<%=produto.getDescricao()%>" class="form-control" placeholder="Seu nome">
                    
                        <label for="preco_compra" class="form-label">Preco da Compra</label>
                        <input type="text" name="preco_compra" <%= acao.equals("Excluir") ? "Readonly" : ""%> class="form-control" value="<%=produto.getPreco_compra()%>" placeholder="999.999.999-99">
                    
                        <label for="preco_venda" class="form-label">Preco Venda</label>
                        <input type="text" name="preco_venda" <%= acao.equals("Excluir") ? "Readonly" : ""%> class="form-control" value="<%=produto.getPreco_venda()%>" placeholder="999.999.999-99">
                    
                        <label for="quantidade_disponivel" class="form-label">Quantidade Dísponivel</label>
                        <input type="text" name="quantidade_disponivel" <%= acao.equals("Excluir") ? "Readonly" : ""%> class="form-control" value="<%=produto.getQuantidade_disponivel()%>" placeholder="999.999.999-99">
                    
                        <label for="liberado_venda" class="form-label">Liberado para Venda</label>
                        <input type="text" name="liberado_venda" <%= acao.equals("Excluir") ? "Readonly" : ""%> class="form-control" value="<%=produto.getLiberado_venda()%>" placeholder="999.999.999-99">
                    
                        <label for="id_categoria" class="form-label">ID Categoria</label>
                        <input type="text" name="id_categoria" <%= acao.equals("Excluir") ? "Readonly" : ""%> value="<%=produto.getId_categoria()%>" class="form-control">
                                       
                    </div>
                    <div class="row">
                        <div class="col-sm-2">
                            <input type="submit" name="btnEnviar" value="<%=acao%>" class="btn btn-primary">  
                            <a href="/aplicacaoMVC/RegistroProduto?acao=Listar" class="btn btn-danger">Retornar</a>

                        </div>
                    </div>                                           
                </form> 
            </div>
        </div>
        <script src="views/bootstrap/bootstrap.bundle.min.js"></script>
    </body>
</html>

