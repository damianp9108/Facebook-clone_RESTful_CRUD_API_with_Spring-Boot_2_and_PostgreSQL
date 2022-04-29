package facebookapi.business.service;

import facebookapi.domain.entity.User;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class UserServiceTest {

    @Test
    public void should_return_list_of_userNames() {
        //given
        UserService userService = mock(UserService.class);
        given(userService.getUserNamesList()).willReturn(prepareMockData());

        //when
        List<String> list = userService.getUserNamesList();

        //then
        Assert.assertEquals(new ArrayList<String>(Arrays.asList("Marcin", "Tomek", "Ania")), list);
    }

    private List<String> prepareMockData(){
        User marcin = new User("Marcin", "da@gd", "dupa", "asdf");
        User tomek = new User("Tomek", "da@gasdfas", "cycki", "aa");
        User ania = new User("Ania", "dsdfa@gd", "cipka", "xxx");

        List<User> list2 = new ArrayList();
        list2.add(marcin);
        list2.add(tomek);
        list2.add(ania);

        List<String> userNamesList = list2.stream()
                .map(User::getUserName)
                .collect(Collectors.toList());

        return userNamesList;
    }

}