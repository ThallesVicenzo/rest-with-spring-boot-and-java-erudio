// package br.com.erudio.integrationtests.controller.withjson;

// import static io.restassured.RestAssured.given;
// import static org.junit.Assert.assertEquals;
// import static org.junit.Assert.assertNotNull;

// import org.junit.jupiter.api.Assertions;
// import org.junit.jupiter.api.BeforeAll;
// import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
// import org.junit.jupiter.api.Order;
// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.TestMethodOrder;
// import org.springframework.boot.test.context.SpringBootTest;

// import com.fasterxml.jackson.core.JsonProcessingException;
// import com.fasterxml.jackson.databind.DeserializationFeature;
// import com.fasterxml.jackson.databind.JsonMappingException;
// import com.fasterxml.jackson.databind.ObjectMapper;

// import br.com.erudio.configs.TestConfigs;
// import br.com.erudio.integrationtests.testcontainers.AbstractIntegrationTest;
// import br.com.erudio.integrationtests.vo.PersonVO;
// import io.restassured.builder.RequestSpecBuilder;
// import io.restassured.filter.log.LogDetail;
// import io.restassured.filter.log.RequestLoggingFilter;
// import io.restassured.filter.log.ResponseLoggingFilter;
// import io.restassured.specification.RequestSpecification;

// @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
// @TestMethodOrder(OrderAnnotation.class)
// public class PersonControllerJsonTest extends AbstractIntegrationTest {

// private static RequestSpecification specification;
// private static ObjectMapper objectMapper;
// private static PersonVO personVO;

// @BeforeAll
// public static void setup() {
// objectMapper = new ObjectMapper();
// objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

// personVO = new PersonVO();
// }

// @Order(1)
// @Test
// void testCreate() throws JsonMappingException, JsonProcessingException {
// mockPerson();
// specification = new
// RequestSpecBuilder().addHeader(TestConfigs.HEADER_PARAM_ORIGIN,
// "http://localhost:8080")
// .setBasePath("/api/person/v1").setPort(TestConfigs.SERVER_PORT)
// .addFilter(new RequestLoggingFilter(LogDetail.ALL))
// .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
// .build();

// var content = given().spec(specification)
// .contentType(TestConfigs.CONTENT_TYPE_JSON)
// .body(personVO)
// .when()
// .post()
// .then()
// .statusCode(200)
// .extract()
// .body().asString();

// PersonVO cPersonVO = objectMapper.readValue(content, PersonVO.class);
// personVO = cPersonVO;
// assertNotNull(cPersonVO);

// assertNotNull(cPersonVO.getId());
// assertNotNull(cPersonVO.getFirstName());
// assertNotNull(cPersonVO.getLastName());
// assertNotNull(cPersonVO.getAddress());
// assertNotNull(cPersonVO.getGender());

// Assertions.assertTrue(cPersonVO.getId() > 0);

// assertEquals("Leal", cPersonVO.getFirstName());
// assertEquals("cuiei", cPersonVO.getLastName());
// assertEquals("Casa de andrey", cPersonVO.getAddress());
// assertEquals("Male", cPersonVO.getGender());
// }

// private void mockPerson() {
// personVO.setFirstName("Leal");
// personVO.setLastName("cuiei");
// personVO.setAddress("Casa de andrey");
// personVO.setGender("Male");
// }

// }
