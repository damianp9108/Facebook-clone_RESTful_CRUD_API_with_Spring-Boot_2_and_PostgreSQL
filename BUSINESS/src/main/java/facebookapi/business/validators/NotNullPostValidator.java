package facebookapi.business.validators;

import facebookapi.business.annotations.NotNullPost;
import facebookapi.business.dto.NewPostDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NotNullPostValidator implements ConstraintValidator<NotNullPost, NewPostDto> {

    @Override
    public boolean isValid(NewPostDto newPostDto, ConstraintValidatorContext constraintValidatorContext) {

        return !((newPostDto.getDescription() == null || newPostDto.getDescription().isBlank()) &&
                (newPostDto.getPostImgURL() == null || newPostDto.getPostImgURL().isBlank()));

    }
}
