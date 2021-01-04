package kakao;

/**
 * 다트게임
 */
class Solution6 {
    public int solution(String dartResult) {

        int i = 0;
        int turn = 0;

        int[] board = new int[3];

        while(i < dartResult.length()) {

            // 점수
            int score = 0;
            while(dartResult.charAt(i) >= '0' && dartResult.charAt(i) <= '9') {
                if(score == 0) { score = dartResult.charAt(i) - '0'; }
                else { score = (score * 10) + dartResult.charAt(i) - '0';}
                ++i;
            }

            // 보너스
            // 더블일 경우 2제곱
            if(dartResult.charAt(i) == 'D') {
                score = score * score;
            }
            // 트리플일 경우 3제곱
            else if(dartResult.charAt(i) == 'T'){
                score = score * score * score;
            }

            ++i;

            if(i >= dartResult.length()) {
                board[turn] = score; break;
            }

            // 옵션 확인
            // 옵션이 있는 경우
            if(dartResult.charAt(i) == '*' || dartResult.charAt(i) == '#') {
                if(dartResult.charAt(i) == '#') {
                    board[turn] = score * (-1);
                }
                else {
                    if(turn == 0) {
                        board[turn] = score * 2;
                    }
                    else{
                        board[turn - 1] = board[turn - 1] * 2;
                        board[turn] = score * 2;
                    }
                }
                i++;
            }
            else {
                board[turn] = score;
            }

            // 턴 증가
            turn++;
        }

        return board[0] + board[1] + board[2];
    }
}