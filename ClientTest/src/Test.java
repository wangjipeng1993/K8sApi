//import javax.ws.rs.core.MediaType;
//
//import org.codehaus.jettison.json.JSONArray;
//import org.codehaus.jettison.json.JSONException;
//import org.codehaus.jettison.json.JSONObject;
//
//import com.sun.jersey.api.client.Client;
//import com.sun.jersey.api.client.WebResource;

import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import net.sf.json.*;
import net.sf.json.JSONException;


/**
 * Created by root on 16-11-24.
 */
public class Test {
    public static void main(String[] argc)  {
//        Client c = Client.create();
//        WebResource r=c.resource("http://localhost:8080/RestDemo6/api/v1/namespaces/json");
//        JSONObject obj = new JSONObject();
//        obj.put("a", "1");
//        obj.put("b", "2");
//        JSONObject response = r.post(JSONObject.class, obj);
//        System.out.println(response.toString());
//        @Context HttpServletRequest request
//    }


//        String url = "http://localhost:8080/JerseyObjPar/rest";
//        ClientConfig clientConfig = new ClientConfig();
//        clientConfig.register(JacksonJsonProvider.class);
//        Client client = ClientBuilder.newClient(clientConfig);
//        WebTarget webTarget = client.target(url).path("HelloWorld").path("sayName");
//        Builder builder = webTarget.request(MediaType.APPLICATION_JSON);
//        UserBean ub = new UserBean("admin","男",121);
//        UserBean res = builder.post(Entity.entity(ub, MediaType.APPLICATION_JSON), UserBean.class);
//        System.out.println(res);


//
//        @POST
//        @Path("/json1")
//        @Produces(MediaType.TEXT_XML)
//        @Consumes(MediaType.TEXT_XML)
//        public String getJson(@Context HttpServletRequest request)  {
////        System.out.println(param);
//            return request.toString();
//        }

        String BASE_URI = "http://localhost:8080/RestDemo6";
        String PATH_NAME = "/namespaces/name/";
//        String PATH_JSON = "/namespaces/default/pods";
//        String PATH_JSON = "/namespaces/default/replicationtrollers";
        String PATH_JSON = "/namespaces/default/service";
        String name = "Pavithra";



//        String json = "jjjjj";

//        JSONObject obj = new JSONObject();String json = "{\"tenantName\": \"dzcloud-admin\",\"passwordCredentials\": {\"username\": \"dzcloud-admin\",\"password\": \"111111\"}}"
//        obj.put("a", "1");
//        obj.put("b", "2");

//        String json = "{\"tenantName\": \"dzcloud-admin\",\"passwordCredentials\": {\"username\": \"dzcloud-admin\",\"password\": \"111111\"}}";

//        String NameSpacesJson = "{\"kind\":\"Namespace\", \"apiVersion\":\"v1\", \"metadata\":{\"name\":\"javaabc\"}}";
//        String pod =  "{\"kind\":\"Pod\", \"apiVersion\":\"v1\", \"metadata\":{\"name\":\"metadanginx\", \"namespace\":\"testdefult\", " +
//                "\"labels\":[{\"abc\":\"abc\", \"iii\":\"ooo\"}]}, \"spec\":{\"containers\":{\"name\":\"nginx\", \"image\":" +
//                "\"index.alauda.cn/kiwenlau/nginx:1.9.7\", \"imagePullPolicy\":\"IfNotPresent\", \"commanad\": [\"echo\", \"echo1\"], " +
//                "\"workingDir\":\"/usr/local\", \"volumeMounts\":[{\"name\":\"data\", \"mountPath\":\"/usr/local\", \"readOnly\":\"true\"}]," +
//                "\"ports\":[{\"name\":\"wjp\",\"containerPort\":\"80\", \"protocol\":\"TCP\"}], \"env\":[{\"name\":\"aaa\", \"value\":\"bbb\"}," +
//                " {\"name\":\"ccc\",\"value\":\"ddd\"}] }," +
//                "\"volumes\":[{\"name\":\"data\", \"hostPath\":{\"path\": \"/usr/local/docker\"}}], \"restartPolicy\":\"Never\", \"dnsPolicy\":\"ClusterFirst\"}}";

//        String rc = "{\"kind\":\"ReplicationController\", \"apiVersion\":\"v1\", \"metadata\":{\"name\":\"rcanginx\"," +
//                " \"namespace\":\"testdefult\", \"labels\":[{\"abc\":\"abc\"}]}, \"spec\":{\"replicas\":" +
//                "\"1\", \"selector\":[{\"abc\":\"abc\"}], \"template\":{\"metadata\":{\"labels\":[{\"abc\":" +
//                "\"abc\"}]}, \"spec\":{\"containers\":{\"name\":\"nginx\", \"image\": \"index.alauda.cn/kiwenla" +
//                "u/nginx:1.9.7\", \"imagePullPolicy\":\"Always\", \"workingDir\":\"/usr/local\",  \"commaand\": [\"echo\", \"echo1\"]" +
//                ", \"volumeMounts\":[{\"name\":\"data\",\"mountPath\":\"/usr/local\", \"readOnly\":\"true\"}], \"ports\":[{\"name\":\"wjp\", \"co" +
//                "ntainerPort\":\"80\", \"protocol\":\"TCP\"}], \"env\":[{\"name\":\"aaa\", \"value\":\"bbb\"}, {\"name\":\"ccc\",\"value\":\"ddd\"" +
//                "}] }, \"volumes\":[{\"name\":\"data\", \"hostPath\":{\"path\": \"/usr/local/docker\"}}], \"restartPaolicy\":\"Always\", \"dnsPoalicy\":\"Default\" } } } }";

        String service = "{\"kind\": \"Service\", \"apiVersion\":\"v1\", \"metadata\":{\"name\":\"nginx\", \"labels\":" +
                "[{\"labels\":\"nginx\"}]}, \"spec\": {\"selector\": [{\"selector\":\"nginx\"}]," +
                "\"type\": \"ClusterIP\", \"clustaerIP\": \"wangjipeng\", \"ports\":[{\"port\":\"70\", \"targetPort\": \"77\"," +
                "\"protocol\": \"TCP\"}], \"staatus\":{\"loadBalaancer\": {\"ingress\": {\"ip\": \"127.0.0.1\", \"hostname\": \"wjp\"}}} }}";


//        String rc = "{\"kind\":\"ReplicationController\", \"apiVersion\":\"v1\", \"metadata\":{\"name\":\"rcnginx\"," +
//                " \"namespace\":\"defult\"}, \"spec\":{\"replicas\":" +
//                "\"3\", \"selector\":[{\"abc\":\"abc\"}], \"template\":{\"metadata\":{\"labels\":[{\"abc\":" +
//                "\"abc\"}]}, \"spec\":{\"containers\":{\"name\":\"nginx\", \"image\": \"index.alauda.cn/kiwenla" +
//                "u/nginx:1.9.7\"}} }  }}";


//        String abc = "{\"kind\":\"Pod\", \"apiVersion\":\"v1\", \"metadata\":{\"name\":\"metadanginx\", \"namespace\":\"testdefult\", " +
//                "\"labels\":[{\"abc\":\"abc\", \"iii\":\"ooo\"}]}, \"spec\":{\"containers\":{\"name\":\"nginx\", \"image\":" +
//                "\"index.alauda.cn/kiwenlau/nginx:1.9.7\", \"imagePullPolicy\":\"Always\", \"command\": [\"echo\", \"echo1\"]," +
//                "\"workingDir\":\"/usr/local\", \"volumeMounts\":[{\"name\":\"data\", \"mountPath\":\"/usr/local\", \"readOnly\":\"true\"}," +
//                "{\"name\":\"data1\", \"mountPath\":\"/usr/local1\", \"readOnly\":\"false\"}], \"ports\":[{\"name\":\"portsName\"," +
//                "\"containerPort\":\"80\", \"protocol\":\"TCP\"}, {\"name\":\"portsName1\", \"containerPort\":\"88\", \"protocol\":\"UDP\"}]," +
//                "\"env\":[{\"name\":\"aaa\", \"value\":\"bbb\"}, {\"name\":\"ccc\",\"value\":\"ddd\"}], \"resources\":{\"limits\":{" +
//                "\"cpu\":\"0.5\", \"memory\":\"128Mi\"}}}, \"volumes\":[{\"name\":\"data\", \"hostPath\":{\"path\": \"/usr/local/docker\"}}," +
//                "{\"name\":\"data1\", \"hostPath\":{\"path\": \"/usr/local/docker1\"}}], \"restartPolicy\":\"Never\", \"dnsPolicy\":" +
//                "\"Default\"}}";

//        String abc = "{\"kind\":\"Pod\", \"apiVersion\":\"v1\", \"metadata\":{\"name\":\"metadanginx\", \"namespace\":\"testdefult\", " +
//                "\"labels\":[{\"abc\":\"abc\", \"iii\":\"ooo\"}]}, \"spec\":{\"containers\":{\"name\":\"nginx\", \"image\":" +
//                "\"index.alauda.cn/kiwenlau/nginx:1.9.7\", \"imagePullPolicy\":\"Always\", \"command\": [\"echo\", \"echo1\"]," +
//                "\"workingDir\":\"/usr/local\", \"volumeMounts\":[{\"name\":\"data\", \"mountPath\":\"/usr/local\", \"readOnly\":\"true\"}," +
//                "{\"name\":\"data1\", \"mountPath\":\"/usr/local1\", \"readOnly\":\"false\"}], \"ports\":[{\"name\":\"portsName\"," +
//                "\"containerPort\":\"80\", \"protocol\":\"TCP\"}, {\"name\":\"portsName1\", \"containerPort\":\"88\", \"protocol\":\"UDP\"}]," +
//                "\"env\":[{\"name\":\"aaa\", \"value\":\"bbb\"}, {\"name\":\"ccc\",\"value\":\"ddd\"}], \"resources\":{\"limits\":{" +
//                "\"cpu\":\"0.5\", \"memory\":\"128Mi\"}}}, \"volumes\":[{\"name\":\"data\", \"hostPath\":{\"path\": \"/usr/local/docker\"}}," +
//                "{\"name\":\"data1\", \"hostPath\":{\"path\": \"/usr/local/docker1\"}}], \"restartPolicy\":\"Never\", \"dnsPolicy\":" +
//                "\"Default\"}}";
////        String json1 = json.split(",")[0];
////        System.out.println("------------------------json  " +json1);
//        JSONObject object = JSONObject.fromObject(json);
////        System.out.println("-------------1 " + object);
//        String abc;
//        try {
//             abc =  object.getString("tenantNamea");
//        } catch (JSONException e){
//             abc = "";
//        }
//        System.out.println("------------------------" + abc);
//        System.out.println("================" + abc);
//        System.out.println("-------------3 " + object.toString());

        ClientConfig config = new DefaultClientConfig();
        Client client = Client.create(config);
        WebResource resource = client.resource(BASE_URI);
//
        WebResource nameResource = resource.path("/api/v1/").path(PATH_NAME + name);
        WebResource jsonResource = resource.path("/api/v1/").path(PATH_JSON);

//        System.out.println("---------------1 " + nameResource.get(String.class));   // GET
//        System.out.println("---------------2 " + nameResource.accept(MediaType.TEXT_XML).get(String.class)); // GET canshu
//        System.out.println("---------------3 " + nameResource.accept(MediaType.TEXT_XML).get(ClientResponse.class).getStatus()); // zhuangtaima 200
//        System.out.println("---------------4 " + nameResource.accept(MediaType.TEXT_XML).get(ClientResponse.class).getEntity(String.class)); // shiti

        // POST
//        ClientResponse response = jsonResource.type(MediaType.TEXT_XML)
//                .post(ClientResponse.class);
////        System.out.println("-------------------  " + response.getStatus() );
////        System.out.println("-------------------  " + response.getEntity(String.class)) ;
//        String result = jsonResource.entity("hello").post(String.class);
//        System.out.println("---------------" + result);
//
        String en = jsonResource.entity(service).post(String.class);
        System.out.println(en);

//        jsonResource.entity(object).post(String.class);

//        MultivaluedMap formData = new MultivaluedMapImpl();

//        System.out.println("Client Response \n"
//                + getClientResponse(nameResource));
//        System.out.println("Response \n" + getResponse(nameResource) + "\n\n");




    }


    private static String getClientResponse(WebResource resource) {
        return resource.accept(MediaType.TEXT_XML).get(ClientResponse.class)
                .toString();
    }


    private static String getResponse(WebResource resource) {
        return resource.accept(MediaType.TEXT_XML).get(String.class);
    }

}