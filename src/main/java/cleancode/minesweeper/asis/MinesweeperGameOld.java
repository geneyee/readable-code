package cleancode.minesweeper.asis;

import cleancode.minesweeper.tobe.GameException;
import cleancode.minesweeper.tobe.Cell;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class MinesweeperGameOld {
    // 상수 추출 단축키 ctrl alt c
    // 메서드 추출 단축키 ctrl alt m
    // 변수명 변경 단축키 shift f6
    public static final int BOARD_ROW_SIZE = 8;
    public static final int BOARD_COL_SIZE = 10;
    public static final Scanner SCANNER = new Scanner(System.in);
//    private static final String[][] BOARD = new String[BOARD_ROW_SIZE][BOARD_COL_SIZE]; //지뢰게임 판
    private static final Cell[][] BOARD = new Cell[BOARD_ROW_SIZE][BOARD_COL_SIZE]; //지뢰게임 판
//    private static final Integer[][] NEARBY_LAND_MINE_COUNTS = new Integer[BOARD_ROW_SIZE][BOARD_COL_SIZE]; // 지뢰숫자
//    private static final boolean[][] LAND_MINES = new boolean[BOARD_ROW_SIZE][BOARD_COL_SIZE]; // 하나의 셀이 지뢰인지 아닌지 판단하는
    public static final int LAND_MINE_COUNT = 10;
//    public static final String FLAG_SIGN = "⚑";
//    public static final String LAND_MINE_SIGN = "☼";
//    public static final String CLOSED_CELL_SIGN = "□";
//    public static final String OPENED_CELL_SIGN = "■";

    private static int gameStatus = 0; // 0: 게임 중, 1: 승리, -1: 패배

    public static void main(String[] args) {
        showGameStartComments();
        initializeGame();

        while (true) {
            try {
                showBoard();

                if (doesUserWinTheGame()) {
                    System.out.println("지뢰를 모두 찾았습니다. GAME CLEAR!");
                    break;
                }
                if (doesUserLoseTheGame()) {
                    System.out.println("지뢰를 밟았습니다. GAME OVER!");
                    break;
                }

                String cellInput = getCellInputFromUser();
                String userActionInput = getUserActionInputFromUser();
                actOnCell(cellInput, userActionInput);
//            } catch (IllegalArgumentException e) {
            } catch (GameException e) { //우리가 의도적으로 던진 예외
                System.out.println(e.getMessage());
            } catch (Exception e) { // 예상하지 못한 예외 처리
                System.out.println("프로그램에 문제가 생겼습니다.");
                e.printStackTrace(); // 실무에서는 log 남김
            }
        }
    }

    private static void actOnCell(String cellInput, String userActionInput) {
        int selectedColIndex = getSelectedColIndex(cellInput);
        int selectedRowIndex = getSelectedRowIndex(cellInput);

        if (doesUserChooseToPlantFlag(userActionInput)) {
//            BOARD[selectedRowIndex][selectedColIndex] = Cell.ofFlage();
            BOARD[selectedRowIndex][selectedColIndex].flage(); // 깃발이 꽂힌 상태
            checkIfGameIsOver();
            return;
        }

        if (doesUserChooseToOpenCell(userActionInput)) {
            if (isLandMineCell(selectedRowIndex, selectedColIndex)) {
                BOARD[selectedRowIndex][selectedColIndex].open();
                changeGameStatusToLose();
                return;
            }

            open(selectedRowIndex, selectedColIndex);
            checkIfGameIsOver();
            return;
        }
//        System.out.println("잘못된 번호를 선택하셨습니다.");
//        throw new IllegalArgumentException("잘못된 번호를 선택하셨습니다."); // 의도하지 않은 예외
        throw new GameException("잘못된 번호를 선택하셨습니다."); // 의도한 예외
    }

    private static void changeGameStatusToLose() {
        gameStatus = -1;
    }

    private static boolean isLandMineCell(int selectedRowIndex, int selectedColIndex) {
        return BOARD[selectedRowIndex][selectedColIndex].isLandMine();
    }

    private static boolean doesUserChooseToOpenCell(String userActionInput) {
        return userActionInput.equals("1");
    }

    private static boolean doesUserChooseToPlantFlag(String userActionInput) {
        return userActionInput.equals("2");
    }

    private static int getSelectedRowIndex(String cellInput) {
        char cellInputRow = cellInput.charAt(1);
        return convertRowFrom(cellInputRow);
    }

    private static int getSelectedColIndex(String cellInput) {
        char cellInputCol = cellInput.charAt(0);
        return convertColFrom(cellInputCol);
    }

    private static String getUserActionInputFromUser() {
        System.out.println("선택한 셀에 대한 행위를 선택하세요. (1: 오픈, 2: 깃발 꽂기)");
        return SCANNER.nextLine();
    }

    private static String getCellInputFromUser() {
        System.out.println("선택할 좌표를 입력하세요. (예: a1)");
        return SCANNER.nextLine();
    }

    private static boolean doesUserLoseTheGame() {
        return gameStatus == -1;
    }

    private static boolean doesUserWinTheGame() {
        return gameStatus == 1;
    }

    private static void checkIfGameIsOver() {
        boolean isAllChecked = isAllCellChecked();
        if (isAllChecked) {
            changeGameStatusToWin();
        }
    }

    private static void changeGameStatusToWin() {
        gameStatus = 1;
    }
    // 모든 셀이 다 열려있는지 유무 확인
    private static boolean isAllCellChecked() {
        return Arrays.stream(BOARD)
                .flatMap(Arrays::stream)
                .allMatch(Cell::isChecked); // 모든 셀이 다 체크 됐는지
//                .noneMatch(Cell::isUnchecked); 체크되지 않은 셀이 없는지 = 다 체크해야 한다.(부정부정)
//                .noneMatch(Cell::isClosed); //nullpointException 방지
                // .noneMatch(cell -> cell.equals(CLOSED_CELL_SIGN));
    }

/*    // stream 풀어서
    private static boolean isAllCellOpened2() {
        Stream<String[]> streamArrayStream = Arrays.stream(BOARD);
        Stream<String> stringStream = streamArrayStream
                .flatMap(stringArray -> {
                    Stream<String> stringStream2 = Arrays.stream(stringArray);
                    return stringStream2;
                });
        return stringStream
                .noneMatch(cell -> cell.equals(CLOSED_CELL_SIGN));
    }*/
/*
    private static boolean isAllCellOpenedOld() {
        boolean isAllOpened = true;
        for (int row = 0; row < BOARD_ROW_SIZE; row++) {
            for (int col = 0; col < BOARD_COL_SIZE; col++) {
                if (BOARD[row][col].equals(CLOSED_CELL_SIGN)) {
                    isAllOpened = false;
                }
            }
        }
        return isAllOpened;
    }*/

    private static int convertRowFrom(char cellInputRow) {
        int rowIndex = Character.getNumericValue(cellInputRow) - 1;
        if (rowIndex >= BOARD_ROW_SIZE) {
            throw new GameException("잘못된 입력입니다.");
//            throw new IllegalArgumentException("잘못된 입력입니다.");
        }

        return rowIndex;
    }

    private static int convertColFrom(char cellInputCol) {
        switch (cellInputCol) {
            case 'a':
                return 0;
            case 'b':
                return 1;
            case 'c':
                return 2;
            case 'd':
                return 3;
            case 'e':
                return 4;
            case 'f':
                return 5;
            case 'g':
                return 6;
            case 'h':
                return 7;
            case 'i':
                return 8;
            case 'j':
                return 9;
            default:
//                return -1;
//                throw new IllegalArgumentException("잘못된 값을 입력하셨습니다.");
                throw new GameException("잘못된 값을 입력하셨습니다.");
        }
    }
    // 콘솔에 보드판 그리는 메서드
    private static void showBoard() {
        System.out.println("   a b c d e f g h i j");
        for (int row = 0; row < BOARD_ROW_SIZE; row++) {
            System.out.printf("%d  ", row + 1);
            for (int col = 0; col < BOARD_COL_SIZE; col++) {
                System.out.print(BOARD[row][col].getSign() + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static void initializeGame() {
        for (int row = 0; row < BOARD_ROW_SIZE; row++) {
            for (int col = 0; col < BOARD_COL_SIZE; col++) {
//                BOARD[row][col] = Cell.ofClosed();
                BOARD[row][col] = Cell.create(); // 빈셀을 만들어서 board에 할당
            }
        }

        for (int i = 0; i < LAND_MINE_COUNT; i++) {
            int col = new Random().nextInt(BOARD_COL_SIZE);
            int row = new Random().nextInt(BOARD_ROW_SIZE);
//            LAND_MINES[row][col] = true; // 지뢰 설치
            BOARD[row][col].turnOnLandMine(); // 지뢰셀
        }

        for (int row = 0; row < BOARD_ROW_SIZE; row++) {
            for (int col = 0; col < BOARD_COL_SIZE; col++) {
                if (isLandMineCell(row, col)) {
//                    NEARBY_LAND_MINE_COUNTS[row][col] = 0;
                    continue;
                }
                int count = countNearbyLandMines(row, col);
//                NEARBY_LAND_MINE_COUNTS[row][col] = count;
                BOARD[row][col].updateNearbyLandMineCount(count);


/*                if (!isLandMineCell(row, col)) { // 지뢰셀이 아니라면
                    int count = countNearbyLandMines(row, col);
                    NEARBY_LAND_MINE_COUNTS[row][col] = count;
                    continue;
                }
                // 지뢰셀이라면
                NEARBY_LAND_MINE_COUNTS[row][col] = 0;*/
            }
        }
    }

    private static int countNearbyLandMines(int row, int col) {
        int count = 0;
        if (row - 1 >= 0 && col - 1 >= 0 && isLandMineCell(row - 1, col - 1)) {
            count++;
        }
        if (row - 1 >= 0 && isLandMineCell(row - 1, col)) {
            count++;
        }
        if (row - 1 >= 0 && col + 1 < BOARD_COL_SIZE && isLandMineCell(row - 1, col + 1)) {
            count++;
        }
        if (col - 1 >= 0 && isLandMineCell(row, col - 1)) {
            count++;
        }
        if (col + 1 < BOARD_COL_SIZE && isLandMineCell(row, col + 1)) {
            count++;
        }
        if (row + 1 < BOARD_ROW_SIZE && col - 1 >= 0 && isLandMineCell(row + 1, col - 1)) {
            count++;
        }
        if (row + 1 < BOARD_ROW_SIZE && isLandMineCell(row + 1, col)) {
            count++;
        }
        if (row + 1 < BOARD_ROW_SIZE && col + 1 < BOARD_COL_SIZE && isLandMineCell(row + 1, col + 1)) {
            count++;
        }
        return count;
    }

    private static void showGameStartComments() {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        System.out.println("지뢰찾기 게임 시작!");
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
    }
    // open(selectedRowIndex, selectedColIndex); 의 open 메소드
    // 재귀함수 - 지뢰찾기에서 어떠한 셀을 선택했을 때, 그 주변에 인접한 셀이 비어있으면 한 번에 열리는 것을 나타내기 위해
    // 이 재귀함수는 인접한 비어있는 셀을 열고 숫자이거나 지뢰일 때 멈춘다.
    private static void open(int row, int col) {
        if (row < 0 || row >= BOARD_ROW_SIZE || col < 0 || col >= BOARD_COL_SIZE) {// 판 안에 있는지 확인
            return;// 저 범위를 벗어나면 정상적이지 않은 것이니 return
        }
//        if (BOARD[row][col].doesNotClosed()) {// 이미 열렸는지 확인
        if (BOARD[row][col].isOpened()) {// 이미 열렸는지 확인
            return;// 이미 열렸다면 return
        }
        if (isLandMineCell(row, col)) {// 지뢰 셀인지 확인
            return;
        }

        BOARD[row][col].open();

//        if (NEARBY_LAND_MINE_COUNTS[row][col] != 0) {// 지뢰카운트가 있다면
        if (BOARD[row][col].hasLandMineCount()) {// 지뢰카운트가 있다면 - 숫자가 있다면
//            BOARD[row][col] = Cell.ofNearbyLandMineCount((NEARBY_LAND_MINE_COUNTS[row][col]));// 숫자를 표기하고
//            BOARD[row][col].open();
            return;
        }
//        else {// 아무것도 아니라면
////            BOARD[row][col] = Cell.ofOpened();// 열려있는 빈 셀
//            BOARD[row][col].open();
//        }
        // 선택한 셀 주변에 있는 셀들을 재귀함수로 도는 것
        open(row - 1, col - 1);
        open(row - 1, col);
        open(row - 1, col + 1);
        open(row, col - 1);
        open(row, col + 1);
        open(row + 1, col - 1);
        open(row + 1, col);
        open(row + 1, col + 1);
    }

}