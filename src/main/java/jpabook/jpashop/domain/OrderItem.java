package jpabook.jpashop.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jpabook.jpashop.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class OrderItem {
	@Id @GeneratedValue
	@Column(name="order_item_id")
	private Long id; 
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="item_id")
	private Item item;
	
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="order_id")
	private Order order;
	
	private int orderPrice; // 주문 가격
	
	private int count ;  // 주문 수량

	// 생성 메서드
	public static OrderItem createOrderItem ( Item item, int orderPrice , int count) {
		OrderItem orderItem = new OrderItem();
		orderItem.setItem(item);
		orderItem.setOrderPrice(orderPrice);
		orderItem.setCount(count);
		
		item.removeStock(count);
		return orderItem;
	}
	
	// 비즈니스 로직
	public void cancle() {
		getItem().addStock(count);
		
	}

	
	// 주문 상품 전체 가격 조회
	public int getTotalPrice() {
		return getOrderPrice() * getCount();
	}
	
	
}
