package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.base.ReadService;
import id.ac.ui.cs.advprog.eshop.service.base.WriteService;

public interface ProductService extends ReadService<Product, String>, WriteService<Product, String> { }
