import org.hyperskill.hstest.dynamic.DynamicTest;
import org.hyperskill.hstest.dynamic.input.DynamicTesting;
import org.hyperskill.hstest.exception.outcomes.WrongAnswer;
import org.hyperskill.hstest.mocks.web.response.HttpResponse;
import org.hyperskill.hstest.stage.SpringTest;

import static org.hyperskill.hstest.testing.expect.json.JsonChecker.*;
import static org.hyperskill.hstest.testing.expect.Expectation.expect;

import org.hyperskill.hstest.testcase.CheckResult;
import task.Main;

import java.util.Random;

import static org.hyperskill.hstest.testcase.CheckResult.correct;


import com.google.gson.Gson;


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

    static class Message {
        final String importantMessage;

        public Message(String msg) {
            this.importantMessage = msg;
        }
    }

    final Random rand = new Random();
    final Gson gson = new Gson();

    final Message[] MESSAGES = new Message[]{
            new Message("Message " + rand.nextInt()),
            new Message("Message " + rand.nextInt()),
            new Message("Message " + rand.nextInt()),
    };

    final String[] JSON_MESSAGES = new String[]{
            gson.toJson(MESSAGES[0]),
            gson.toJson(MESSAGES[1]),
            gson.toJson(MESSAGES[2]),
    };

    final String MESSAGE = "/message";


    @DynamicTest
    final DynamicTesting[] dt = new DynamicTesting[]{
            () -> testPostMessage(JSON_MESSAGES[0]),
            () -> testGetMessage(MESSAGES[0]),

            () -> testPostMessage(JSON_MESSAGES[1]),
            () -> testGetMessage(MESSAGES[1]),

            () -> testPostMessage(JSON_MESSAGES[2]),
            () -> testPostMessage(JSON_MESSAGES[2]),
            () -> testGetMessage(MESSAGES[2]),
            () -> testGetMessage(MESSAGES[2]),
    };

    CheckResult testPostMessage(String jsonMessage) {
        HttpResponse response = post(MESSAGE, jsonMessage).send();

        throwIfIncorrectStatusCode(response, 200);

        return correct();
    }

    CheckResult testGetMessage(Message message) {
        HttpResponse response = get(MESSAGE).send();

        throwIfIncorrectStatusCode(response, 200);

        expect(response.getContent()).asJson().check(
                isObject()
                        .value("importantMessage", isString(message.importantMessage)));

        return correct();
    }
}