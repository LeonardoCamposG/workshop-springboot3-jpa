package com.educandoweb.course.entities.enums;

public enum OrderStatus {

	WAITING_PAYMENT(1),		// Declarar qual o indice para o enumerado é importante, 
	PAID(2),				// para evitar problemas em manutençõs futuras.			
	SHIPPED(3),
	DELIVERED(4),
	CANCELED(5);
	
	private int code;		// Pra fazer o enum dessa forma declarativa, é preciso usar um construtor especial
							// do tipo private.
	
	private OrderStatus(int code) {
		this.code = code;
	}
	
	public int getCode() {
		return code;
	}
	
	// Static para poder sempre ser acessível sem instanciação.
	public static OrderStatus valueOf(int code){
		for(OrderStatus value : OrderStatus.values()) { // Percorrendo todos os valures do enum.
			if(value.getCode() == code) {
				return value;				// Metodo que recebe como parametro o indice do enum, e retorna o valor dele.
			}
		}
		throw new IllegalArgumentException("Invalid OrderStatus code");
	}
}
