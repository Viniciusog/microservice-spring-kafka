package br.viniciusog.ecommerce.checkout.service;

import br.viniciusog.ecommerce.checkout.entity.CheckoutEntity;
import br.viniciusog.ecommerce.checkout.resource.checkout.CheckoutRequest;

import java.util.List;
import java.util.Optional;

public interface CheckoutService {

    Optional<CheckoutEntity> create (CheckoutRequest request);

    List<CheckoutEntity> findAll();

    void deleteById(Long id);
}