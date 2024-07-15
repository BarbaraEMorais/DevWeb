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
             <jsp:include page="../../comum/menu.jsp" />
            <div class="mt-5">

                <h2>Área Administrativa</h2>
                <h3>Controle Vendas</h3>

                <div class="table-responsive">
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <th scope="col">Data da venda</th>
                                <th scope="col">Quantidade de vendas</th>                                
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
                                        out.println("<td>" + venda.getData_venda() + "</td>");
                                        //out.println("<th>" + usuario.getId() + "</th>");
                                        out.println("<td>" + venda.getQuantidade_venda()+ "</td>");
                                        out.println("<td>" + venda.getValor_venda()+ "</td>");
                                        out.println("<td>" + venda.getId_cliente()+ "</td>");
                                        out.println("<td>" + venda.getId_produto()+ "</td>");                                        
                                        out.println("<td>" + venda.getId_funcionario()+ "</td>");                      
                                        out.println("</tr>");
                                }
                            %>

                        </tbody>
                    </table>
                </div>
                <div class="table-responsive">
                    <table class="table table-hover">
                        <thead>
                            <tr>                 
                                <th scope="col">Quantidade de vendas realizadas</th>  
                                <th scope="col">Valor Total de vendas (R$)</th>                                
                                                              
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                float valorTotal = 0;
                                int quantidadeVendida = 0;
                                
                                for (Venda venda : listaVendas) {
                                    
                                    valorTotal += venda.getValor_venda()*venda.getQuantidade_venda();
                                    quantidadeVendida += venda.getQuantidade_venda();
                                    
                                }
                                out.println("<tr>");     
                                out.println("<td>" + quantidadeVendida + "</td>");   
                                out.println("<td>" + valorTotal + "</td>");
                                                             
                                out.println("</tr>");
                            %>

                        </tbody>
                    </table>
                </div>
            </div>
        </div>

        <script src="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.bundle.min.js"></script>

    </body>
</html>

