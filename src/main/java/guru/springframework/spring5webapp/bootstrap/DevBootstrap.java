package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.model.Author;
import guru.springframework.spring5webapp.model.Book;
import guru.springframework.spring5webapp.model.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent>
{
    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;

    public DevBootstrap(final AuthorRepository authorRepository,
        final BookRepository bookRepository, final PublisherRepository publisherRepository)
    {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void onApplicationEvent(final ContextRefreshedEvent contextRefreshedEvent)
    {
        initData();
    }

    private void initData()
    {
        Publisher pub1 = new Publisher("Harper Collins", "City Zipp and Downing Street 5");
        Publisher pub2 = new Publisher("Some other Publisher", "Some City and Zip And Street");
        publisherRepository.save(pub1);
        publisherRepository.save(pub2);

        Author eric = new Author("Eric", "Evans");
        Author rene = new Author("Rene", "Niko");
        Author rod = new Author("Rod", "Johnson");
        authorRepository.save(eric);
        authorRepository.save(rene);
        authorRepository.save(rod);

        Book book1 = new Book("Some Title", "isbn12345678", pub1);
        book1.getAuthors().add(eric);
        bookRepository.save(book1);
        book1.getAuthors().add(rene);
        bookRepository.save(book1);

        Book book2 = new Book("Other Title", "isbn00000001", pub2);
        book2.getAuthors().add(rod);
        bookRepository.save(book2);
    }
}
