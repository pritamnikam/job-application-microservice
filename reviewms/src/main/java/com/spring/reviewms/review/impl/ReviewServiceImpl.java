package com.spring.reviewms.review.impl;

import com.spring.reviewms.review.Review;
import com.spring.reviewms.review.ReviewRepository;
import com.spring.reviewms.review.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public List<Review> getAllReviews(Long companyId) {
        return this.reviewRepository.findByCompanyId(companyId);
    }

    @Override
    public boolean createReview(Long companyId, Review review) {
        if (companyId != null && review != null) {
            review.setCompanyId(companyId);
            this.reviewRepository.save(review);
            return true;
        }
        return false;
    }

    @Override
    public Review getReviewById(Long id) {
        return this.reviewRepository.findById(id).orElse(null);
    }

    @Override
    public boolean updateReview(Long id, Review updatedReview) {

        if (this.reviewRepository.existsById(id) && updatedReview != null) {
            Review review = this.reviewRepository.getById(id);
            review.setTitle(updatedReview.getTitle());
            review.setDescription(updatedReview.getDescription());
            review.setCompanyId(updatedReview.getCompanyId());
            review.setRating(updatedReview.getRating());
            this.reviewRepository.save(review);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteReviewById(Long id) {
        try {
            this.reviewRepository.deleteById(id);
            return true;
        } catch(Exception e) {
            return false;
        }
    }
}
