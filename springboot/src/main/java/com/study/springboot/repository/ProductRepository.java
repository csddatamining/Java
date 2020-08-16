package com.study.springboot.repository;

import com.study.springboot.entity.Product;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface ProductRepository {

    @Select("select * from product where uid=(select id from users where username=#{username})")
    List<Product> findAllByusername(String username);

    @Update("update product set name=#{product.name}," +
            "description=#{product.description}," +
            "price=#{product.price}," +
            "pic=#{product.pic} where id=#{product.id}")
    void updateProduct(@Param("product") Product product);

    @Delete("delete from product where id=#{id}")
    void deleteProductById(int id);

    @Select("select * from product where id=#{id}")
    Product findProductById(int id);
}
