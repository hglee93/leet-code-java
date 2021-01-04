package kakao;

import java.util.*;

class Solution1 {

    class Music {
        public int playTime;
        public String title;
        public List<String> soundList;
    }

    public int getPlaytime(String start, String end) {
        String[] startArr = start.split(":");
        String[] endArr = end.split(":");

        int startMinutes = Integer.parseInt(startArr[0]) * 60 + Integer.parseInt(startArr[1]);
        int endMinutes = Integer.parseInt(endArr[0]) * 60 + Integer.parseInt(endArr[1]);

        return endMinutes - startMinutes;
    }

    public List<String> getSoundList(String music) {

        List<String> soundList = new ArrayList<String>();

        for(int i =0; i < music.length() - 1; i++) {
            if(music.charAt(i + 1) == '#') {
                soundList.add(music.substring(i, i + 2));
                i += 1;
            }
            else {
                soundList.add(music.substring(i, i + 1));
            }
        }

        if(music.charAt(music.length() - 1) != '#') {
            soundList.add(music.substring(music.length() - 1, music.length()));
        }

        return soundList;
    }

    public List<String> getPlayedMusic(int playTime, String music) {

        List<String> soundList = getSoundList(music);

        List<String> playedSound = new ArrayList<String>();

        if(playTime == soundList.size()) { return soundList; }

        for(int t = 0; t < playTime; t++) {
            playedSound.add(soundList.get(t % soundList.size()));
        }

        return playedSound;
    }

    public boolean IsContains(List<String> playedList, List<String> findList) {

        if(playedList.size() < findList.size()) { return false; }

        for(int i = 0; i < playedList.size() - findList.size() + 1; i++) {
            int j = 0;
            for(; j < findList.size(); j++) {
                if(!playedList.get(i + j).equals(findList.get(j))) { break; }
            }
            if(j == findList.size()) { return true; }
        }

        return false;
    }

    public String solution(String m, String[] musicinfos) {

        String answer = "";

        List<Music> musicList = new ArrayList<Music>();

        List<String> findList = getSoundList(m);

        for(int i = 0; i < musicinfos.length; i++) {

            String[] info = musicinfos[i].split(",");
            String startTime = info[0];
            String endTime = info[1];
            String title = info[2];
            String music = info[3];

            int playTime = getPlaytime(startTime, endTime);
            List<String> playedSoundList = getPlayedMusic(playTime, music);

            if(IsContains(playedSoundList, findList) == true) {
                Music musicObject = new Music();
                musicObject.playTime = playTime;
                musicObject.title = title;
                musicObject.soundList = playedSoundList;
                musicList.add(musicObject);
            }
        }

        if(musicList.size() == 1) { return musicList.get(0).title; }
        else if(musicList.size() > 1){
            Collections.sort(musicList, new Comparator<Music>() {
                public int compare(Music m1, Music m2) {
                    return Integer.compare(m2.playTime, m1.playTime);
                }
            });
            return musicList.get(0).title;
        }

        return "(None)";
    }
}