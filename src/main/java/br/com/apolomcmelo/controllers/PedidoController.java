package br.com.apolomcmelo.controllers;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import br.com.apolomcmelo.enums.RequestResponseStatusEnum;
import br.com.apolomcmelo.models.Pedido;
import br.com.apolomcmelo.services.PedidoService;

@Path("/pedido")
public class PedidoController {
	private static final int MAX_LIMIT_ORDERS = 10;
	private PedidoService service = new PedidoService();

	// Serviços expostos
	@GET
	@Path("/listar")
	@Produces("application/json")
	public List<Pedido> listar(@QueryParam("numero_controle") Long numeroControle,
			@QueryParam("data_cadastro") String dataCadastro) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date parsedDataCadastro = (dataCadastro != null && !"".equals(dataCadastro)) ? sdf.parse(dataCadastro)
					: null;

			return service.listarPedidos(numeroControle, parsedDataCadastro);
		} catch (Exception e) {
			throw new WebApplicationException(RequestResponseStatusEnum.INTERNAL_ERROR.getStatus());
		}
	}

	@POST
	@Path("/cadastrar")
	@Consumes("application/json")
	public Response cadastrarListaPedidosJson(List<Pedido> pedidos) {
		return cadastrarListaPedidos(pedidos);
	}

	@POST
	@Path("/cadastrar")
	@Consumes("application/xml")
	public Response cadastrarListaPedidosXml(List<Pedido> pedidos) {
		return cadastrarListaPedidos(pedidos);
	}
	
	private Response cadastrarListaPedidos(List<Pedido> pedidos) {
		if (pedidos != null && pedidos.size() > MAX_LIMIT_ORDERS) {
			return Response.status(RequestResponseStatusEnum.INTERNAL_ERROR.getStatus())
					.entity("Lista contém mais pedidos que o limite: " + MAX_LIMIT_ORDERS).build();
		}

		validarListaPedidos(pedidos);

		try {
			service.cadastrarListaPedidos(pedidos);

			return Response.status(RequestResponseStatusEnum.SUCCESS_OK.getStatus())
					.entity("Pedidos cadastrados com sucesso.").build();
		} catch (Exception e) {
			return Response.status(RequestResponseStatusEnum.INTERNAL_ERROR.getStatus())
					.entity("Erro ao cadastrar lista de pedidos.\n" + e.getLocalizedMessage()).build();
		}
	}
	
	// Métodos para regras de negócio
	/**
	 * Realiza as validações iniciais de todos os pedidos de uma lista, setando
	 * valores default em caso de atributos não preenchidos
	 * 
	 * @param pedidos
	 */
	private void validarListaPedidos(List<Pedido> pedidos) {
		for (int i = 0; i < pedidos.size(); i++) {
			validarPedido(pedidos.get(i));
		}
	}

	/**
	 * Realiza as validações iniciais de um pedido, setando valores default em
	 * caso de atributos não preenchidos
	 * 
	 * @param pedido
	 */
	private void validarPedido(Pedido pedido) {
		// Considera a data atual em caso de a data não ser informada
		if (pedido.getDataCadastro() == null) {
			pedido.setDataCadastro(new Date());
		}

		// Considera 1 como valor default em caso de quantidade de produtos
		// não ser informada
		if (pedido.getQuantidadeProdutos() == null || pedido.getQuantidadeProdutos() == 0) {
			pedido.setQuantidadeProdutos(1);
		}

		calcularValorTotalPedido(pedido);
	}

	/**
	 * Calcula o valor total do pedido, já aplicando desconto se existente
	 * 
	 * @param pedido
	 */
	private void calcularValorTotalPedido(Pedido pedido) {
		double valorTotal = pedido.getValorUnitarioProduto().doubleValue() * pedido.getQuantidadeProdutos();
		valorTotal -= valorTotal * getDesconto(pedido);

		pedido.setValorTotal(new BigDecimal(valorTotal).setScale(2, RoundingMode.HALF_EVEN));
	}

	/**
	 * Retorna a porcentagem de desconto baseado na quantidade de produtos
	 * 
	 * @param pedido
	 */
	private double getDesconto(Pedido pedido) {
		if (pedido.getQuantidadeProdutos() <= 5) {
			return 0d;
		} else if (pedido.getQuantidadeProdutos() < 10) {
			return 0.05;
		} else {
			return 0.1;
		}
	}
}