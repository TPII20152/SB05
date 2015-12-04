package br.ufc.banco.conta;

import br.ufc.banco.conta.excecoes.SIException;

public class ContaImposto extends ContaAbstrata {

	public ContaImposto(String numero) {
		super(numero);
	}

	public void debitar(double valor) throws SIException {
		if(this.saldo < (valor + (valor * 0.001))){
			this.saldo = this.saldo - (valor + (valor * 0.001));
		}
		else{
			throw new SIException(numero, this.saldo);
		}
	}
}
