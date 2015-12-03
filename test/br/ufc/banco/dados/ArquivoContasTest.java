package br.ufc.banco.dados;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import br.ufc.banco.conta.Conta;
import br.ufc.banco.conta.ContaAbstrata;
import br.ufc.banco.dados.excecoes.CEException;
import br.ufc.banco.dados.excecoes.CIException;

public class ArquivoContasTest {

	ArquivoContas contas;
	
	@Before
	public void setUp() throws Exception {
		contas = new ArquivoContas();
	}

	@After
	public void tearDown() throws Exception {
		File arquivo = new File(System.getProperty("user.home") + File.separator + "Sistema Bancario" + File.separator + "contas");
		arquivo.delete();
	}

	@Test
	public void testProcurarVazio() {
		ContaAbstrata c = contas.procurar("11111");
		assertNull(c);
	}
	
	@Test
	public void testInserir() throws CEException {
		contas.inserir(new Conta("11111"));
		
		ContaAbstrata c = contas.procurar("11111");
		assertNotNull(c);
	}
	
	@Test (expected = CEException.class)
	public void testInserirDuplicado() throws CEException {
		contas.inserir(new Conta("0001"));
		contas.inserir(new Conta("0001"));
	}

	@Test (expected = CIException.class)
	public void testApagarContaInexistente() throws CIException {
		contas.apagar("9999");
	}
	
	@Test
	public void testApagarConta() throws CIException, CEException {
		contas.inserir(new Conta("0004"));
		contas.inserir(new Conta("0005"));
		contas.apagar("0005");
		
		ContaAbstrata c = contas.procurar("0005");
		assertNull(c);
	}

	@Test
	public void testNumeroContas() throws CEException {
		contas.inserir(new Conta("0007"));
		contas.inserir(new Conta("0008"));
		contas.inserir(new Conta("0009"));
		
		assertEquals(3, contas.numeroContas());
	}
	
	@Test
	public void testNumeroContasComExclusao() throws CEException, CIException {
		contas.inserir(new Conta("0001"));
		contas.inserir(new Conta("0002"));
		contas.inserir(new Conta("0003"));
		contas.apagar("0002");
		contas.inserir(new Conta("0004"));
		
		assertEquals(3, contas.numeroContas());
	}
	
	@Test
	public void testListarContas() throws CEException {
		ContaAbstrata[] lista;
		
		contas.inserir(new Conta("0001"));
		contas.inserir(new Conta("0002"));
		contas.inserir(new Conta("0003"));
		
		lista = contas.listar();
		
		assertEquals(3, lista.length);
	}
	
	@Test
	public void testListarContasArrayVazio() throws CEException {
		ContaAbstrata[] lista;
		lista = contas.listar();
		
		assertNull(lista);
	}

}
