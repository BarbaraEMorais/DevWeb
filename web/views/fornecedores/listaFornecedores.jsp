<%@page import="entidade.Fornecedor"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" href="#">
        <title>Lista Registros</title>
         <link href="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.min.css"  rel="stylesheet">

    </head>
    <body>
        <div class="container">
             <jsp:include page="../comum/menu.jsp" />
            <div class="mt-5">

                <h1>Área Restrita</h1>
                <h2>Lista de Fornecedores</h2>

                <a href="/aplicacaoMVC/RegistroFornecedor?acao=Incluir" class="mb-2 btn btn-primary">Incluir</a>
                <div class="table-responsive">
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <th scope="col">Nome</th>
                                <th scope="col">CPF</th>
                                <th scope="col">Endereço</th>
                                <th scope="col">Bairro</th>
                                <th scope="col">Cidade</th>
                                <th scope="col">UF</th>
                                <th scope="col">CEP</th>
                                <th scope="col">Telefone</th>
                                <th scope="col">E-mail</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                ArrayList<Fornecedor> listaFornecedores = (ArrayList<Fornecedor>) request.getAttribute("listaFornecedores");
                                             
                                for (Fornecedor fornecedor : listaFornecedores) {
                                                                                                            
                                        out.println("<tr>");
                                        //out.println("<th>" + usuario.getId() + "</th>");
                                        out.println("<td>" + fornecedor.getRazaoSocial()+ "</td>");
                                        out.println("<td>" + fornecedor.getCnpj()+ "</td>");
                                        out.println("<td>" + fornecedor.getEndereco()+ "</td>");
                                        out.println("<td>" + fornecedor.getBairro()+ "</td>");
                                        out.println("<td>" + fornecedor.getCidade()+ "</td>");                                        
                                        out.println("<td>" + fornecedor.getUf()+ "</td>");
                                        out.println("<td>" + fornecedor.getCep()+ "</td>");
                                        out.println("<td>" + fornecedor.getTelefone()+ "</td>");
                                        out.println("<td>" + fornecedor.getEmail()+ "</td>");
                                
                                    
                                    %>
                            <td>
                            <a href="/aplicacaoMVC/RegistroFornecedor?acao=Alterar&id=<%=fornecedor.getId()%>" class="btn btn-warning">Alterar</a>
                            <a href="/aplicacaoMVC/RegistroFornecedor?acao=Excluir&id=<%=fornecedor.getId()%>" class="btn btn-danger">Excluir</a></td>

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

