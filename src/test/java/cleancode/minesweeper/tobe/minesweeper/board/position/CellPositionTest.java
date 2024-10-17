package cleancode.minesweeper.tobe.minesweeper.board.position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;


class CellPositionTest {

    @DisplayName("Cellposition 생성")
    @Test
    void createValidCellPosition() {
        // given
        int rowIndex = 8;
        int colIndex = 10;

        // when
        CellPosition position = new CellPosition(rowIndex, colIndex);

        // then
        assertThat(position.getRowIndex()).isEqualTo(rowIndex);
        assertThat(position.getColIndex()).isEqualTo(colIndex);
    }

    @DisplayName("0 미만의 인덱스는 CellPosition를 생성할 수 없다.")
    @Test
    public void testCreateInvalidCellPosition() {
        // given
        int rowIndex = -1;
        int colIndex = 0;

        // when, then
        assertThatThrownBy(() -> new CellPosition(rowIndex, colIndex))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("올바르지 않은 좌표입니다.");
    }
}