package facebookapi.business.service;

import facebookapi.domain.entity.User;
import facebookapi.domain.repository.UserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest2 {
    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService userService;

    @Before
    public void init() {
        given(userRepository.findAll()).willReturn(prepareMockData());
    }

    private List<User> prepareMockData(){
        User marcin = new User("Marcin", "da@gd", "dupa", "asdf");
        User tomek = new User("Tomek", "da@gasdfas", "cycki", "aa");
        User ania = new User("Ania", "dsdfa@gd", "cipka", "xxx");

        List<User> list2 = new ArrayList();
        list2.add(marcin);
        list2.add(tomek);
        list2.add(ania);

        return list2;
    }

    @Test
    public void should_return_list_of_userNames() {
        List<String> userNamesList = userService.getUserNamesList();

        //then
        Assert.assertEquals(new ArrayList<String>(Arrays.asList("Marcin", "Tomek", "Ania")), userNamesList);
    }
}
