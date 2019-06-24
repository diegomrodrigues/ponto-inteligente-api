package drsolutions.pontointeligente.api.services.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import drsolutions.pontointeligente.api.entities.Lancamento;
import drsolutions.pontointeligente.api.repositories.LancamentoRepository;
import drsolutions.pontointeligente.api.services.LancamentoService;

/**
 * Implementação so serviço LancamentoServiceImpl.
 * 
 * @author Diego M. Rodrigues
 *
 */
@Service
public class LancamentoServiceImpl implements LancamentoService {
	private static final Logger log = LoggerFactory.getLogger(LancamentoServiceImpl.class);

	@Autowired
	LancamentoRepository lancamentoRepository;

	@Override
	public List<Lancamento> buscarPorFuncionarioId(Long funcionarioId) {
		log.info("Buscando uma lista com todos os lançamentos de um determinado funcionário pelo IDl: {}",
				funcionarioId);
		return this.lancamentoRepository.findByFuncionarioId(funcionarioId);
	}

	@Override
	public Page<Lancamento> buscarPorFuncionarioId(Long funcionarioId, PageRequest pageRequest) {
		log.info("Buscando uma lista paginada de lançamentos de um determinado funcionário pelo IDl: {}",
				funcionarioId);
		return this.lancamentoRepository.findByFuncionarioId(funcionarioId, pageRequest);
	}

	@Override
	@Cacheable("lancamentoPorId")
	public Optional<Lancamento> buscarPorId(Long id) {
		log.info("Buscando lançamento pelo IDl: {}", id);
		return Optional.ofNullable(this.lancamentoRepository.findOne(id));
	}

	@Override
	@CachePut("lancamentoPorId")
	public Lancamento persistir(Lancamento lancamento) {
		log.info("Persistindo um lançamento: {}", lancamento);
		return this.lancamentoRepository.save(lancamento);
	}

	@Override
	public void remover(Long id) {
		log.info("Removendo um lançamento pelo IDl: {}", id);
		this.lancamentoRepository.delete(id);
	}
}
