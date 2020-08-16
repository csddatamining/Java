package com.study.springboot.controller;

import com.study.springboot.entity.Product;
import com.study.springboot.repository.ProductRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author Cdu
 * @discription:
 * @create 2020-08-16 11:55
 */
@Controller
public class ProductController {

    @Resource
    private ProductRepository productRepository;

    @ResponseBody
    @GetMapping("/product/{id}")
    public Object findProductById(@PathVariable int id){
        return productRepository.findProductById(id);
    }

    @ResponseBody
    @DeleteMapping("/product/{id}")
    public String deleteProductById(@PathVariable int id){
        productRepository.deleteProductById(id);
        return "success";
    }

    @RequestMapping("/product/update")
    public String update(Product product){
        productRepository.updateProduct(product);
        return "redirect:/user/list";
    }
}