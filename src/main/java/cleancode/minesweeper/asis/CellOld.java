//package cleancode.minesweeper.tobe;
//
//public class CellOld {
//
//    private static final String FLAG_SIGN = "⚑";
//    private static final String LAND_MINE_SIGN = "☼";
//    private static final String CLOSED_CELL_SIGN = "□";
//    private static final String OPENED_CELL_SIGN = "■";
//
//    private final String sign;
//    private int nearbyLandMineCount;
//    private boolean isLandMine;
//    private boolean isFlaged;
//
//    // Cell이 가진 속성 : 근처 지뢰 숫자, 지뢰여부
//    // Cell의 상태 : 깃발 유무, 열렸다/닫혔다, 사용자가 확인함
//    // 예를 들면 깃발이 꽂힌 셀은 깃발 o, 닫힌 셀이다.
//    // (깃발이 꽂혔지만 사용자가 지뢰가 있을거라고 임의로 생각한 셀이니까 아직 닫혀있음)
//
//    // 정적팩토리 메서드의 사용으로 생성자의 public을 private로 변경
//    private CellOld(String sign) {
//        this.sign = sign;
//    }
//
//    // 정적팩토리 메서드(강사님의 방법) - 이름을 별도로 줄 수 있음
//    // 정적팩토리 메서드로 생성자의 역할을 대신함
//    public static CellOld of(String sign) {
//        return new CellOld(sign);
//    }
//
//    public static CellOld ofFlage() {
//        return of(FLAG_SIGN);
//    }
//
//    public static CellOld ofLandMine() {
//        return of(LAND_MINE_SIGN);
//    }
//
//    public static CellOld ofClosed() {
//        return of(CLOSED_CELL_SIGN);
//    }
//
//    public static CellOld ofOpened() {
//        return of(OPENED_CELL_SIGN);
//    }
//
//    public static CellOld ofNearbyLandMineCount(int count) {
//        return of(String.valueOf(count));
//    }
//
//    /*
//        public boolean equalsSign(String sign) {
//            return this.sign.equals(sign);
//        }
//
//        public boolean doesNotEqualSign(String sign) {
//            return !equalsSign(sign); // 기존에 존재하는 equalsSign메서드 사용할 수 있으므로 사용
//        }
//    */
//    public String getSign() {
//        return sign;
//    }
//
//    public boolean isClosed() {
//        return CLOSED_CELL_SIGN.equals(this.sign);
//    }
//
//    public boolean doesNotClosed() {
//        return !isClosed();
//    }
//}
//
