package br.viniciusog.ecommerce.checkout.repository;

import br.viniciusog.ecommerce.checkout.entity.CheckoutEntity;
import org.springframework.data.jpa.repository.JpaRepository;

//Entidade / id
public interface CheckoutRepository extends JpaRepository<CheckoutEntity, Long> {
}
