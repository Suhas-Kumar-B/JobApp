package com.learn.firstjobapp.review.impl;

import com.learn.firstjobapp.company.Company;
import com.learn.firstjobapp.company.CompanyService;
import com.learn.firstjobapp.review.Review;
import com.learn.firstjobapp.review.ReviewRepository;
import com.learn.firstjobapp.review.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final CompanyService companyService;

    public ReviewServiceImpl(ReviewRepository reviewRepository,CompanyService companyService) {
        this.reviewRepository = reviewRepository;
        this.companyService= companyService;
    }

    @Override
    public List<Review> getAllReviews(Long companyId) {

        List<Review> reviews=reviewRepository.findByCompanyId(companyId);
        return reviews;
    }

    @Override
    public boolean addReview(Long companyId, Review review) {
        Company company=companyService.getCompanyById(companyId);
        if(company!=null){
            review.setCompany(company);
            reviewRepository.save(review);
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public Review getReviewById(Long companyId,Long reviewId) {
        List<Review> reviews=reviewRepository.findByCompanyId(companyId);
        return reviews.stream()
                .filter(review -> review.getID().equals(reviewId))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean updateReviewById(Long companyId, Long reviewId, Review updatedreview) {
        if (companyService.getCompanyById(companyId)!=null){
            updatedreview.setCompany(companyService.getCompanyById(companyId));
            updatedreview.setID(reviewId);
            reviewRepository.save(updatedreview);
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public boolean deleteReviewById(Long companyId, Long reviewId) {
        if (companyService.getCompanyById(companyId)!=null && reviewRepository.existsById(reviewId)){
            Review review=reviewRepository.findById(reviewId).orElse(null);
            Company company=review.getCompany();
            company.getReviews().remove(review);
            companyService.updateCompany(companyId,company);
            reviewRepository.deleteById(reviewId);
            return true;
        }
        else {
            return false;
        }
    }
}
