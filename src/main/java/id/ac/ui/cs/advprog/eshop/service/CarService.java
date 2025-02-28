package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.service.base.ReadService;
import id.ac.ui.cs.advprog.eshop.service.base.WriteService;

public interface CarService extends ReadService<Car, String>, WriteService<Car, String> { }
