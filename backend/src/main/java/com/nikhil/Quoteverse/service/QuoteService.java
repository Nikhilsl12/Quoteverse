package com.nikhil.Quoteverse.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nikhil.Quoteverse.config.QuoteFileProperties;
import com.nikhil.Quoteverse.model.Quote;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class QuoteService {

    private final ObjectMapper mapper = new ObjectMapper();
    private final QuoteFileProperties quoteFileProperties;
    private List<Quote> quotes = new ArrayList<>();
    private File quotesFile;

    public QuoteService(QuoteFileProperties quoteFileProperties) {
        this.quoteFileProperties = quoteFileProperties;
    }

    @PostConstruct
    public void init() {
        quotesFile = new File(quoteFileProperties.getPath());
        try {
            if (quotesFile.exists()) {
                quotes = mapper.readValue(quotesFile, new TypeReference<List<Quote>>() {});
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to read external quotes file", e);
        }
    }

    public Quote updateQuote(int id, Quote updatedFields) {
        Quote existingQuote = quotes.stream()
                .filter(q -> q.getId() == id)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Quote with id " + id + " not found"));

        if (updatedFields.getText() != null) {
            existingQuote.setText(updatedFields.getText());
        }

        if (updatedFields.getAuthor() != null) {
            existingQuote.setAuthor(updatedFields.getAuthor());
        }

        if (updatedFields.getTags() != null) {
            existingQuote.setTags(updatedFields.getTags());
        }

        saveQuotesToFile();
        return existingQuote;
    }

    private void saveQuotesToFile() {
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(quotesFile, quotes);
        } catch (IOException e) {
            throw new RuntimeException("Failed to save updated quotes to external file", e);
        }
    }
}
