package jwd.wafepa;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jwd.wafepa.model.Activity;
import jwd.wafepa.model.Address;
import jwd.wafepa.model.Author;
import jwd.wafepa.model.Book;
import jwd.wafepa.model.User;
import jwd.wafepa.service.ActivityService;
import jwd.wafepa.service.AddressService;
import jwd.wafepa.service.AuthorService;
import jwd.wafepa.service.BookService;
import jwd.wafepa.service.UserService;

@Component
public class TestData {

	@Autowired
	private UserService userService;
	
	@Autowired
	private AddressService addressService;

	@Autowired
	private ActivityService activityService;
	
	@Autowired
	private AuthorService authorService;
	
	@Autowired
	private BookService bookService;
	
	@PostConstruct
	public void init(){
//		Author martinHajdeger = new Author("Martin", "Hajdeger");
//		authorService.save(martinHajdeger);
//		Book bitakIVreme = new Book("Bitak i vreme");
//		bitakIVreme.getAuthors().add(martinHajdeger);
//		bookService.save(bitakIVreme);
//		
//		Author fridrihNice = new Author("Fridrih", "Nice");
//		authorService.save(fridrihNice);
//		Book koJeNiceovZaratustra = new Book("Ko je Niceov Zaratustra?");
//		koJeNiceovZaratustra.getAuthors().add(martinHajdeger);
//		koJeNiceovZaratustra.getAuthors().add(fridrihNice);
//		bookService.save(koJeNiceovZaratustra);
//		
//		Author sorenKjerkegar = new Author("Soren", "Kejrkegar");
//		authorService.save(sorenKjerkegar);
//		Author markoMiljanovPopovic = new Author("Marko Miljanov", "Popovic");
//		authorService.save(markoMiljanovPopovic);
		
//	       for (int i = 1; i <= 100; i++) {
/*	            User user = new User();
	            user.setFirstName("First name " + i);
	            user.setLastName("Last name " + i);
	            user.setEmail("email" + i + "@example.com");
	            user.setPassword("123");
	            userService.save(user);

	            for (int j = 1; j <= 3; j++) {
	                Address address = new Address();
	                address.setNumber(j + "c/7");
	                address.setStreat("Narodnog fronta");

//	                address.setUser(user);
	                user.addAddress(address);
	                addressService.save(address);
	            }
	            Activity activity = new Activity("Activity "+i);
	            activity.getUsers().add(user);
	            activityService.save(activity);
*/	            
//	       }
	}
}
