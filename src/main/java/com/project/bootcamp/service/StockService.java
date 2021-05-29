package com.project.bootcamp.service;

import com.project.bootcamp.exceptions.BusinessException;
import com.project.bootcamp.exceptions.NotFoundException;
import com.project.bootcamp.mapper.StockMapper;
import com.project.bootcamp.model.Stock;
import com.project.bootcamp.model.dto.StockDTO;
import com.project.bootcamp.repository.StockRepository;
import com.project.bootcamp.util.MessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class StockService {

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private StockMapper stockMapper;

    @Transactional
    public StockDTO save(StockDTO dto) {
        Optional<Stock> optionalStock = stockRepository.findByNameAndDate(dto.getName(), dto.getDate());
        if(optionalStock.isPresent()) {
            throw new BusinessException(MessageUtils.STOCK_ALREADY_EXIST);
        }

        Stock stock = stockMapper.toEntity(dto);
        stockRepository.save(stock);
        return stockMapper.toDTO(stock);
    }

    @Transactional
    public StockDTO update(StockDTO dto) {
        Optional<Stock> optionalStock = stockRepository.findByStockUpdate(dto.getName(), dto.getDate(), dto.getId());
        if(optionalStock.isPresent()) {
            throw  new BusinessException(MessageUtils.STOCK_ALREADY_EXIST);
        }

        Stock stock = stockMapper.toEntity(dto);
        stockRepository.save(stock);
        return stockMapper.toDTO(stock);
    }

    @Transactional(readOnly = true)
    public List<StockDTO> findAll() {
        return stockMapper.toDTO(stockRepository.findAll());
    }

    @Transactional(readOnly = true)
    public StockDTO findById(Long id) {
        return stockRepository.findById(id).map(stockMapper::toDTO).orElseThrow(NotFoundException::new);
    }

    @Transactional
    public StockDTO delete(Long id) {
        StockDTO dto = this.findById(id);
        stockRepository.deleteById(dto.getId());
        return dto;
    }

    @Transactional(readOnly = true)
    public List<StockDTO> findByToday() {
        return stockRepository.findByToday(LocalDate.now()).map(stockMapper::toDTO).orElseThrow(NotFoundException::new);
    }
}
