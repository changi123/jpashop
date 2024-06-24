package jpabook.jpashop.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class DeliveryStatus {
	@Id @GeneratedValue
	@Column(name= "delivery_id")
	private Long id;
	
	private Order order;
	
	@Embedded 
	private Address address;
	
//	@Enumerated(EnumType.ORDINAL)
//	private deliver
}
