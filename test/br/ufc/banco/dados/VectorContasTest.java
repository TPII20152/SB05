package br.ufc.banco.dados;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.ufc.banco.conta.Conta;
import br.ufc.banco.conta.ContaAbstrata;
import br.ufc.banco.dados.excecoes.CEException;
import br.ufc.banco.dados.excecoes.CIException;

public class VectorContasTest {

	private VectorContas vectorContas;
	
	@Before
	public void setUp() throws Exception {
		this.vectorContas = new VectorContas();	
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testInserir() throws CEException {
		vectorContas.inserir(new Conta("11111"));
		assertNotNull(vectorContas);
	}

	@Test
	public void testApagar() throws CEException, CIException {
		vectorContas.inserir(new Conta("11111"));
		vectorContas.inserir(new Conta("22222"));
		vectorContas.apagar("11111");
		
		ContaAbstrata contaAbstrata = vectorContas.procurar("11111");
		assertNull(contaAbstrata);
	}
	
	@Test (expected = CIException.class)
	public void testApagarContaInexistente() throws CIException {
		vectorContas.apagar("9999");
	}
	
	@Test
	public void testProcurarVectorVazio() {
		ContaAbstrata contaAbstrata = vectorContas.procurar("11111");
		assertNull(contaAbstrata);
	}
	
	@Test
	public void testProcurar() throws CEException {
		vectorContas.inserir(new Conta("11111"));
		vectorContas.inserir(new Conta("22222"));
		ContaAbstrata contaAbstrata = vectorContas.procurar("11111");
		assertNotNull(contaAbstrata);
	}
	
	@Test (expected = CIException.class)
	public void testInserirDuplicado() throws CEException {
		vectorContas.inserir(new Conta("11111"));
		vectorContas.inserir(new Conta("11111"));
	}

	@Test
	public void testNumeroContas() throws CEException {
		vectorContas.inserir(new Conta("11111"));
		vectorContas.inserir(new Conta("22222"));
		vectorContas.inserir(new Conta("33333"));
		
		assertEquals(3, vectorContas.numeroContas());
	}
	
	@Test
	public void testNumeroContasComExclusao() throws CEException, CIException {
		vectorContas.inserir(new Conta("11111"));
		vectorContas.inserir(new Conta("22222"));
		vectorContas.inserir(new Conta("33333"));
		vectorContas.apagar("11111");
		vectorContas.inserir(new Conta("44444"));
		
		assertEquals(3, vectorContas.numeroContas());
	}
	
	@Test
	public void testListarContas() throws CEException {
		ContaAbstrata[] lista;
		
		vectorContas.inserir(new Conta("11111"));
		vectorContas.inserir(new Conta("22222"));
		vectorContas.inserir(new Conta("33333"));
		
		lista = vectorContas.listar();
		
		assertEquals(3, lista.length);
	}
	
	@Test
	public void testListarContasVectorVazio() throws CEException {
		ContaAbstrata[] lista;
		lista = vectorContas.listar();
		
		assertNull(lista);
	}
	
}
