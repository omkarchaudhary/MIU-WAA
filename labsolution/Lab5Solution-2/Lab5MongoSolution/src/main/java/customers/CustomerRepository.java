package customers;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, Integer> {
    Customer findByPhone(String phone);
    Customer findByName(String name);
    List<Customer> findByNameOrPhone(String name, String phone);

    @Query("{email : ?0}")
    Customer findCustomerWithEmail(String email);

    @Query("{'creditCard.type' : ?0}")
    List<Customer>  findCustomerWithCreditCardType(String cctype);

}

