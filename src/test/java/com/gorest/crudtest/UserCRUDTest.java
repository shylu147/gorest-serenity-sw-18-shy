package com.gorest.crudtest;

import com.gorest.goreststeps.UserSteps;
import com.gorest.testbase.UserTestBase;
import com.gorest.utils.TestUtils;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;
import java.util.HashSet;

import static org.hamcrest.Matchers.hasValue;

@RunWith(SerenityRunner.class)
public class UserCRUDTest extends UserTestBase {
    static String name = "Adinath Bhattacharya" + TestUtils.getRandomValue();
    static String email = "adinath_bhattacharya@cassin.example" +TestUtils.getRandomValue();
    static String gender = "male";
    static String status = "active";

    static int userId;

    @Steps
    UserSteps userSteps;
    @Title("This will create a new user")
    @Test
    public void test001(){
        userSteps.createUser(name,email,gender,status).statusCode(201);
    }
    @Title("Verify if the user added successfully")
    @Test
    public void test002(){
        HashMap<String, Object>userMap=userSteps.getUserInfoByName(name);
        Assert.assertThat(userMap, hasValue(name));
        userId = (int) userMap.get("id");

    }
    @Title("Update the user info and verify the user info updated successfully")
    @Test
    public void test003(){
        name=name+"updated";
        userSteps.updatUser(userId,name,email,gender,status);

        HashMap<String,Object>userMap=userSteps.getUserInfoByName(name);
        Assert.assertThat(userMap,hasValue(name));

    }

    @Title("Delete the user and verify that user deleted successfully")
    @Test
    public void test004(){
        userSteps.deleteUser(userId).statusCode(204);
        userSteps.deleteUser(userId).statusCode(404);
    }


}
