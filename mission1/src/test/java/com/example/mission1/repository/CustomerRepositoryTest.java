package com.example.mission1.repository;

import com.example.mission1.domain.Customer;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;


@Slf4j
@DataJpaTest()
class CustomerRepositoryTest {

    @Autowired
    CustomerRepository customerRepository;

    Customer newCustomer;

    String uuid = UUID.randomUUID().toString();

    @BeforeEach
    void create() {
        customerRepository.deleteAll();
        newCustomer = new Customer(uuid,"영지","youngji804@naver.com","안양시 동안구");
        customerRepository.save(newCustomer);
    }

    @Test
    @DisplayName("등록된 customer를 찾을 수 있다.")
    void read() {
        var findCustomer = customerRepository.findById(uuid);

        assertThat(findCustomer.isPresent()).isTrue();
        assertThat(findCustomer.get().getEmail()).isEqualTo(newCustomer.getEmail());
        log.info("{}",findCustomer.get());
        var customers = customerRepository.findAll();

        assertThat(customers.size()).isEqualTo(1);
    }

    @Test
    @DisplayName("등록된 customer의 주소를 변경하고 변경을 확인한다.")
    void update() {
        var findCustomer = customerRepository.findById(uuid).get();
        findCustomer.changeAddress("이사감~");

        var reFindCustomer = customerRepository.findById(uuid).get();
        assertThat(findCustomer.getAddress()).isEqualTo(reFindCustomer.getAddress());
    }

    @Test
    @DisplayName("등록된 customer를 삭제한다.")
    void delete() {
        var findCustomer = customerRepository.findById(uuid).get();
        customerRepository.delete(findCustomer);

        var reFindCustomer = customerRepository.findById(uuid);
        assertThat(reFindCustomer.isEmpty()).isTrue();
    }


}