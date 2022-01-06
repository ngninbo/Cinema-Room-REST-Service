import org.hyperskill.hstest.dynamic.DynamicTest;
import org.hyperskill.hstest.dynamic.input.DynamicTesting;
import org.hyperskill.hstest.exception.outcomes.WrongAnswer;
import org.hyperskill.hstest.mocks.web.response.HttpResponse;
import org.hyperskill.hstest.stage.SpringTest;

import org.hyperskill.hstest.testcase.CheckResult;
import task.Main;

import java.util.Map;

import static org.hyperskill.hstest.testcase.CheckResult.wrong;
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


    @DynamicTest
    DynamicTesting[] dt = new DynamicTesting[]{
            () -> testPost("abc"),
            () -> testPost("123"),
    };

    CheckResult testPost(String value) {
        HttpResponse response = post("/test", Map.of("param", value)).send();

        throwIfIncorrectStatusCode(response, 200);

        if (!value.equals(response.getContent())) {
            return wrong("Expected: \"" + value + "\", received: \"" + response.getContent() + "\"");
        }

        return correct();
    }

}