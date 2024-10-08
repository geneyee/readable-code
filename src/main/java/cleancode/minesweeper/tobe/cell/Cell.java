package cleancode.minesweeper.tobe.cell;

public abstract class Cell {

    protected static final String FLAG_SIGN = "⚑";
    protected static final String UNCHECKED_SIGN = "□"; // 아직 열지 않은 셀

    protected boolean isFlagged;
    protected boolean isOpened;

    // Cell이 가진 속성 : 근처 지뢰 숫자, 지뢰여부
    // Cell의 상태 : 깃발 유무, 열렸다/닫혔다, 사용자가 확인함
    // 예를 들면 깃발이 꽂힌 셀은 깃발 o, 닫힌 셀이다.
    // (깃발이 꽂혔지만 사용자가 지뢰가 있을거라고 임의로 생각한 셀이니까 아직 닫혀있음)

    public abstract boolean isLandMine();

    public abstract boolean hasLandMineCount();

    // 셀을 직접 바꿔주는 것(SIGN 플래그 넣어주는 것)이 아니라 상태로 결정
    public abstract String getSign();

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

    public boolean isOpened() {
        return isOpened;
    }

}
