package com.spring.reviewms.review;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("reviews")
public class ReviewController {
    private Long nextId = 1L;
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping
    public ResponseEntity<List<Review>> getAllReviews(@RequestParam Long companyId) {
        return ResponseEntity.ok(this.reviewService.getAllReviews(companyId));
    }

    @PostMapping
    public ResponseEntity<String> createReview(@RequestParam Long companyId, @RequestBody Review review) {
        review.setId(nextId++);
        boolean reviewAdded = this.reviewService.createReview(companyId, review);
        if (reviewAdded) {
            return new ResponseEntity<>("New review successfully added.", HttpStatus.CREATED);
        }

        return new ResponseEntity<>("Company not found.", HttpStatus.NOT_FOUND);
    }

    @GetMapping("{id}")
    public ResponseEntity<Review> getReviewById(@PathVariable Long id) {
        Review review = this.reviewService.getReviewById(id);
        if (review != null) {
            return ResponseEntity.ok(review);
        }

        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PutMapping("{id}")
    public ResponseEntity<String> updateReview(@PathVariable Long id, @RequestBody Review updatedReview) {
        boolean updated = this.reviewService.updateReview(id, updatedReview);
        if (updated) {
            return ResponseEntity.ok("Review updated successfully!");
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteReviewById( @PathVariable Long id) {
        boolean deleted = this.reviewService.deleteReviewById(id);
        if (deleted) {
            return ResponseEntity.ok("Review deleted successfully!");
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
