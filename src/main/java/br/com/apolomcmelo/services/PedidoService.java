package br.com.apolomcmelo.services;

import java.util.Date;
import java.util.List;

import br.com.apolomcmelo.daos.ProdutoDAO;
import br.com.apolomcmelo.models.Pedido;

public class PedidoService {
	private ProdutoDAO dao = new ProdutoDAO();
	
	/**
	 * Cadastra uma lista de pedidos
	 * 
	 * @param pedidos
	 */
	public void cadastrarListaPedidos(List<Pedido> pedidos) {
		dao.saveAll(pedidos);
	}

	/**
	 * Retorna uma lista de pedidos baseada nos parâmetros informados
	 * 
	 * @param numeroControle
	 * @param dataCadastro
	 * @return
	 */
	public List<Pedido> listarPedidos(Long numeroControle, Date dataCadastro) {
		return dao.findByParams(numeroControle, dataCadastro);
	}
}
