package facebookapi.business.validators;

import facebookapi.business.annotations.NotNullPost;
import facebookapi.business.payload.request.NewPostRequest;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NotNullPostValidator implements ConstraintValidator<NotNullPost, NewPostRequest> {

    @Override
    public boolean isValid(NewPostRequest newPostRequest, ConstraintValidatorContext constraintValidatorContext) {

        return !((newPostRequest.getDescription() == null || newPostRequest.getDescription().isBlank()) &&
                (newPostRequest.getPostImgURL() == null || newPostRequest.getPostImgURL().isBlank()));

    }
}
