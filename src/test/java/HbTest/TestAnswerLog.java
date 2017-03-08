package HbTest;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * Created by ww on 17/2/17.
 */
@Data
public class TestAnswerLog {
    private String userId;
    private Date answerDate;
    private List<TestMasker> maskers;
    private List<TestAnswerResult>answerList;

    public List<TestMasker> getMaskers() {
        return maskers;
    }

    public List<TestAnswerResult> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(List<TestAnswerResult> answerList) {
        this.answerList = answerList;
    }

    public void setMaskers(List<TestMasker> maskers) {
        this.maskers = maskers;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }



    public Date getAnswerDate() {
        return answerDate;
    }

    public void setAnswerDate(Date answerDate) {
        this.answerDate = answerDate;
    }

}
