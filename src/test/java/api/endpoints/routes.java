package api.endpoints;

public class routes {

    // USER MODEL
    //public so that it can be accessed anywhere within the project, static so that we can access without class object
    public static String base_url = "https://petstore.swagger.io/v2";

    public static String post_url = base_url + "/user";
    public static String get_url = base_url + "/user/{username}";
    public static String put_url = base_url + "/user/{username}";
    public static String delete_url = base_url + "/user/{username}";


    //STORE MODEL

    //PET MODEL


}
