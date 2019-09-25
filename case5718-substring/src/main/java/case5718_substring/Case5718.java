package case5718_substring;

import java.util.ArrayList;
import java.util.List;

public class Case5718 {
    private int numberOfOccurrences;
    private List<Integer> positionList = new ArrayList<>();

    public int findFirstPositionOfPatternInTheText(String pattern, String text) {
        return text.indexOf(pattern);
    }

    public int getNumberOfOccurrences() {
        return numberOfOccurrences;
    }

    public List<Integer> getPositionList() {
        return positionList;
    }

    public void proceed(String pattern, String text) {
        numberOfOccurrences = 0;
        positionList.clear();
        String currentText = text;
        int patternLength = pattern.length();
        int lastFoundPosition = -patternLength;
        while (patternLength <= currentText.length()) {
            int foundPosition = findFirstPositionOfPatternInTheText(pattern, currentText);
            if (foundPosition < 0) {
                break;
            }
            numberOfOccurrences++;
            lastFoundPosition = lastFoundPosition + patternLength + foundPosition;
            positionList.add(lastFoundPosition);
            currentText = currentText.substring(foundPosition + patternLength);
        }
    }

}
