//***************** Cadastro de pedidos ******************//
Conforme solicitado, a API está preparada para cadastrar de 1 a 10 pedidos. Segue exemplo de como a inclusão deve ser feita:

URL: localhost:8080/provatecnogroup/pedido/cadastrar

Parâmetros JSON -> um pedido:
	[
		{
			"numeroControle": 11,
			"codigoCliente": 2,
			"dataCadastro": "2017-04-03",
			"nomeProduto": "Central Multimidia",
			"valorUnitarioProduto": 2659.9
		}
	]

Parâmetros JSON -> mais de um pedido:
	[
		{
			"numeroControle": 12,
			"codigoCliente": 1,
			"dataCadastro": "2017-04-02",
			"nomeProduto": "Bronzina",
			"valorUnitarioProduto": 23.75,
			"quantidadeProdutos": 12
		}, 
		{
			"numeroControle": 13,
			"codigoCliente": 1,
			"nomeProduto": "Biela",
			"valorUnitarioProduto": 358.5,
			"quantidadeProdutos": 6
		},
		{
			"numeroControle": 14,
			"codigoCliente": 2,
			"dataCadastro": "2017-04-10",
			"nomeProduto": "Pneu",
			"valorUnitarioProduto": 637,
			"quantidadeProdutos": 4
		}
	]

Parâmetros XML -> um pedido:
	<?xml version="1.0" encoding="UTF-8"?>
	<collection>
		<pedido>
			<numeroControle>15</numeroControle>
			<codigoCliente>2</codigoCliente>
			<dataCadastro>2017-04-03</dataCadastro>
			<nomeProduto>Central Multimidia</nomeProduto>
			<valorUnitarioProduto>2659.9</valorUnitarioProduto>
			<quantidadeProdutos>1</quantidadeProdutos>
		</pedido>
	</collection>

Parâmetros XML mais de um pedido:
	<?xml version="1.0" encoding="UTF-8"?>
	<collection>
		<pedido>
			<numeroControle>2</numeroControle>
			<codigoCliente>2</codigoCliente>
			<dataCadastro>2017-04-03</dataCadastro>
			<nomeProduto>Brozina</nomeProduto>
			<valorUnitarioProduto>23.75</valorUnitarioProduto>
			<quantidadeProdutos>12</quantidadeProdutos>
		</pedido>
		
		<pedido>
			<numeroControle>3</numeroControle>
			<codigoCliente>5</codigoCliente>
			<dataCadastro/>
			<nomeProduto>Biela</nomeProduto>
			<valorUnitarioProduto>358.5</valorUnitarioProduto>
		</pedido>
	</collection>


//***************** Listagem de pedidos ******************//
Como solicitado, existem "3" tipos de filtros para consulta dos pedidos dos clientes:
	 - Número do pedido (número de controle)
	 - Data de Cadastro
	 - Todos

O filtro "Todos" é o default, ou seja, se nenhum filtro for inserido na url, o comportamento padrão será listar todos os pedidos. Dessa forma, a URL de consulta segue o exemplo abaixo:

Consulta padrão (Filtro TODOS): localhost:8080/provatecnogroup/pedido/listar
Com filtros especificados:		localhost:8080/provatecnogroup/pedido/listar?numero_controle=12&?data_cadastro=2017-04-02

Obs.: A data deve ser enviada no formato yyyy-MM-dd.