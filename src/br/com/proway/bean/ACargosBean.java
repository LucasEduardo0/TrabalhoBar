package br.com.proway.bean;

public class ACargosBean {

	//Atributos
	private int idCargo;
	private String nomeCargo;
	private double valorSalario;
	private double descontoSalario;
	
	//Set e Get
	public int getIdCargo() {
		return idCargo;
	}
	public void setIdCargo(int idCargo) {
		this.idCargo = idCargo;
	}
	public String getNomeCargo() {
		return nomeCargo;
	}
	public void setNomeCargo(String nomeCargo) {
		this.nomeCargo = nomeCargo;
	}
	public double getValorSalario() {
		return valorSalario;
	}
	public void setValorSalario(double valorSalario) {
		this.valorSalario = valorSalario;
	}
	public double getDescontoSalario() {
		return descontoSalario;
	}
	public void setDescontoSalario(double descontoSalario) {
		this.descontoSalario = descontoSalario;
	}

}