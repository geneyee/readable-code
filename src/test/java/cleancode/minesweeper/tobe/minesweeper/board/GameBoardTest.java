package cleancode.minesweeper.tobe.minesweeper.board;

import cleancode.minesweeper.tobe.minesweeper.board.position.CellPosition;
import cleancode.minesweeper.tobe.minesweeper.gamelevel.Beginner;
import cleancode.minesweeper.tobe.minesweeper.gamelevel.GameLevel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class GameBoardTest {

    @DisplayName("지뢰가 있는 셀을 오픈하면 게임상태가 LOSE로 변경된다.")
    @Test
    void isLoseStatus() {
        // given
        GameLevel beginner = new Beginner();
        GameBoard gameBoard = new GameBoard(beginner);
        gameBoard.initializeGame();
        CellPosition landMinePosition = new CellPosition(3,5); // 실패..

        // when
        gameBoard.openAt(landMinePosition);

        // then
        assertThat(gameBoard.isLoseStatus()).isTrue();
    }
}