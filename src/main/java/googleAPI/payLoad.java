package googleAPI;

public class payLoad {
    public static String getPostData(){
        String body1 = "{\n" +
                "  \"location\": {\n" +
                "    \"lat\": -33.8669710,\n" +
                "    \"lng\": 151.1958750\n" +
                "  },\n" +
                "  \"accuracy\": 50,\n" +
                "  \"name\": \"Google Shoes!\",\n" +
                "  \"phone_number\": \"(02) 9374 4000\",\n" +
                "  \"address\": \"48 Pirrama Road, Pyrmont, NSW 2009, Australia\",\n" +
                "  \"types\": [\"shoe_store\"],\n" +
                "  \"website\": \"http://www.google.com.au/\",\n" +
                "  \"language\": \"en-AU\"\n" +
                "}";
        return body1;
    }

  /*  public static String authentication(){
        String bodyU = "{\"username\": \"juileemadaye\", \"password\": \"Smera568\" }";
        return bodyU;
    }

    public static String createBug(){
        String createBugBody = "{\"fields\": {\n" +
                "        \"project\": {\n" +
                "          \"key\": \"UD\"\n" +
                "        },\n" +
                "        \"summary\": \"Debit card defect\",\n" +
                "        \"description\":\"creating my second bug\",\n" +
                "        \"issuetype\": {\n" +
                "          \"name\": \"Bug\"\n" +
                "        }\n" +
                "       \n" +
                "        \n" +
                "}\n" +
                "}";
        return createBugBody;
    }

    public static String addComment(){
        String comment = "{\n" +
                "      \"body\": \"comment from automation\",\n" +
                "      \"visibility\": {\n" +
                "        \"type\": \"role\",\n" +
                "        \"value\": \"Administrators\"\n" +
                "      }\n" +
                " }";
        return comment;
    }

    public static String updateComment(){
        String comment = "{\n" +
                "      \"body\": \"comment UPDATED from automation\",\n" +
                "      \"visibility\": {\n" +
                "        \"type\": \"role\",\n" +
                "        \"value\": \"Administrators\"\n" +
                "      }\n" +
                " }";
        return comment;
    }*/


}
