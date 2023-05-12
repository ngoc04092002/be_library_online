package ltw.btl.dto.book;

import ltw.btl.model.Book.RatingEntity;

public record RatingResponse(Integer one, Integer two, Integer three, Integer four, Integer five) {
    public RatingResponse(RatingEntity ratingEntity) {
        this(ratingEntity.getOne(), ratingEntity.getTwo(), ratingEntity.getThree(), ratingEntity.getFour(),
             ratingEntity.getFive());
    }
}
