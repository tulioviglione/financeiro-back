package com.financeiro.api.services;

import java.util.List;

import com.financeiro.api.dtos.CaixaDTO;
import com.financeiro.api.exceptions.BusinessException;

public interface CaixaService {

	/**
	 * Cadastra novo caixa
	 * 
	 * @param dto
	 */
	CaixaDTO cadastrarCaixa(CaixaDTO dto);

	/**
	 * Altera registro caixa
	 * 
	 * @param dto
	 * @return
	 * @throws BusinessException
	 */
	CaixaDTO alterarCaixa(CaixaDTO dto) throws BusinessException;

	/**
	 * habilita o caixa
	 * 
	 * @param idCaixa
	 * @param idUsuario
	 * @throws BusinessException
	 * @return
	 */
	String habilitarCaixa(Long idCaixa, Long idUsuario) throws BusinessException;

	/**
	 * desabilita o caixa
	 * 
	 * @param idCaixa
	 * @param idUsuario
	 * @throws BusinessException
	 * @return
	 */
	String desabilitarCaixa(Long idCaixa, Long idUsuario) throws BusinessException;

	/**
	 * lista os caixa pelo ID do Usuario
	 * 
	 * @param idUsuario
	 * @return
	 */
	List<CaixaDTO> findCaixaByIdUsuario(Long idUsuario);

	/**
	 * lista os caixas ativos pelo ID do Usuario
	 * 
	 * @param idUsuario
	 * @return
	 */
	List<CaixaDTO> findActiveCaixaByIdUsuario(Long idUsuario);
}