package br.ufc.banco.dados;

import java.util.ArrayList;

import br.ufc.banco.conta.ContaAbstrata;
import br.ufc.banco.dados.excecoes.CEException;
import br.ufc.banco.dados.excecoes.CIException;

public class ArrayContas implements IRepositorioContas {

	private ArrayList<ContaAbstrata> contas = null;

	public ArrayContas() {
		this.contas = new ArrayList<ContaAbstrata>();
	}

	public void apagar(String numero) throws CIException {
		if (this.procurar(numero) != null) {
			for (ContaAbstrata conta : contas) {
				if(conta.obterNumero().equals(numero)) {
					contas.remove(conta);
				}
			}
		} else {
			throw new CIException(numero);
		}
	}

	public void inserir(ContaAbstrata conta) throws CEException {
		if (this.procurar(conta.obterNumero()) == null) {
			this.contas.add(conta);
		} else {
			throw new CEException(conta.obterNumero());
		}
	}

	public ContaAbstrata[] listar() {
		ContaAbstrata[] lista = null;
		if (!contas.isEmpty()) {
			lista = new ContaAbstrata[this.contas.size()];
			int i = 0;
			for (ContaAbstrata conta : this.contas) {
				lista[i++] = conta;
			}
		}
		return lista;
	}

	public int numeroContas() {
		return contas.size();
	}

	public ContaAbstrata procurar(String numero) {
		if (!contas.isEmpty()) {
			for (ContaAbstrata conta : contas) {
				if (conta.obterNumero().equals(numero)) {
					return conta;
				}
			}
		}
		return null;
	}
}
