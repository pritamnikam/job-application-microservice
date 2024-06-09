package com.spring.reviewms.review;

import java.util.List;

public interface ReviewService {
    List<Review> getAllReviews(Long companyId);
    boolean createReview(Long companyId, Review review);
    Review getReviewById(Long id);
    boolean updateReview(Long id, Review updatedReview);
    boolean deleteReviewById(Long id);
}
