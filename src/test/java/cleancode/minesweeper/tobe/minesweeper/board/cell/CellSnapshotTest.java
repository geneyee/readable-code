package cleancode.minesweeper.tobe.minesweeper.board.cell;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CellSnapshotTest {

    @DisplayName("빈 셀을 생성한다.")
    @Test
    void ofEmpty() {
        // given
        CellSnapshot cellSnapshot;

        // when
        cellSnapshot = CellSnapshot.ofEmpty();

        // then
        assertThat(cellSnapshot.getStatus()).isEqualTo(CellSnapshotStatus.EMPTY);
    }

    @DisplayName("지뢰 셀을 생성한다.")
    @Test
    void ofLandMine() {
        // given
        CellSnapshot cellSnapshot;

        // when
        cellSnapshot = CellSnapshot.ofLandMine();

        // then
        assertThat(cellSnapshot.getStatus()).isEqualTo(CellSnapshotStatus.LAND_MINE);
    }

    @DisplayName("숫자 셀을 생성하고 근처 지뢰 개수를 확인한다.")
    @Test
    void ofNumber() {
        // given
        CellSnapshot cellSnapshot;
        int nearbyLandMineCount = 5;

        // when
        cellSnapshot = CellSnapshot.ofNumber(nearbyLandMineCount);

        // then
        assertThat(cellSnapshot.getStatus()).isEqualTo(CellSnapshotStatus.NUMBER);
        assertThat(cellSnapshot.getNearbyLandMineCount()).isEqualTo(nearbyLandMineCount);
    }

    @DisplayName("열지 않은 셀을 생성한다.")
    @Test
    void ofUnchecked() {
        // given
        CellSnapshot cellSnapshot;

        // when
        cellSnapshot = CellSnapshot.ofUnchecked();

        // then
        assertThat(cellSnapshot.getStatus()).isEqualTo(CellSnapshotStatus.UNCHECKED);
    }
}