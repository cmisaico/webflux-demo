package com.sermaluc;

import com.sermaluc.controllers.UsuarioController;
import com.sermaluc.models.PhoneDto;
import com.sermaluc.models.UserRequestDTO;
import com.sermaluc.models.UserResponseDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.client.ExchangeFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(MockitoExtension.class)
class SermalucDemoApplicationTests {


	@Autowired
	private WebTestClient client;

	@Test
	@Order(1)
	void crearUserTest() {
		List<PhoneDto> phones = new ArrayList<>();
		phones.add(PhoneDto.builder()
				.number("956123567")
				.citycode("LI")
				.contrycode("PE")
				.build());
		UserRequestDTO user = UserRequestDTO.builder()
				.name("Juan")
				.phones(phones)
				.email("juan@gmail.com")
				.password("La123c456")
				.build();
		client.post().uri("/api/user/create")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.body(Mono.just(user), UserRequestDTO.class)
				.exchange()
				.expectStatus().isCreated()
				.expectHeader().contentType(MediaType.APPLICATION_JSON)
				.expectBody(UserResponseDTO.class)
				.consumeWith(response -> {
					UserResponseDTO userResponseDTO = response.getResponseBody();
                    assert userResponseDTO != null;
                    Assertions.assertNotNull(userResponseDTO.getId());
					Assertions.assertNotNull(userResponseDTO.getPhones());
					Assertions.assertEquals(userResponseDTO.getName(), user.getName());
					Assertions.assertEquals(userResponseDTO.getEmail(), user.getEmail());
					Assertions.assertNotNull(userResponseDTO.getCreated());
					Assertions.assertNotNull(userResponseDTO.getModified());
					Assertions.assertNotNull(userResponseDTO.getToken());
					Assertions.assertNotNull(userResponseDTO.getLastLogin());
					Assertions.assertEquals(userResponseDTO.getPhones().get(0).getNumber(), user.getPhones().get(0).getNumber());
					Assertions.assertEquals(userResponseDTO.getPhones().get(0).getCitycode(), user.getPhones().get(0).getCitycode());
					Assertions.assertEquals(userResponseDTO.getPhones().get(0).getContrycode(), user.getPhones().get(0).getContrycode());

				});
	}

	@Test
	@Order(2)
	void existeEmailTest(){
		List<PhoneDto> phones = new ArrayList<>();
		phones.add(PhoneDto.builder()
				.number("956123567")
				.citycode("LI")
				.contrycode("PE")
				.build());
		UserRequestDTO user = UserRequestDTO.builder()
				.name("Juan")
				.phones(phones)
				.email("juan@gmail.com")
				.password("La123c456")
				.build();
		client.post().uri("/api/user/create")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.body(Mono.just(user), UserRequestDTO.class)
				.exchange()
				.expectStatus().isBadRequest()
				.expectHeader().contentType(MediaType.APPLICATION_JSON)
				.expectBody(Map.class)
				.consumeWith(response -> {
					Map<String, String> mensaje = response.getResponseBody();
					Assertions.assertEquals(mensaje.get("mensaje"), "El correo ya registrado");
				});
	}


	@Test
	@Order(3)
	void createUsuarioErrorTest(){
		List<PhoneDto> phones = new ArrayList<>();
		phones.add(PhoneDto.builder()
				.number("956123567")
				.citycode("LI")
				.contrycode("PE")
				.build());
		UserRequestDTO user = UserRequestDTO.builder()
				.name("Juan")
				.phones(phones)
				.email("juangmail.com")
				.password("La123c456")
				.build();
		client.post().uri("/api/user/create")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.body(Mono.just(user), UserRequestDTO.class)
				.exchange()
				.expectStatus().isBadRequest()
				.expectHeader().contentType(MediaType.APPLICATION_JSON)
				.expectBody(Map.class)
				.consumeWith(response -> {
					Map<String, String> mensaje = response.getResponseBody();
					Assertions.assertNotNull(mensaje.get("mensaje"));
				});
	}


}
