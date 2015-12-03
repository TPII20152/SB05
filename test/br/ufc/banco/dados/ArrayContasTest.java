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

public class ArrayContasTest {

	ArrayContas array;
	
	@Before
	public void setUp() throws Exception {
		array = new ArrayContas();
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testProcurarArrayVazio() {
		ContaAbstrata c = array.procurar("0001");
		assertNull(c);
	}
	
	@Test
	public void testInserir() throws CEException {
		array.inserir(new Conta("0001"));
		
		ContaAbstrata c = array.procurar("0001");
		assertNotNull(c);
	}
	
	@Test (expected = CEException.class)
	public void testInserirDuplicado() throws CEException {
		array.inserir(new Conta("0001"));
		array.inserir(new Conta("0001"));
	}
	
	@Test (expected = CIException.class)
	public void testApagarContaInexistente() throws CIException {
		array.apagar("0001");
	}
	
	@Test
	public void testApagarConta() throws CIException, CEException {
		array.inserir(new Conta("0001"));
		array.inserir(new Conta("0002"));
		array.apagar("0001");
		
		ContaAbstrata c = array.procurar("0001");
		assertNull(c);
	}
	
	@Test
	public void testNumeroContas() throws CEException {
		array.inserir(new Conta("0001"));
		array.inserir(new Conta("0002"));
		array.inserir(new Conta("0003"));
		
		assertEquals(3, array.numeroContas());
	}
	
	@Test
	public void testNumeroContasComExclusao() throws CEException, CIException {
		array.inserir(new Conta("0001"));
		array.inserir(new Conta("0002"));
		array.inserir(new Conta("0003"));
		array.apagar("0002");
		array.inserir(new Conta("0004"));
		
		assertEquals(3, array.numeroContas());
	}

	@Test
	public void testLimiteQtdContas() throws CEException {
		int numConta = 0;

		for(int i = 0; i < 201; i++) {
			array.inserir(new Conta(new Integer(numConta+i).toString()));
		}
		
		assertEquals(201, array.numeroContas());
	}
	
	@Test
	public void testListarContas() throws CEException {
		ContaAbstrata[] lista;
		
		array.inserir(new Conta("0001"));
		array.inserir(new Conta("0002"));
		array.inserir(new Conta("0003"));
		
		lista = array.listar();
		
		assertEquals(3, lista.length);
	}
	
	@Test
	public void testListarContasArrayVazio() throws CEException {
		ContaAbstrata[] lista;
		lista = array.listar();
		
		assertNull(lista);
	}
}
