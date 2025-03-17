package demoqa;

import com.github.javafaker.Faker;
import demoqa.response.Book;
import demoqa.response.GenerateTokenSuccess;
import demoqa.response.GetAllBooksSuccess;
import demoqa.response.ResponseAccountSuccess;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class BookStoreTest {
    String username;
    String token;
    String userID;


    @Test
    public void metodaTest(){
        createAccount();
        generateToken();
        getAccount();
        getAllBooks();

    }

    public void getAllBooks(){
        RequestSpecification request = createRequest(false);
        Response response = request.get("BookStore/v1/Books");

        GetAllBooksSuccess allBooks = response.body().as(GetAllBooksSuccess.class);
        assertBookResponse(allBooks.getBooks());
    }

    private void assertBookResponse(List<Book> books){
        assertEquals(books.size(), 8);

        Set<String> setISBN = new HashSet<>();

        for (Book book:books){
            setISBN.add(book.getIsbn());
            assertBook(book);
        }
        //check ISBN is unique
        assertEquals(setISBN.size(), books.size());
    }

    private void assertBook(Book book){
        assertNotNull(book.getAuthor());
        assertNotNull(book.getDescription());
        assertNotNull(book.getPublisher());
        assertNotNull(book.getPages());
        assertNotNull(book.getPublish_date());
        assertNotNull(book.getSubTitle());
        assertNotNull(book.getTitle());
        assertNotNull(book.getWebsite());
        assertNotNull(book.getIsbn());
    }

    public void createAccount() {
        Faker faker = new Faker();
        username = faker.name().firstName() + faker.number().digits(5);

        RequestSpecification request = createRequest(false);
        request.body(getCreateAccountData());

        Response response = request.post("Account/v1/User");

        ResponseAccountSuccess responseAccountSuccess = response.body().as(ResponseAccountSuccess.class);
        userID = responseAccountSuccess.getUserID();

    }

    public void generateToken(){

        RequestSpecification request = createRequest(false);
        request.body(getCreateAccountData());
        Response responseToken = request.post("Account/v1/GenerateToken");
        GenerateTokenSuccess generateTokenSuccess = responseToken.body().as(GenerateTokenSuccess.class);
        token = generateTokenSuccess.getToken();

        assertEquals(generateTokenSuccess.getResult(), "User authorized successfully.");
        assertEquals(generateTokenSuccess.getStatus(), "Success");

    }

    public void getAccount() {
        RequestSpecification request = createRequest(true);
        Response responseAccount = request.get("Account/v1/User/" + userID);

        System.out.println(responseAccount.body().prettyPrint());

    }

    private String getCreateAccountData(){
        JSONObject requestParam = new JSONObject();
        requestParam.put("userName", username);
        requestParam.put("password", "Parola123!");

        return requestParam.toJSONString();
    }

    public RequestSpecification createRequest(Boolean needAuthorization ){
        RestAssured.baseURI = "https://demoqa.com/";
        RequestSpecification request = RestAssured.given();

        request.header("Content-Type", "application/json");
        request.header("Accept", "application/json");
        if (needAuthorization) {
            request.header("Authorization", "Bearer " + token);
        }

        return request;
    }
}
