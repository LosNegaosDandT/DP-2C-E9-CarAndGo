
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.MessageRepository;
import security.LoginService;
import domain.Actor;
import domain.Attachment;
import domain.Customer;
import domain.Message;
import forms.MessageForm;
import forms.RegisterCustomerForm;

@Service
@Transactional
public class MessageService {

	// Managed repository -----------------------------------------------------
	@Autowired
	private MessageRepository	messageRepository;

	@Autowired
	private LoginService		loginService;


	// Supporting services ----------------------------------------------------

	// Constructors -----------------------------------------------------------
	public MessageService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------
	public Message create() {
		final Message r = new Message();
		r.setAttachments(new ArrayList<Attachment>());
		r.setDate(new Date());
		r.setText("");
		r.setTitle("");
		r.setOwner(this.loginService.getPrincipalActor());
		return r;
	}

	public Collection<Message> findAll() {
		final Collection<Message> res = this.messageRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Message findOne(final int messageId) {
		return this.messageRepository.findOne(messageId);
	}

	public Message save(final Message message) {
		Assert.notNull(message);
		final Message m = this.messageRepository.save(message);
		message.setId(0);
		message.setVersion(0);
		message.setOwner(message.getRecipient());
		this.messageRepository.save(message);
		return m;
	}

	public void delete(final Message message) {
		this.messageRepository.delete(message);
	}

	public void flush() {
		this.messageRepository.flush();
	}
	// Other business methods -------------------------------------------------

	public Collection<Message> getSentMessages(final Actor a) {
		return this.messageRepository.getSentMessages(a);
	}

	public Collection<Message> getReceivedMessages(final Actor a) {
		return this.messageRepository.getReceivedMessages(a);
	}

	public Message reconstruct(final MessageForm messageForm, final Message message) {
		message.setRecipient(messageForm.getRecipient());
		message.setText(messageForm.getText());
		message.setTitle(messageForm.getTitle());
		return message;
	}

	public Customer reconstruct(final RegisterCustomerForm customerForm, final Customer customer) {
		final Md5PasswordEncoder encoder = new Md5PasswordEncoder();
		customer.getUserAccount().setPassword(encoder.encodePassword(customerForm.getPassword(), null));
		customer.getUserAccount().setUsername(customerForm.getUsername());

		customer.setName(customerForm.getName());
		customer.setSurname(customerForm.getSurname());
		customer.setEmail(customerForm.getEmail());
		customer.setPhone(customerForm.getPhone());

		return customer;
	}
}
