package cleancode.minesweeper.tobe.minesweeper.io;

import cleancode.minesweeper.tobe.minesweeper.user.UserAction;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;


class ConsoleInputHandlerTest {

    @DisplayName("사용자가 1을 입력하면 셀을 연다.")
    @Test
    void getUserActionOpen() {
        // given
        ConsoleInputHandler inputHandler = new ConsoleInputHandler();
        String userInput = "1"; // 사용자가 입력한 값
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));
        Scanner scanner = new Scanner(System.in);

        // when
        UserAction action = inputHandler.getUserActionFromUser(); //java.util.NoSuchElementException: No line found

        // then
        assertThat(action).isEqualTo(UserAction.OPEN);
    }

}