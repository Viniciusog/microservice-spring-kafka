package br.viniciusog.ecommerce.checkout.resource.checkout;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CheckoutResponse implements Serializable {

    //Esta classe serve apenas para mostrarmos o código do Checkout em vez de outros dados
    //da classe CheckoutEntity

    private String code;
}
