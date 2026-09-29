package com.apress.crm.customer;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureRestTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.client.RestTestClient;

import com.apress.crm.customer.Domain.Customer;

@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureRestTestClient 
@DirtiesContext 
class CustomerApplicationTests {

	@Autowired 
	private RestTestClient restTestClient;

	@Test 
	void shouldRetrieveAndCreateCustomers() {
		restTestClient.get().uri("/api/v1/customers")
			.exchange()
			.expectStatus().isOk()
			.expectBody(Customer[].class)
			.value(customers->assertThat(customers).hasSize(4));
		
		Customer customer = new Customer("New User", "new@example.com", "123-456-7890");
		restTestClient.post().uri("/api/v1/customers")
			.contentType(MediaType.APPLICATION_JSON)
			.body(customer)
			.exchange()
			.expectStatus().isOk()
			.expectBody(Customer.class)
			.value(savedCustomer -> {
				assertThat(savedCustomer.id()).isNotNull();
				assertThat(savedCustomer.name()).isEqualTo(customer.name());
			});
		
		restTestClient.get().uri("/api/v1/customers")
			.exchange()
			.expectStatus().isOk()
			.expectBody(Customer[].class)
			.value(customers->assertThat(customers).hasSize(5));
	}

}
