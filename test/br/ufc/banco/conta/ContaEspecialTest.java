package br.ufc.banco.conta;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ContaEspecialTest {
	
	ContaEspecial contaEspecial;

	@Before
	public void setUp() throws Exception {
		contaEspecial = new ContaEspecial("1111");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCreditarNormal() {
		contaEspecial.creditar(10);
		assertEquals(10, contaEspecial.obterSaldo(), 0);
	}
	
	@Test
	public void testCreditarNegativo(){
		contaEspecial.creditar(10);
		contaEspecial.creditar(-5);
		assertEquals(10, contaEspecial.obterSaldo(), 0);
	}
	
	@Test
	public void testBonusNormal(){
		contaEspecial.creditar(10);
		assertEquals(0.1, contaEspecial.obterBonus(), 0);
	}
	
	@Test
	public void testBonusNegativo(){
		contaEspecial.creditar(10);
		contaEspecial.creditar(-5);
		assertEquals(0.1, contaEspecial.obterBonus(), 0);
	}
	
	@Test
	public void testRendeBonus(){
		contaEspecial.creditar(10);
		System.out.println(contaEspecial.obterBonus());
		contaEspecial.creditar(10);
		System.out.println(contaEspecial.obterBonus());
		
		contaEspecial.rendeBonus();
		
		assertEquals(20.2, contaEspecial.obterSaldo(), 0);
	}
	
	@Test
	public void testRendeZerarBonus(){
		contaEspecial.creditar(10);
		System.out.println(contaEspecial.obterBonus());
		contaEspecial.creditar(10);
		System.out.println(contaEspecial.obterBonus());
		
		contaEspecial.rendeBonus();
		
		assertEquals(0, contaEspecial.obterBonus(), 0);
	}
}
