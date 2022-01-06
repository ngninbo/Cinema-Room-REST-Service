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

    final int[] IDS = new int[]{2234, 234234234, 2134, 3456, 98684, 85, 8495, 48438};
    final int[] INCORRECT_IDS = new int[]{-100, -99, -2, -1, 0, 1, 2, 99, 100, 999, 1000};

    @DynamicTest
    final DynamicTesting[] dt = new DynamicTesting[]{
            () -> testDeleteParticipantsStatusCode(IDS[0], 204),
            () -> testDeleteParticipantsStatusCode(IDS[0], 404),
            () -> testDeleteParticipantsStatusCode(IDS[0], 404),
            () -> testDeleteParticipantsStatusCode(IDS[1], 204),
            () -> testDeleteParticipantsStatusCode(IDS[1], 404),
            () -> testDeleteParticipantsStatusCode(IDS[2], 204),
            () -> testDeleteParticipantsStatusCode(IDS[2], 404),
            () -> testDeleteParticipantsStatusCode(IDS[3], 204),
            () -> testDeleteParticipantsStatusCode(IDS[3], 404),
            () -> testDeleteParticipantsStatusCode(IDS[4], 204),
            () -> testDeleteParticipantsStatusCode(IDS[4], 404),
            () -> testDeleteParticipantsStatusCode(IDS[5], 204),
            () -> testDeleteParticipantsStatusCode(IDS[5], 404),
            () -> testDeleteParticipantsStatusCode(IDS[6], 204),
            () -> testDeleteParticipantsStatusCode(IDS[6], 404),
            () -> testDeleteParticipantsStatusCode(IDS[7], 204),
            () -> testDeleteParticipantsStatusCode(IDS[7], 404),
            () -> testDeleteParticipantsStatusCode(IDS[7], 404),

            () -> testDeleteParticipantsStatusCode(INCORRECT_IDS[0], 404),
            () -> testDeleteParticipantsStatusCode(INCORRECT_IDS[1], 404),
            () -> testDeleteParticipantsStatusCode(INCORRECT_IDS[2], 404),
            () -> testDeleteParticipantsStatusCode(INCORRECT_IDS[3], 404),
            () -> testDeleteParticipantsStatusCode(INCORRECT_IDS[4], 404),
            () -> testDeleteParticipantsStatusCode(INCORRECT_IDS[5], 404),
            () -> testDeleteParticipantsStatusCode(INCORRECT_IDS[6], 404),
            () -> testDeleteParticipantsStatusCode(INCORRECT_IDS[7], 404),
            () -> testDeleteParticipantsStatusCode(INCORRECT_IDS[8], 404),
            () -> testDeleteParticipantsStatusCode(INCORRECT_IDS[9], 404),
            () -> testDeleteParticipantsStatusCode(INCORRECT_IDS[10], 404),
    };

    CheckResult testDeleteParticipantsStatusCode(int id, int statusCode) {
        HttpResponse response = delete("/users/" + id).send();

        throwIfIncorrectStatusCode(response, statusCode);

        return correct();
    }
}