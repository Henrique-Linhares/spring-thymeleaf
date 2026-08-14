package br.com.aweb.sistema_produto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.aweb.sistema_produto.model.Product;
import br.com.aweb.sistema_produto.service.ProductService;

@Controller
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    //Listar todos
    @GetMapping
    public String list(Model model) {
        model.addAttribute("products", productService.findAll());
        return "products/list";
    }

    //Retorna o nome da view do formulário de cadastro/edição de produtos
    @GetMapping("/new")
    public String showForm(Model model) {
        model.addAttribute("product", new  Product());
        return "products/form";
    }


    // Salvar produto
    @PostMapping
    public String save(@Validated Product product, 
        BindingResult result, RedirectAttributes attributes) {
            if(result.hasErrors()) return "products/form";
            productService.save(product);
            attributes.addFlashAttribute("message", "Produto salvo com sucesso!");
            return "redirect:/products";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("product", productService.findById(id));
        return "products/form";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        productService.deleteById(id);
        return "redirect/products";
    }
}
