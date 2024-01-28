<h1 align=center>🛒 market-manager</h1>
<div align=center>
    <img width=250px src="https://github.com/EdnaldoLuiz/market-manager/assets/112354693/4a2aca7e-eda6-4473-9c6c-f20667c19426">
</div>

<h1>📚 Índice </h1>
<ol>
    <li><a href="#tela-de-registro">Tela de Registro</a></li>
    <li><a href="#tela-esqueceu-a-senha">Tela de Esqueceu a Senha</a></li>
    <li><a href="#tela-funcionarios">Tela de Funcionários</a></li>
    <li><a href="#tela-produtos">Tela de Produtos</a></li>
    <li><a href="#tela-registrar-produtos">Tela de Registrar Produtos</a></li>
    <li><a href="#tela-registrar-funcionarios">Tela de Registrar Funcionários</a></li>
    <li><a href="#executar-o-projeto">Executar o Projeto</a></li>
    <li><a href="#tecnologias-utilizadas">Tecnologias Utilizadas</a></li>
</ol>

<h2 id="tela-de-registro">✏️ Tela de Registro </h2>
<p>Tela de registro onde é necessário ter seu e-mail cadastrado por um Admin e uma senha.</p>
<div align=center>
    <img width=80% src="https://github.com/EdnaldoLuiz/market-manager/assets/112354693/445af718-f84f-4173-8daf-501196afb194">
</div>

<br>

- **Campos de Entrada:** Existem campos para inserir o e-mail e senha do usuário.
- **Botão de Login:** O botão "LOGIN" realiza a tentativa de autenticação com as credenciais inseridas.
- **Redirecionamento:** Após a autenticação bem-sucedida, os usuários são redirecionados para a tela principal, sendo exibida uma visão diferente dependendo do cargo do usuário (Admin ou Funcionário).

<h3>Modal para Admin</h3>
<div align=center>
    <img width=50% src="https://github.com/EdnaldoLuiz/market-manager/assets/112354693/c7ce5974-904a-43b1-87a6-2fd288021c55">
</div>

<br>

- **Modal para Admin:** Em caso de autenticação bem-sucedida, um modal pode ser exibido se o usuário autenticado for um administrador.

<h2 id="tela-esqueceu-a-senha">🔑 Tela de Esqueceu a Senha </h2>
<div align=center>
    <img src="https://github.com/EdnaldoLuiz/market-manager/assets/112354693/e5b6aeca-958f-4288-91e8-1d49c6107596">
</div>

<br>

- **Esqueceu a Senha:** Um link para "Esqueceu a Senha" permite que os usuários solicitem a recuperação de senha.

<h2 id="tela-funcionarios">👥 Tela de Funcionários </h2>

<p>A tela de funcionários permite visualizar, adicionar e excluir membros da equipe do sistema. Principais funcionalidades:</p>

<div align=center>
    <img width=80% src="https://github.com/EdnaldoLuiz/market-manager/assets/112354693/e2c10adb-c422-4a8b-bf10-bb8736af1fdc">
</div>

<br>

- **Adicionar Funcionário:** Clicando no botão "Registrar Funcionário", é possível abrir um formulário para inserir as informações do novo funcionário. Após adição, a tabela é atualizada automaticamente.
- **Excluir Funcionário:** Ao clicar em "Excluir Funcionário", é possível remover um membro da equipe selecionado na tabela.

<h2 id="tela-produtos">🛍️ Tela de Produtos</h2>

<p>A tela de produtos permite visualizar, adicionar e importar produtos no inventário do sistema. Principais funcionalidades:</p>

<div align=center>
    <img width=80% src="https://github.com/EdnaldoLuiz/market-manager/assets/112354693/2e5b82ee-8324-485f-a761-0ca5ca8a793f">
</div>

<br>

- **Ordenar e Filtrar:** É possível selecionar a categoria desejada e ordenar os produtos por preço em ordem crescente ou decrescente, utilizando os menus suspensos correspondentes.
- **Adicionar Produto:** Ao clicar em "Adicionar Produto", é aberto um formulário para inserir informações do novo produto, como nome, preço, quantidade e categoria. Após a adição, a tabela é atualizada automaticamente.
- **Importar Produtos:** Clicando em "Importar Produtos", é possível realizar a importação de produtos para o sistema lendo os dados de um PDF.

<div align=center>
    <img width=400px src="https://github.com/EdnaldoLuiz/market-manager/assets/112354693/86e8672c-8205-45b5-9be6-efa6b4f21abe">
</div>

<h2 id="tela-registrar-produtos">📝 Tela de Registrar Produtos</h2>
<div align=center>
    <img src="https://github.com/EdnaldoLuiz/market-manager/assets/112354693/f152974f-daba-4e48-a40b-70fb5a704f20">
</div>
<br>

- **Campos de Entrada:** Nome, preço, quantidade e categoria do produto.
- **Botão de Adicionar:** Inclui o novo produto na tabela de produtos.
- **Interface Intuitiva:** Design amigável para facilitar o registro de produtos.

<h2 id="tela-registrar-funcionarios">📝 Tela de Registrar Funcionários </h2>
<div align=center>
    <img align=center src="https://github.com/EdnaldoLuiz/market-manager/assets/112354693/0d79223a-b683-45c0-b3bd-260448fd171d">
</div>
<br>

- **Campos de Entrada:** Nome, e-mail, CPF e cargo do funcionário.
- **Botão de Adicionar:** Inclui o novo funcionário na tabela de funcionários.
- **Escolha de Cargo:** Admin ou Funcionário, selecionados através de uma lista suspensa.

<h2 id="executar-o-projeto">▶️ Executar o Projeto</h2>

```bash
git clone https://github.com/EdnaldoLuiz/market-manager.git
cd market-manager
```

> Obs: Precisa ter o JDK instalado e abra em uma IDE Java de sua escolha. Ou você pode executar o market-manager-app.jar disponível 

<h2 id="tecnologias-utilizadas">🛠️ Tecnologias Utilizadas:</h2> 

<table align="center" width=1000px>
    <thead>
        <tr>
            <th><img src="https://skillicons.dev/icons?i=java" width=80px height=80px/></th>
            <th><img src="https://skillicons.dev/icons?i=idea" width=80px height=80px/></th>
            <th><img src="https://skillicons.dev/icons?i=maven" width=80px height=80px/></th>
            <th><img src="https://skillicons.dev/icons?i=postgres" width=80px height=80px/></th>
        </tr>
    </thead>
    <tbody align="center">
        <tr>
            <td>Java</td>
            <td>Intellij</td>
            <td>Maven</td>
            <td>PostgreSQL</td>
        </tr>
        <tr>
            <td>🔖 8</td>
            <td>🔖 2023.3.2</td>
            <td>🔖 3.9.6</td>
            <td>🔖 16.1.1</td>
        </tr>
    </tbody>
</table>
