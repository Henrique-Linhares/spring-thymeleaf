package br.com.aweb.sistema_produto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.aweb.sistema_produto.model.Product;
import br.com.aweb.sistema_produto.repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    // Buscar Todos produtos
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    // Buscar produto por id
    public Product findById(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            return optionalProduct.get();
        } else {
            throw new RuntimeException("Produto não encontrado");
        }
    }

    // Inserir ou atualizar um produto
    public Product save(Product product) {
        return productRepository.save(product);
    }

    //Excluir produto por ID
    public void deleteById(Long id) {
        if(!productRepository.existsById(id)) throw new RuntimeException("Produto não encontrado");
        productRepository.deleteById(id);
    }
}
