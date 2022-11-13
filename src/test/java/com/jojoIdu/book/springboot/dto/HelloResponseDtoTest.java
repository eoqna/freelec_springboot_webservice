package com.jojoIdu.book.springboot.dto;

import com.jojoIdu.book.springboot.web.dto.HelloResponseDto;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;

public class HelloResponseDtoTest {

    @Test
    public void 롬복_기능_테스트() {
        //given
        String name = "test";
        int amount = 1000;

        //when
        HelloResponseDto dto = new HelloResponseDto(name, amount);

        //then
        assertThat(dto.getName()).isEqualTo(name);
        assertThat(dto.getAmount()).isEqualTo(amount);

    }

}
