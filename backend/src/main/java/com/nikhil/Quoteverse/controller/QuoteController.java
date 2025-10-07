package com.nikhil.Quoteverse.controller;


import com.nikhil.Quoteverse.model.Quote;
import com.nikhil.Quoteverse.service.QuoteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/quotes")
public class QuoteController {

    private final QuoteService quoteService;

    public QuoteController(QuoteService quoteService) {
        this.quoteService = quoteService;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Quote> updateQuote(@PathVariable int id, @RequestBody Quote updatedFields) {
        Quote updatedQuote = quoteService.updateQuote(id, updatedFields);
        return ResponseEntity.ok(updatedQuote);
    }
}