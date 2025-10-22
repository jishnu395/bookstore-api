package com.bookstore.api.controller;

import com.bookstore.api.entity.Book;
import com.bookstore.api.repository.BookRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookRepository bookRepository;

    @Operation(summary = "Get all books", description = "Lists all books in the bookstore with pagination.")
    @ApiResponse(responseCode = "200", description = "List of books returned successfully")
    @GetMapping
    public Page<Book> getAllBooks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return bookRepository.findAll(pageable);
    }
    @Operation(summary = "Add a new book", description = "Creates a new book record in the database.")
    @ApiResponse(responseCode = "201", description = "Book created successfully")
    @PostMapping
    public Book addBook(@Valid @RequestBody Book book) {
        return bookRepository.save(book);
    }
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Book found"),
            @ApiResponse(responseCode = "404", description = "Book not found")
    })
    @Operation(summary = "Get book by ID", description = "Returns details of a specific book by its ID.")
    @ApiResponse(responseCode = "200", description = "Book found")
    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + id));
    }
    @Operation(summary = "Update book", description = "Updates an existing book identified by ID.")
    @ApiResponse(responseCode = "200", description = "Book updated successfully")
    @PutMapping("/{id}")
    public Book updateBook(@PathVariable Long id, @Valid @RequestBody Book updateBook) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + id));
        book.setTitle(updateBook.getTitle());
        book.setAuthor(updateBook.getAuthor());
        book.setIsbn(updateBook.getIsbn());
        book.setPrice(updateBook.getPrice());
        return bookRepository.save(book);
    }
    @Operation(summary = "Delete book", description = "Deletes a book from the database by ID.")
    @ApiResponse(responseCode = "204", description = "Book deleted (no content returned)")
    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookRepository.deleteById(id);
    }

    @Operation(summary = "Search books", description = "Finds books by partial match on title or author.")
    @ApiResponse(responseCode = "200", description = "Books matching criteria")
    @GetMapping("/search")
    public List<Book> searchBooks(
            @RequestParam(required = false) String author,
            @RequestParam(required = false) String title) {
        if (author != null && !author.isEmpty()) {
            return bookRepository.findByAuthorContainingIgnoreCase(author);
        } else if (title != null && !title.isEmpty()) {
            return bookRepository.findByTitleContainingIgnoreCase(title);
        } else {
            return bookRepository.findAll();
        }
    }
}
