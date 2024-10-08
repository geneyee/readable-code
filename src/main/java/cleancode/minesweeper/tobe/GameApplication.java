package cleancode.minesweeper.tobe;

import cleancode.minesweeper.tobe.gamelevel.Advanced;
import cleancode.minesweeper.tobe.gamelevel.Beginner;
import cleancode.minesweeper.tobe.gamelevel.GameLevel;
import cleancode.minesweeper.tobe.gamelevel.Middle;
import cleancode.minesweeper.tobe.io.ConsoleInputHandler;
import cleancode.minesweeper.tobe.io.ConsoleOutputHandler;
import cleancode.minesweeper.tobe.io.InputHandler;
import cleancode.minesweeper.tobe.io.OutputHandler;

public class GameApplication {

    public static void main(String[] args) {
        GameLevel gameLevel = new Beginner();
//        GameLevel gameLevel = new Middle();
//        GameLevel gameLevel = new Advanced();
        InputHandler inputHandler = new ConsoleInputHandler();
        OutputHandler outputHandler = new ConsoleOutputHandler();

        Minesweeper minesweeper = new Minesweeper(gameLevel, inputHandler, outputHandler);
        minesweeper.initialize();
        minesweeper.run();
    }

    /** 주의
     * DIP(Dependency Inversion Principle) : 의존성 역전
     *
     * DI(Dependency Injection) : 의존성 주입
     * -> 필요한 의존성을 내가 직접 생성하는 것이 아니라 외부에서 주입
     * -> a객체를 b객체에서 주입받아서 사용하고 싶다고 할 때, 직접 주입 받을 수 없음.
     * -> 스프링에서는 이것을 spring context가 해준다.
     *
     * IoC(Inversion of Control) : 제어의 역전
     * -> 프로그램의 흐름을 프레임워크가 제어하도록 하는 것.(생명주기 - 객체의 생성, 소멸)
     * -> 순방향은 개발자가 제어하는 것
    * */
}