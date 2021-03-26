package br.viniciusog.ecommerce.checkout.resource.checkout;

import br.viniciusog.ecommerce.checkout.entity.CheckoutEntity;
import br.viniciusog.ecommerce.checkout.repository.CheckoutRepository;
import br.viniciusog.ecommerce.checkout.service.CheckoutService;
import br.viniciusog.ecommerce.checkout.service.CheckoutServiceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Path;
import java.util.List;

@RestController
@RequestMapping("/api/v1/checkout")
//Cria um construtor em tempo de execução com todos os atributos que são final. É uma boa prática
@RequiredArgsConstructor
public class CheckoutController {

    private final CheckoutService checkoutService;

    @PostMapping("/")
    public ResponseEntity<CheckoutResponse> create(@RequestBody CheckoutRequest checkoutRequest) {
        //Caso tenha dado tudo certo, o checkoutEntity será adicionado na variável
        //Caso tenha dado alguma coisa de errado, retornará uma exceção
        final CheckoutEntity checkoutEntity =  checkoutService.create(checkoutRequest).orElseThrow();

        //Em vez de retornar o checkoutEntity com vários dados, estamos retornando apenas o CheckoutResponse
        //com o código
        final CheckoutResponse checkoutResponse = CheckoutResponse.builder()
                .code(checkoutEntity.getCode()).build();

        return ResponseEntity.status(HttpStatus.CREATED).body(checkoutResponse);
    }

    //Mudar para retornar uma lista apenas de CheckoutResponse
    @GetMapping
    public ResponseEntity<List<CheckoutEntity>> findAll() {
        return ResponseEntity.ok().body(checkoutService.findAll());
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        checkoutService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
