package TestNosql;

/**
 * Created by ww on 16/10/22.
 */

import HbTest.TestAnswerLog;
import HbTest.TestAnswerResult;
import HbTest.TestMasker;
import com.ww.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**applicationContext
 * 单元测试
 * @author marker
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-mongo.xml"})
public class TestMongo {

 @Autowired
 private MongoTemplate mongo;


    @Test
    public void testSave1(){
        User c = new User();
        c.setUserName("test155555");
        c.setPassword("test255555");
        System.out.println("uuuuuu"+mongo.toString()+mongo.getDb());
        boolean exists = mongo.collectionExists("db_mongo");
        mongo.save(c);
    }
    @Test
    public void testSave2(){
        //填充100条数据
        for (int i = 0; i < 100; i++) {
            TestAnswerLog testAnswerLog = new TestAnswerLog();
            testAnswerLog.setUserId("userId"+i);
            testAnswerLog.setAnswerDate(new Date());
            List maskers=new ArrayList<TestMasker>();
            List<TestAnswerResult> testAnswerResults = new ArrayList<TestAnswerResult>();
            //生成5条
            for (int j = 0; j <5 ; j++) {
                TestMasker testMasker = new TestMasker();
                testMasker.setId("testMasker"+i);
                testMasker.setType(j+"");
                maskers.add(testMasker);
            }
            for (int j = 0; j < 3; j++) {
                TestAnswerResult testAnswerResult = new TestAnswerResult();
                List answerMaskers=new ArrayList<TestMasker>();
                for (int k = 0; k < 9; k++) {
                    TestMasker testMasker = new TestMasker();
                    testMasker.setId("examObject1"+k);
                    testMasker.setType(k+"");
                    answerMaskers.add(testMasker);
                }
                testAnswerResult.setMaskers(answerMaskers);
                testAnswerResult.setAnswerResult(j+2+"");
                testAnswerResults.add(testAnswerResult);
            }

            testAnswerLog.setMaskers(maskers);
            testAnswerLog.setAnswerList(testAnswerResults);
            mongo.save(testAnswerLog);
        }
//        mongo.find();
    }
}
