<%@page import="entidade.Compra"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" href="#">
        <title>Lista Compras</title>
         <link href="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.min.css"  rel="stylesheet">

    </head>
    <body>
        <div class="container">
             <jsp:include page="../comum/menu.jsp" />
            <div class="mt-5">

                <h2>Área Restrita</h2>
                <h3>Lista de Compras</h3>

                <a href="/aplicacaoMVC/RegistroCompra?acao=Incluir" class="mb-2 btn btn-primary">Incluir</a>
                <div class="table-responsive">
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <th scope="col">Quantidade de Compras</th>
                                <th scope="col">Data da Compra</th>
                                <th scope="col">Valor da Compra</th>
                                <th scope="col">ID Fornecedor</th>
                                <th scope="col">ID produto</th>
                                <th scope="col">ID funcionário</th>                                
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                ArrayList<Compra> listaCompras = (ArrayList<Compra>) request.getAttribute("listaCompras");
                                             
                                for (Compra compra : listaCompras) {
                                                                                                            
                                        out.println("<tr>");
                                        //out.println("<th>" + usuario.getId() + "</th>");
                                        out.println("<td>" + compra.getQuantidade_compra()+ "</td>");
                                        out.println("<td>" + compra.getData_compra()+ "</td>");
                                        out.println("<td>" + compra.getValor_compra()+ "</td>");
                                        out.println("<td>" + compra.getId_fornecedor()+ "</td>");
                                        out.println("<td>" + compra.getId_produto()+ "</td>");                                        
                                        out.println("<td>" + compra.getId_funcionario()+ "</td>");                               
                                    
                                    %>
                            <td>
                            <a href="/aplicacaoMVC/RegistroCompra?acao=Alterar&id=<%=compra.getId()%>" class="btn btn-warning">Alterar</a>
                            <a href="/aplicacaoMVC/RegistroCompra?acao=Excluir&id=<%=compra.getId()%>" class="btn btn-danger">Excluir</a></td>

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

