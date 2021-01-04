package kakao;

import java.util.*;

class Solution3 {
    class FileInfo {
        public String HEAD;
        public String NUMBER;
        public String TAIL;
    }

    public FileInfo getFileInfo(String file) {

        int startNumber = 0;
        for(; startNumber < file.length(); startNumber++) {
            if(file.charAt(startNumber) >= '0'
                    && file.charAt(startNumber) <= '9') {
                break;
            }
        }

        int endNumber = startNumber;
        for(; endNumber < file.length(); endNumber++) {
            if(!(file.charAt(endNumber) >= '0'
                    && file.charAt(endNumber) <= '9')) {
                break;
            }
        }

        FileInfo fileinfo = new FileInfo();
        fileinfo.HEAD = file.substring(0, startNumber);
        fileinfo.NUMBER = file.substring(startNumber, endNumber);
        fileinfo.TAIL = file.substring(endNumber);
        return fileinfo;
    }

    public String[] solution(String[] files) {

        FileInfo[] fileArray = new FileInfo[files.length];

        for(int i = 0; i < files.length; i++) {
            fileArray[i] = getFileInfo(files[i]);
        }

        Arrays.sort(fileArray, new Comparator<FileInfo>() {
            public int compare(FileInfo f1, FileInfo f2) {
                if(f1.HEAD.toLowerCase().equals(f2.HEAD.toLowerCase())) {
                    int number1 = Integer.parseInt(f1.NUMBER);
                    int number2 = Integer.parseInt(f2.NUMBER);
                    return Integer.compare(number1, number2);
                }
                return f1.HEAD.toLowerCase().compareTo(f2.HEAD.toLowerCase());
            }
        });

        String[] answer = new String[files.length];
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < files.length; i++) {
            sb.append(fileArray[i].HEAD);
            sb.append(fileArray[i].NUMBER);
            sb.append(fileArray[i].TAIL);
            answer[i] = sb.toString();
            sb.setLength(0);
        }

        return answer;
    }
}