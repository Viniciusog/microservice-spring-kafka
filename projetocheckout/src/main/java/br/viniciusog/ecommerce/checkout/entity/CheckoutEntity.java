package br.viniciusog.ecommerce.checkout.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

//@Builder Possibilita criar um objeto sem fazer uma instanciação direta
//Fica dessa forma no nosso serviço:
//CheckoutEntity.builder().code(UUID.randomUUID().toString()).build();
@Builder
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class CheckoutEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String code;

    @Column
    @Enumerated(value = EnumType.STRING)
    private CheckoutStatus status;
}