package HbTest;

import java.util.List;

/**
 * Created by ww on 17/2/17.
 */
public class TestAnswerResult {
    private List<TestMasker>maskers;
    private int answerResultState;
    private List<TestMasker>configurations;
    private String answerResult;

    public List<TestMasker> getMaskers() {
        return maskers;
    }

    public void setMaskers(List<TestMasker> maskers) {
        this.maskers = maskers;
    }

    public int getAnswerResultState() {
        return answerResultState;
    }

    public void setAnswerResultState(int answerResultState) {
        this.answerResultState = answerResultState;
    }

    public List<TestMasker> getConfigurations() {
        return configurations;
    }

    public void setConfigurations(List<TestMasker> configurations) {
        this.configurations = configurations;
    }

    public String getAnswerResult() {
        return answerResult;
    }

    public void setAnswerResult(String answerResult) {
        this.answerResult = answerResult;
    }
}
