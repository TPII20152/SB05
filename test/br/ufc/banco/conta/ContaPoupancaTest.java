package br.ufc.banco.conta;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by thiagoisaias on 12/6/15.
 */
public class ContaPoupancaTest {

    ContaPoupanca cp = new ContaPoupanca("87263112");

    @Test
    public void testRendeJuros() throws Exception {
        cp.creditar(453);
        double saldoAnterior = cp.obterSaldo();
        double taxa = 0.07;
        cp.rendeJuros(taxa);
        double novoSaldo = cp.obterSaldo();
        assertEquals(novoSaldo,saldoAnterior*(1+taxa),0.001);
    }
}