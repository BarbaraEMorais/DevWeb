<%@page contentType="text/html" pageEncoding="UTF-8" import="entidade.Usuario" %>
<nav class="navbar navbar-expand-lg navbar-light bg-light p-3">
    <div class="container-fluid d-flex align-items-baseline">
        <a class="navbar-brand" href="/aplicacaoMVC/home">Home</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse justify-content-end" id="navbarNavAltMarkup">
            <div class="navbar-nav">
                <%
                    // testar se está logado
                    HttpSession sessao = request.getSession(false);
                    if (sessao != null) {
                        Usuario usuarioLogado = (Usuario) session.getAttribute("usuario");
                        if (usuarioLogado != null) { %>
                            <a class="nav-link" href="/aplicacaoMVC/admin/dashboard">Dashboard</a>                            
                            
                            <% if("2".equals(usuarioLogado.getPapel())){%>
                                <a class="nav-link" href="/aplicacaoMVC/admin/CategoriaController?acao=Listar">Categorias</a>
                                <a class="nav-link" href="/aplicacaoMVC/RegistroFornecedor?acao=Listar">Fornecedores</a>                       
                                <a class="nav-link" href="/aplicacaoMVC/RegistroCompra?acao=Listar">Compras</a>
                                <a class="nav-link" href="/aplicacaoMVC/RegistroProduto?acao=Listar">Produtos</a>

                            <%}%>
                            
                            <% if("1".equals(usuarioLogado.getPapel())){%>
                                <a class="nav-link" href="/aplicacaoMVC/RegistroCliente?acao=Listar">Clientes</a>                       
                                <a class="nav-link" href="/aplicacaoMVC/RegistroVenda?acao=Listar">Vendas</a>                       

                            <%}
                            %>
                            <%          if("0".equals(usuarioLogado.getPapel())){ %>                          
                                <a class="nav-link" href="/aplicacaoMVC/RegistrarController?acao=Listar">Funcionários</a>
                                <a class="nav-link" href="/aplicacaoMVC/RelatorioController">Relatório Estoque</a>
                                <a class="nav-link" href="/aplicacaoMVC/RelatorioVendaController">Relatório Venda</a>

                <%    }%>
                <a class="nav-link" href="/aplicacaoMVC/admin/logOut">Logout</a>

                <%} else { %>
                
                <!-- comment  <a class="nav-link" href="/aplicacaoMVC/MostrarComentarios">Coment&aacute;rios</a>-->
                            <a class="nav-link" href="/aplicacaoMVC/AutenticaController?acao=Login">Login</a>
                <%    }
                    }%>



            </div>
        </div>
    </div>
</nav>
