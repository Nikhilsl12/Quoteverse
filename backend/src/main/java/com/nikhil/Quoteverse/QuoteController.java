package com.nikhil.Quoteverse;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api")
public class QuoteController {

    @GetMapping("/quotes")
    public ResponseEntity<?> getAllQuotes() {
        try {
        
            String basePath = System.getProperty("user.dir"); // should be ...\Quoteverse\Quoteverse\backend
            Path filePath = Path.of(basePath)
                .getParent()
                // .resolve("QuoteVerse") 
                .resolve("data")     // enter data folder
                .resolve("quotes.json")
                .normalize();

            File file = filePath.toFile();


            if (!file.exists()) {
                return ResponseEntity.status(404)
                        .body(Map.of("error", "quotes.json not found at " + file.getAbsolutePath()));
            }

            ObjectMapper mapper = new ObjectMapper();
            List<Map<String, Object>> quotes = mapper.readValue(file, new TypeReference<List<Map<String, Object>>>() {});


            if (quotes.isEmpty()) {
                return ResponseEntity.status(404).body(Map.of("error", "no quotes found"));
            }

            return ResponseEntity.ok(quotes);

        } catch (IOException e) {
            return ResponseEntity.status(500).body(Map.of("error", e.getMessage()));
        }
    }
}