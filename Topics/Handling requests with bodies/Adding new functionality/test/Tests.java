import org.hyperskill.hstest.dynamic.DynamicTest;
import org.hyperskill.hstest.dynamic.input.DynamicTesting;
import org.hyperskill.hstest.exception.outcomes.WrongAnswer;
import org.hyperskill.hstest.mocks.web.response.HttpResponse;
import org.hyperskill.hstest.stage.SpringTest;

import static org.hyperskill.hstest.testing.expect.json.JsonChecker.*;
import static org.hyperskill.hstest.testing.expect.Expectation.expect;

import org.hyperskill.hstest.testcase.CheckResult;
import task.Main;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

    final Random rand = new Random();

    final String[] data = new String[]{
            "random data " + rand.nextInt(),
            "random data " + rand.nextInt(),
            "random data " + rand.nextInt(),
            "random data " + rand.nextInt(),
            "random data " + rand.nextInt(),
            "random data " + rand.nextInt(),
            "random data " + rand.nextInt(),
            "random data " + rand.nextInt(),
            "random data " + rand.nextInt(),
            "random data " + rand.nextInt(),
    };

    final List<Integer> dataIds = new ArrayList<>();


    @DynamicTest
    final DynamicTesting[] dt = new DynamicTesting[]{
            () -> testPostData(data[0]),
            () -> testPostData(data[1]),
            () -> testPostData(data[2]),
            () -> testPostData(data[3]),
            () -> testPostData(data[4]),
            () -> testPostData(data[5]),
            () -> testPostData(data[6]),
            () -> testPostData(data[7]),
            () -> testPostData(data[8]),
            () -> testPostData(data[9]),

            this::testIdsShouldBeUnique,

            () -> testGetData(dataIds.get(0), data[0]),
            () -> testGetData(dataIds.get(1), data[1]),
            () -> testGetData(dataIds.get(2), data[2]),
            () -> testGetData(dataIds.get(3), data[3]),
            () -> testGetData(dataIds.get(4), data[4]),
            () -> testGetData(dataIds.get(5), data[5]),
            () -> testGetData(dataIds.get(6), data[6]),
            () -> testGetData(dataIds.get(7), data[7]),
            () -> testGetData(dataIds.get(8), data[8]),
            () -> testGetData(dataIds.get(9), data[9]),
    };

    CheckResult testPostData(String data) {
        HttpResponse response = post("/api/data/new", "{\"data\":\"" + data + "\"}").send();

        throwIfIncorrectStatusCode(response, 200);

        expect(response.getContent()).asJson().check(
                isObject()
                        .value("id", isInteger(dataId -> {
                            dataIds.add(dataId);
                            return true;
                        })));

        return correct();
    }

    CheckResult testGetData(int dataId, String data) {
        HttpResponse response = get("/api/data/" + dataId).send();

        throwIfIncorrectStatusCode(response, 200);

        expect(response.getContent()).asJson().check(
                isObject()
                        .value("data", isString(data)));

        return correct();
    }

    CheckResult testIdsShouldBeUnique() {
        if (dataIds.size() != dataIds.stream().distinct().count()) {
            return wrong("The program shouldn't return the same id twice");
        }

        return correct();
    }
}