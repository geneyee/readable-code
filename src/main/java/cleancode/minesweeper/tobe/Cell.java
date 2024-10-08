package cleancode.minesweeper.tobe;

public class Cell {

    private static final String FLAG_SIGN = "⚑";
    private static final String LAND_MINE_SIGN = "☼";
    private static final String UNCHECKED_SIGN = "□"; // 아직 열지 않은 셀
    private static final String EMPTY_SIGN = "■"; // 열었지만 비어있는 셀

    private int nearbyLandMineCount;
    private boolean isLandMine;
    private boolean isFlagged;
    private boolean isOpened;

    // Cell이 가진 속성 : 근처 지뢰 숫자, 지뢰여부
    // Cell의 상태 : 깃발 유무, 열렸다/닫혔다, 사용자가 확인함
    // 예를 들면 깃발이 꽂힌 셀은 깃발 o, 닫힌 셀이다.
    // (깃발이 꽂혔지만 사용자가 지뢰가 있을거라고 임의로 생각한 셀이니까 아직 닫혀있음)

    // 정적팩토리 메서드의 사용으로 생성자의 public을 private로 변경
    private Cell(int nearbyLandMineCount, boolean isLandMine, boolean isFlagged, boolean isOpened) {
        this.nearbyLandMineCount = nearbyLandMineCount;
        this.isLandMine = isLandMine;
        this.isFlagged = isFlagged;
    }

    // 정적팩토리 메서드(강사님의 방법) - 이름을 별도로 줄 수 있음
    // 정적팩토리 메서드로 생성자의 역할을 대신함
    public static Cell of(int nearbyLandMineCount, boolean isLandMine, boolean isFlagged, boolean isOpened) {
        return new Cell(nearbyLandMineCount, isLandMine, isFlagged, isOpened);
    }

    // 빈 셀 생성
    public static Cell create() {
        return of(0, false, false, false);
    }

    // setter 대신 메서드
    public void turnOnLandMine() {
        this.isLandMine = true;
    }

    // setter 대신
    public void updateNearbyLandMineCount(int count) {
        this.nearbyLandMineCount = count;
    }

    // 깃발이 꽂힌 상태
    public void flage() {
        this.isFlagged = true;
    }

    public void open() {
        this.isOpened = true;
    }

    public boolean isChecked() {
        return isFlagged || isOpened; // 깃발이 꽂혀있거나 열려있거나
    }

    public boolean isLandMine() {
        return isLandMine;
    }

    public boolean isOpened() {
        return isOpened;
    }

    public boolean hasLandMineCount() {
        return this.nearbyLandMineCount != 0;
    }

    // 셀을 직접 바꿔주는 것(SIGN 플래그 넣어주는 것)이 아니라 상태로 결정
    public String getSign() {
        if (isOpened) {
            if (isLandMine) {
                return LAND_MINE_SIGN;
            }
            if (hasLandMineCount()) {
                return String.valueOf(nearbyLandMineCount);
            }
            return EMPTY_SIGN;
        }

        if (isFlagged) {
            return FLAG_SIGN;
        }
        return UNCHECKED_SIGN;
    }
}
