package om19.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import om19.entity.Product;
import om19.repository.ProductRepository;
import om19.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    @Override
    public List<Product> allProducts() {
        return productRepository.findAll();
    }
}
