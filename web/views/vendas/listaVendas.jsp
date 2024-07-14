<%@page import="entidade.Venda"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" href="#">
        <title>Lista Vendas</title>
         <link href="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.min.css"  rel="stylesheet">

    </head>
    <body>
        <div class="container">
             <jsp:include page="../comum/menu.jsp" />
            <div class="mt-5">

                <h1>Área Restrita</h1>
                <h2>Lista de Vendas</h2>

                <a href="/aplicacaoMVC/RegistroVenda?acao=Incluir" class="mb-2 btn btn-primary">Incluir</a>
                <div class="table-responsive">
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <th scope="col">Quantidade de vendas</th>
                                <th scope="col">Data da venda</th>
                                <th scope="col">Valor da venda</th>
                                <th scope="col">ID cliente</th>
                                <th scope="col">ID produto</th>
                                <th scope="col">ID funcionário</th>                                
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                ArrayList<Venda> listaVendas = (ArrayList<Venda>) request.getAttribute("listaVendas");
                                             
                                for (Venda venda : listaVendas) {
                                                                                                            
                                        out.println("<tr>");
                                        //out.println("<th>" + usuario.getId() + "</th>");
                                        out.println("<td>" + venda.getQuantidade_venda()+ "</td>");
                                        out.println("<td>" + venda.getData_venda()+ "</td>");
                                        out.println("<td>" + venda.getValor_venda()+ "</td>");
                                        out.println("<td>" + venda.getId_cliente()+ "</td>");
                                        out.println("<td>" + venda.getId_produto()+ "</td>");                                        
                                        out.println("<td>" + venda.getId_funcionario()+ "</td>");                               
                                    
                                    %>
                            <td>
                            <a href="/aplicacaoMVC/RegistroVenda?acao=Alterar&id=<%=venda.getId()%>" class="btn btn-warning">Alterar</a>
                            <a href="/aplicacaoMVC/RegistroVenda?acao=Excluir&id=<%=venda.getId()%>" class="btn btn-danger">Excluir</a></td>

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

