import org.hyperskill.hstest.dynamic.DynamicTest;
import org.hyperskill.hstest.dynamic.input.DynamicTesting;
import org.hyperskill.hstest.exception.outcomes.WrongAnswer;
import org.hyperskill.hstest.mocks.web.response.HttpResponse;
import org.hyperskill.hstest.stage.SpringTest;

import org.hyperskill.hstest.testcase.CheckResult;
import task.Main;

import static org.hyperskill.hstest.testcase.CheckResult.correct;


public class Tests extends SpringTest {

    public Tests() {
        super(Main.class);
    }

    static void throwIfIncorrectStatusCode(HttpResponse response, int status) {
        if (response.getStatusCode() != status) {
            throw new WrongAnswer(response.getRequest().getMethod() +
                    " " + response.getRequest().getLocalUri() +
                    " should respond with status code " + status +
                    ", responded: " + response.getStatusCode() + "\n\n" +
                    "Response body:\n" + response.getContent());
        }
    }

    final int[] IDS = new int[]{200, 300, 400, 500};


    @DynamicTest
    final DynamicTesting[] dt = new DynamicTesting[]{
            () -> testTest(IDS[0]),
            () -> testTest(IDS[1]),
            () -> testTest(IDS[2]),
            () -> testTest(IDS[3]),
    };

    CheckResult testTest(int idAndStatusCode) {
        HttpResponse response = get("/test/" + idAndStatusCode).send();

        throwIfIncorrectStatusCode(response, idAndStatusCode);

        return correct();
    }
}