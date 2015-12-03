package br.ufc.banco.dados;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.XStream;

import br.ufc.banco.conta.ContaAbstrata;
import br.ufc.banco.dados.excecoes.CEException;
import br.ufc.banco.dados.excecoes.CIException;

public class ArquivoContas implements IRepositorioContas {

	private File arquivo;
	
	public ArquivoContas() throws IOException {
		arquivo = new File(System.getProperty("user.home") + File.separator + "Sistema Bancario" + File.separator + "contas.xml");
		
		if(!arquivo.exists()) {
			arquivo.getParentFile().mkdirs();
			arquivo.createNewFile();
			
			XStream xstream = new XStream();
			xstream.toXML(new ArrayList<ContaAbstrata>(), new FileOutputStream(arquivo));
		}
	}
	
	@Override
	public void inserir(ContaAbstrata conta) throws CEException {
		List<ContaAbstrata> lista = lerArquivo();
		
		if (procurar(conta.obterNumero()) == null) {
			lista.add(conta);
		} else {
			throw new CEException(conta.obterNumero());
		}
		
		try {
			salvarArquivo(lista);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void apagar(String numero) throws CIException {
		List<ContaAbstrata> lista = lerArquivo();
		
		if (this.procurar(numero) != null) {
			for (ContaAbstrata conta : lista) {
				if(conta.obterNumero().equals(numero)) {
					lista.remove(conta);
					break;
				}
			}
		} else {
			throw new CIException(numero);
		}
		
		try {
			salvarArquivo(lista);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public ContaAbstrata procurar(String numero) {
		List<ContaAbstrata> lista = lerArquivo();
		
		if (!lista.isEmpty()) {
			for (ContaAbstrata conta : lista) {
				if (conta.obterNumero().equals(numero)) {
					return conta;
				}
			}
		}
		return null;
	}

	@Override
	public ContaAbstrata[] listar() {
		List<ContaAbstrata> lista = lerArquivo();
		
		ContaAbstrata[] array = null;
		if (!lista.isEmpty()) {
			array = new ContaAbstrata[lista.size()];
			int i = 0;
			for (ContaAbstrata conta : lista) {
				array[i++] = conta;
			}
		}
		return array;
	}

	@Override
	public int numeroContas() {
		List<ContaAbstrata> lista = lerArquivo();
		return lista.size();
	}
	
	private List<ContaAbstrata> lerArquivo() {
		List<ContaAbstrata> lista = null;
		XStream xstream = new XStream();
		lista = (List<ContaAbstrata>) xstream.fromXML(arquivo);

		if(lista != null) {
			return lista;
		} else {
			return new ArrayList<ContaAbstrata>();
		}
	}
	
	private void salvarArquivo(List<ContaAbstrata> lista) throws FileNotFoundException {
		XStream xstream = new XStream();
		xstream.toXML(lista, new FileOutputStream(arquivo));
	}

}
