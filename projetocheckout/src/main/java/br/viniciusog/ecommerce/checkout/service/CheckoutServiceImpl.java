package br.viniciusog.ecommerce.checkout.service;

import br.viniciusog.ecommerce.checkout.entity.CheckoutEntity;
import br.viniciusog.ecommerce.checkout.entity.CheckoutStatus;
import br.viniciusog.ecommerce.checkout.repository.CheckoutRepository;
import br.viniciusog.ecommerce.checkout.resource.checkout.CheckoutRequest;
import br.viniciusog.ecommerce.checkout.streaming.CheckoutCreatedSource;
import com.vinicius.ecommerce.checkout.event.CheckoutCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
//Cria um construtor em tempo de compilação com todos os atributos que estão em final
@RequiredArgsConstructor
public class CheckoutServiceImpl implements CheckoutService {

    //@Autowired ==> Não é uma boa prática colocar isso, pois dificulta na hora de realizar os testes unitários
    private final CheckoutRepository checkoutRepository;
    private final CheckoutCreatedSource checkoutCreatedSource;

    //Nem estamos utlizando os dados de CheckoutRequest
    //Quando recebermos o request do nosso frontend, criaremos um checkout e enviaremos
    //esse request depois para a api de payment
    @Override
    public Optional<CheckoutEntity> create(CheckoutRequest request) {
        //Cria um checkoutEntity e passa um UUID para ele
        final CheckoutEntity checkoutEntity = CheckoutEntity.builder()
                .code(UUID.randomUUID().toString())
                .status(CheckoutStatus.CREATED)
                .build();
        final CheckoutEntity entity = checkoutRepository.save(checkoutEntity);

        //Vamos pegar o nosso evento para passar na nossa mensagem
        final CheckoutCreatedEvent checkoutCreatedEvent = CheckoutCreatedEvent.newBuilder()
                .setCheckoutCode(entity.getCode())
                .setStatus(entity.getStatus().name())
                .build();

        //Estamos pegando o canal de mensagens do kafka e enviando uma mensagem para esse canal
        checkoutCreatedSource.output().send(MessageBuilder.withPayload(checkoutCreatedEvent).build());
        //Pronto, enviamos um evento para o Kafka, no ponto de escuta: checkout-created-output ->
        //streaming.ecommerce.checkout.created
       return Optional.of(entity);
    }


    @Override
    public List<CheckoutEntity> findAll() {
        return this.checkoutRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        checkoutRepository.deleteById(id);
    }
}
