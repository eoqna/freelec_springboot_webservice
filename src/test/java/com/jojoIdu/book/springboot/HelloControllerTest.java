package com.jojoIdu.book.springboot;

import com.jojoIdu.book.springboot.web.HelloController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class) // 테스트를 진행할 때 JUnit에 내장된 실행자 외에 다른 실행자, 즉 SpringRunner라는 스프링 실행자를 사용한다.
@WebMvcTest(controllers = HelloController.class) // 여러 스프링 테스트 어노테이션 중, Web(Spring MVC)에 집중할 수 있는 어노테이션이다.
                                                 // @Controller, @ControllerAdvice 등을 사용할 수 있고,
                                                 // @Service, @Component, @Repository등을 사용할 수 없다.
public class HelloControllerTest {

    @Autowired // 스프링이 관리하는 빈(Bean)을 주입 받는다.
    // 웹 API를 테스트할 때 사용한다.
    // 스프링 MVC 테스트의 시작점이다.
    // 이 클래스를 통해 HTTP GET,POST 등에 대한 API 테스트를 할 수 있다.
    private MockMvc mvc;

    @Test
    public void hello가_리턴된다() throws Exception {
        String hello = "hello";

        // MockMvc를 통해 "/hello" 주소로 HTTP GET 요청을 한다.
        mvc.perform(get("/hello"))
                .andExpect(status().isOk()) // mvc.perform의 결과를 검증한다. HTTP Header의 Status를 검증한다 (200, 404, 500)
                .andExpect(content().string(hello)); // mvc.perform의 결과를 검증한다. 응답 본문의 내용을 검증한다.

    }

    @Test
    public void helloDto가_리턴된다() throws Exception {
        String name = "hello";
        int amount = 1000;

        mvc.perform(get("/hello/dto")
                .param("name",name)
                .param("amount",String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.amount", is(amount)));

    }

}
