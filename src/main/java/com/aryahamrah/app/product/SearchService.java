package com.aryahamrah.app.product;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchService {
    private final MongoTemplate template = null;

    public List<Product> searchByName(String name){
        Query query = new Query();
        query.addCriteria(Criteria.where("name").is(name));
        List<Product> products = template.find(query,Product.class);
        return products;
    }

    public List<Product> searchByNameStartingWith(String name){
        Query query = new Query();
        query.addCriteria(Criteria.where("name").regex("^" + name));
        List<Product> products = template.find(query,Product.class);
        return products;
    }

    public List<Product> searchByNameEndWith(String name){
        Query query = new Query();
        query.addCriteria(Criteria.where("name").regex(name + "$"));
        List<Product> products = template.find(query,Product.class);
        return products;
    }

    public List<Product> searchByNamePriceLt(BigDecimal price){
        Query query = new Query();
        query.addCriteria(Criteria.where("price").lt(price));
        List<Product> products = template.find(query,Product.class);
        return products;
    }

    public List<Product> searchByNamePriceGt(BigDecimal price){
        Query query = new Query();
        query.addCriteria(Criteria.where("price").gt(price));
        List<Product> products = template.find(query,Product.class);
        return products;
    }

    public List<Product> sortAscByField(String fieldName){
        Query query = new Query();
        query.with(Sort.by(Sort.Direction.ASC,fieldName));
        List<Product> products = template.find(query,Product.class);
        return products;
    }

    public List<Product> sortAndPageByField(String fieldName){
        Query query = new Query();
        Pageable pageable = PageRequest.of(0,2,Sort.by(Sort.Direction.ASC,fieldName));
        query.with(pageable);
        List<Product> products = template.find(query,Product.class);
        return products;
    }
}
