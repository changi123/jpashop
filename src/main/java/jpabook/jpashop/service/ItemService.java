package jpabook.jpashop.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {
	private final ItemRepository itemRepository;
	
	
	@Transactional
	public void saveItem(Item item) {
		itemRepository.save(item);
	}
	
	
	@Transactional
	public void updateItem(Long itemId, Book param) {
		Item findItem = itemRepository.findOne(itemId);
		findItem.setPrice(param.getPrice());;
		findItem.setName(param.getName());
		findItem.setStockQuantity(param.getStockQuantity());
		
	}
	
	
	
	public List<Item> findItems(){
		return itemRepository.findAll();
	}
	public Item findOne(Long itemId){
		return itemRepository.findOne(itemId);
	}

    @Transactional
    public void updateItem(Long itemId, String name, int price, int stockQuantity) {
        Item item = itemRepository.findOne(itemId);
        item.setName(name);
        item.setPrice(price);
        item.setStockQuantity(stockQuantity);
    }

	
	
}
