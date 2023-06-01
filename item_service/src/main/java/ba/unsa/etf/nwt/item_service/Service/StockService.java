package ba.unsa.etf.nwt.item_service.Service;

import ba.unsa.etf.nwt.item_service.Exceptions.NotFoundException;
import ba.unsa.etf.nwt.item_service.Model.Item;
import ba.unsa.etf.nwt.item_service.Model.Stock;
import ba.unsa.etf.nwt.item_service.Repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockService {
    @Autowired
    private final StockRepository stockRepository;

    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    public Stock getStockById(Integer id) {
        return stockRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }


    public Stock changeStockAmount(Stock newAmount, Integer id){
        Stock oldStockAmount = getStockById(id);

        oldStockAmount.setAmount(newAmount.getAmount());

        stockRepository.save(oldStockAmount);
        return oldStockAmount;
    }

   // public void deleteStock(Integer id){
       // stockRepository.deleteById(id);
   // }

   // public void deleteAll(){
       // stockRepository.deleteAll();
    //}



}