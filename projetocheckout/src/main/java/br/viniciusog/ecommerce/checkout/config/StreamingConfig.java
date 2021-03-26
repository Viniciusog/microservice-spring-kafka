package br.viniciusog.ecommerce.checkout.config;

import br.viniciusog.ecommerce.checkout.streaming.CheckoutCreatedSource;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBinding(value = {
        //Estamos passando aquela interfae que nós criamos
        CheckoutCreatedSource.class
})
public class StreamingConfig {
}
