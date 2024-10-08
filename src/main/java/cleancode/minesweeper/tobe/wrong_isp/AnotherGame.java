package cleancode.minesweeper.tobe.wrong_isp;

import cleancode.minesweeper.tobe.game.GameRunnable;

public class AnotherGame implements GameRunnable {

    @Override
    public void run() {
        // 게임 진행
    }
}
/*
// ISP 위반 예시
public class AnotherGame implements Game {
    @Override
    public void initialize() {
        // 이 클래스는 초기화가 필요 없는 로직이라고 가정한다면.
        // initialize()의 기능이 변경된다면 이 클래스도 같이 영향을 받는다.
    }

    @Override
    public void run() {
        // 게임 진행
    }
}*/


