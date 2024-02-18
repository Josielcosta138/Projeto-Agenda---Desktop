package Relatorio;

import java.io.IOException;

public interface IRelatorio {
	
	public void preparador();
	public void gerarCabecalho() throws IOException;
	public void gerarCopor();
	public void gerarRodape();
	public void gerarImprimir();
	
	

}
