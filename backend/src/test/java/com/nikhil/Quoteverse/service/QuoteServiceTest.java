package com.nikhil.Quoteverse.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nikhil.Quoteverse.config.QuoteFileProperties;
import com.nikhil.Quoteverse.model.Quote;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class QuoteServiceTest {

    private QuoteService quoteService;
    private final ObjectMapper mapper = new ObjectMapper();

    @TempDir
    Path tempDir; // JUnit 5 annotation for temporary folder

    private File testQuotesFile;

    @BeforeEach
    void setup() throws IOException {
        // Prepare a temporary JSON file for testing
        testQuotesFile = tempDir.resolve("quotes.json").toFile();

        List<Quote> initialQuotes = List.of(
                createQuote(1, "Test quote 1", "Author 1", List.of("tag1", "tag2")),
                createQuote(2, "Test quote 2", "Author 2", List.of("tag3"))
        );

        // Write initial quotes to the temp file
        mapper.writerWithDefaultPrettyPrinter().writeValue(testQuotesFile, initialQuotes);

        // Mock QuoteFileProperties to return our temp file path
        QuoteFileProperties properties = new QuoteFileProperties() {
            @Override
            public String getPath() {
                return testQuotesFile.getAbsolutePath();
            }
        };

        // Initialize service with mocked properties
        quoteService = new QuoteService(properties);
        quoteService.init();
    }

    @Test
    void updateQuote_shouldUpdateExistingQuote() {
        Quote updatedFields = new Quote();
        updatedFields.setText("Updated test quote 1");
        updatedFields.setAuthor("Updated Author");
        updatedFields.setTags(List.of("updated", "tags"));

        Quote updatedQuote = quoteService.updateQuote(1, updatedFields);

        assertEquals(1, updatedQuote.getId());
        assertEquals("Updated test quote 1", updatedQuote.getText());
        assertEquals("Updated Author", updatedQuote.getAuthor());
        assertEquals(List.of("updated", "tags"), updatedQuote.getTags());

        // Verify that the file is updated correctly
        try {
            List<Quote> quotesFromFile = mapper.readValue(testQuotesFile, new TypeReference<List<Quote>>() {});
            Quote q = quotesFromFile.stream().filter(quote -> quote.getId() == 1).findFirst().orElse(null);

            assertNotNull(q);
            assertEquals("Updated test quote 1", q.getText());
            assertEquals("Updated Author", q.getAuthor());
            assertEquals(List.of("updated", "tags"), q.getTags());
        } catch (IOException e) {
            fail("Failed to read quotes from file");
        }
    }

    @Test
    void updateQuote_shouldThrowExceptionIfQuoteNotFound() {
        Quote updatedFields = new Quote();
        updatedFields.setText("New text");

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            quoteService.updateQuote(999, updatedFields);
        });

        assertTrue(exception.getMessage().contains("not found"));
    }

    private Quote createQuote(int id, String text, String author, List<String> tags) {
        Quote q = new Quote();
        q.setId(id);
        q.setText(text);
        q.setAuthor(author);
        q.setTags(tags);
        return q;
    }
}

