package hello.springmvc.response;

import hello.springmvc.basic.HelloData;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@Controller
public class ResponseBodyController {

    @GetMapping("/response-body-string-v1")
    public void responseBodyV1(HttpServletResponse response) throws IOException {
        response.getWriter().write("ok");
    }

    /**
     * HttpEntity, ResponseEntity(Http Status 추가)
     * <p>
     * ResponseEntity 엔티티는 HttpEntity를 상속받았는데, HttpEntity는 HTTP메세지의 헤더, 바디 정보를 가지고 있다면
     * ResponseEntity는 HTTP 응답코드가 추가되었다고 생각하면 된다.
     */
    @GetMapping("/response-body-string-v2")
    public ResponseEntity<String> responseBodyV2() {
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

    /**
     * @ResponseBody 애노테이션을 사용하면 view 를 사용하지 않고 HTTP 메세지 컨버터를 통해 HTTP 메세지를 직접 입력할 수 있다
     * ResponseEntity도 동일한 방식으로 동작한다.
     */

    @ResponseBody
    @GetMapping("/response-body-string-v3")
    public String responseBodyV3() {
        return "ok";
    }

    /**
     * ResponseEntity를 반환한다. HTTP 메세지 컨버터를 통해서 객체는 JSON으로 변환되어 반환된다.
     */
    @GetMapping("/response-body-json-v1")
    public ResponseEntity<HelloData> responseBodyJsonV1() {
        HelloData helloData = new HelloData();
        helloData.setUsername("userA");
        helloData.setAge(20);
        return new ResponseEntity<>(helloData, HttpStatus.OK);
    }

    /**
     * ResponseEntity는 HTTP 응답 코드를 설정할 수 있는데 @ResponseBody를 사용하면 설정하기가 까다롭다.
     * <p>
     * 그래서 이런 경우에는 @ResponseStatus 애노테이션을 이용하여 상태코드를 설정할 수 있다.
     * <p>
     * 정적으로 상태코드를 작성한 것이기에 유연하지는 못하다.
     * <p>
     * 그렇기에 동적으로 상태코드가 변경되야하는 상황이라면 ResponseEntity를 사용하면 된다.
     */
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @GetMapping("/response-body-json-v2")
    public HelloData responseBodyJsonV2() {
        HelloData helloData = new HelloData();
        helloData.setUsername("userA");
        helloData.setAge(20);
        return helloData;
    }
}
