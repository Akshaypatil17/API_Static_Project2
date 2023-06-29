package Test_Classes1;

import java.io.IOException;
import java.time.LocalDateTime;
import org.testng.Assert;
import Common_API_Methods.Post_API_Methods;
import RequestRepository.Post_Create_Repo;
import io.restassured.path.json.JsonPath;

public class Post_Create_TC01 
{

	public static void extractor() throws IOException
	{
		for(int i=0; i<=5; i++) {
			int statusCode = 
		Post_API_Methods.ResponseStatusCode(Post_Create_Repo.BaseURI(), Post_Create_Repo.Post_Resource(), Post_Create_Repo.Post_Req_TC1());
		System.out.println(statusCode);
		if(statusCode==201) {
		String ResponseBody =
		Post_API_Methods.ResponseBody(Post_Create_Repo.BaseURI(), Post_Create_Repo.Post_Resource(), Post_Create_Repo.Post_Req_TC1());
		System.out.println(ResponseBody);
		Post_Create_TC01.Validator(Post_Create_Repo.Post_Req_TC1(), ResponseBody);
		break;
		}
		else
		{ 
			System.out.println("Statuscode is failed");
		}
		
		}
		
		}
		public static void Validator(String RequestBody, String ResponseBody) throws IOException {
			// validate
					JsonPath JspRequest = new JsonPath(Post_Create_Repo.Post_Req_TC1());
					String Req_name = JspRequest.getString("name");
					String Req_job = JspRequest.getString("job");
					LocalDateTime currentdate = LocalDateTime.now();
					String expecteddate = currentdate.toString().substring(0, 11);
			// Create an object of JSON path to parse the response body
					JsonPath JspResponse = new JsonPath(ResponseBody);
					String Res_name = JspResponse.getString("name");
					String Res_job = JspResponse.getString("job");
					String Res_createdAt = JspResponse.getString("createdAt");
					Res_createdAt = Res_createdAt.substring(0, 11);
			// Validate the ResponseBody parameters
					Assert.assertEquals(Res_name, Req_name);
					Assert.assertEquals(Res_job, Req_job);
					Assert.assertEquals(Res_createdAt, expecteddate);
				}

	}