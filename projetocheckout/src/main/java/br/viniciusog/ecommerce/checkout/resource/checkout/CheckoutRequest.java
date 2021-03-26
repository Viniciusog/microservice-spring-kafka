package br.viniciusog.ecommerce.checkout.resource.checkout;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckoutRequest implements Serializable {

    //Ao apertar no botão de realizar pagamento, será criado uma requisição de Checkout
    private static final long serialVersion = 1l;

    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String address;
    private String complement;
    private String country;
    private String state;
    private String cep;
    private Boolean saveAddress;
    private Boolean saveInfo;
    private String paymentMethod;
    private String cardName;
    private String cardNumber;
    private String cardDate;
    private String cardCvv;
}
