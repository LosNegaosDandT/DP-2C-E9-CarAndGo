
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.CustomerRepository;
import security.Authority;
import security.UserAccount;
import domain.Application;
import domain.Comment;
import domain.Customer;
import domain.Message;
import domain.Offer;
import domain.Request;
import forms.RegisterCustomerForm;

@Service
@Transactional
public class CustomerService {

	// Managed repository -----------------------------------------------------
	@Autowired
	private CustomerRepository	customerRepository;


	/*
	 * @Autowired
	 * private Validator validator;
	 */

	// Supporting services ----------------------------------------------------

	// Constructors -----------------------------------------------------------
	public CustomerService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------
	public Customer create() {

		final UserAccount userAccount = new UserAccount();
		final Authority aut = new Authority();
		aut.setAuthority(Authority.CUSTOMER);
		final Collection<Authority> authorities = userAccount.getAuthorities();
		authorities.add(aut);
		userAccount.setAuthorities(authorities);

		final Customer r = new Customer();
		final Collection<Request> requests = new ArrayList<>();
		final Collection<Offer> offers = new ArrayList<>();
		final Collection<Application> applications = new ArrayList<>();
		final Collection<Comment> comments = new ArrayList<>();
		final Collection<Message> messages = new ArrayList<>();
		r.setRequests(requests);
		r.setOffers(offers);
		r.setApplications(applications);
		r.setUserAccount(userAccount);
		r.setComments(comments);
		r.setSentMessages(messages);
		r.setSentMessages(messages);
		r.setReceivedMessages(messages);
		return r;
	}

	public Collection<Customer> findAll() {
		final Collection<Customer> res = this.customerRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Customer findOne(final int customerId) {
		return this.customerRepository.findOne(customerId);
	}

	public Customer save(final Customer customer) {
		Assert.notNull(customer);
		return this.customerRepository.save(customer);
	}

	public void delete(final Customer customer) {
		this.customerRepository.delete(customer);
	}

	public void flush() {
		this.customerRepository.flush();
	}
	// Other business methods -------------------------------------------------

	public Object[] query4() {
		Object[] res = null;
		final List<Object[]> o = this.customerRepository.query4();
		if (o.size() > 0)
			res = o.get(0);
		return res;
	}

	public Object[] query5() {
		Object[] res = null;
		final List<Object[]> o = this.customerRepository.query5();
		if (o.size() > 0)
			res = o.get(0);
		return res;
	}

	public Customer reconstruct(final RegisterCustomerForm customerForm, final Customer customer) {
		final Md5PasswordEncoder encoder = new Md5PasswordEncoder();
		final UserAccount u = customer.getUserAccount();
		u.setPassword(encoder.encodePassword(customerForm.getPassword(), null));
		u.setUsername(customerForm.getUsername());
		customer.setUserAccount(u);

		customer.setName(customerForm.getName());
		customer.setSurname(customerForm.getSurname());
		customer.setEmail(customerForm.getEmail());
		customer.setPhone(customerForm.getPhone());

		return customer;
	}

}
