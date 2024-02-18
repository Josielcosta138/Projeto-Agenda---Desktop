package br.com.senac.vo;

public class RelatorioItem {
	
	   private String id;
	    private String nomeCliente;
	    private String dd;
	    private String numero;
	    private String totalServico;

	    public RelatorioItem(String id, String nomeCliente, String dd, String numero, String totalServico) {
	        this.id = id;
	        this.nomeCliente = nomeCliente;
	        this.dd = dd;
	        this.numero = numero;
	        this.totalServico = totalServico;
	    }

	 

	    @Override
	    public String toString() {
	        return "RelatorioItem{" +
	                "id='" + id + '\'' +
	                ", nomeCliente='" + nomeCliente + '\'' +
	                ", dd='" + dd + '\'' +
	                ", numero='" + numero + '\'' +
	                ", totalServico='" + totalServico + '\'' +
	                '}';
	    }



		public String getId() {
			return id;
		}



		public void setId(String id) {
			this.id = id;
		}



		public String getNomeCliente() {
			return nomeCliente;
		}



		public void setNomeCliente(String nomeCliente) {
			this.nomeCliente = nomeCliente;
		}



		public String getDd() {
			return dd;
		}



		public void setDd(String dd) {
			this.dd = dd;
		}



		public String getNumero() {
			return numero;
		}



		public void setNumero(String numero) {
			this.numero = numero;
		}



		public String getTotalServico() {
			return totalServico;
		}



		public void setTotalServico(String totalServico) {
			this.totalServico = totalServico;
		}
}
