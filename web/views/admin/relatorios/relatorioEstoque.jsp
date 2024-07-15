<%@page import="entidade.Produto"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" href="#">
        <title>Relatório de Estoque</title>
         <link href="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.min.css"  rel="stylesheet">

    </head>
    <body>
        <div class="container">
             <jsp:include page="../../comum/menu.jsp" />
            <div class="mt-5">

                <h2>Área Administrativa</h2>
                <h3>Controle de Estoque</h3>

                <div class="table-responsive">
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <th scope="col">Nome do Produto</th>
                                <th scope="col">Descrição</th>
                                <th scope="col">Preço de Compra (R$)</th>
                                <th scope="col">Preço de Venda (R$)</th>
                                <th scope="col">Quantidade Disponível</th>
                                <th scope="col">Liberado para Venda</th>
                                <th scope="col">ID categoria</th>    
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                ArrayList<Produto> listaProdutos = (ArrayList<Produto>) request.getAttribute("listaProdutos");
                                
                                String liberado = "";
                                
                                for (Produto produto : listaProdutos) {
                                
                                    if("S".equals(produto.getLiberado_venda())){
                                        liberado = "Sim";
                                    }else{
                                        liberado = "Não";
                                    }
                                                                                                            
                                        out.println("<tr>");
                                        //out.println("<th>" + usuario.getId() + "</th>");
                                        out.println("<td>" + produto.getNome_produto()+ "</td>");
                                        out.println("<td>" + produto.getDescricao()+ "</td>");
                                        out.println("<td>" + produto.getPreco_compra()+ "</td>");
                                        out.println("<td>" + produto.getPreco_venda()+ "</td>");
                                        out.println("<td>" + produto.getQuantidade_disponivel()+ "</td>");                                        
                                        out.println("<td>" + liberado + "</td>");
                                        out.println("<td>" + produto.getId_categoria()+ "</td>");
                                        out.println("</tr>");
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

