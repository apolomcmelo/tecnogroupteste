package br.com.apolomcmelo.daos;

import java.util.Date;
import java.util.List;

import br.com.apolomcmelo.models.Pedido;

public interface PedidoDAO {

	/**
	 * Retorna uma lista de Pedidos baseada nos parâmetros informados
	 * @param numeroControle
	 * @param dataCadastro
	 * @return
	 */
	public List<Pedido> findByParams(Long numeroControle, Date dataCadastro);
}