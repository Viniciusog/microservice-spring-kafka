package br.viniciusog.ecommerce.checkout.streaming;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface CheckoutCreatedSource {
    //Tudo que eu jogar aqui nesse tópico virtual, irá para o tópico real que eu definir depois
    //Esse 'irá para aonde'é configurado em application.yml

    //Como se fosse um apelido para o nosso tópico real, definido em application.yml
    String OUTPUT = "checkout-created-output";

    //É o nosso canal com nome checkout-created-output
    @Output(OUTPUT)
    MessageChannel output();
}