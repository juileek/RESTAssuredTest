package jiraAPI;

public class Resources {


   /* public static String placePostData(){
        String res = "/maps/api/place/add/json";
        return res;
    }

    public static String placeDeleteData(){
        String delres= "/maps/api/place/delete/json";
        return delres;
    }

    public static String nearbyPlaces(){
        String nearBy="/maps/api/place/nearbysearch/json";
        return nearBy;
    }

    public static String addPlace(){
        String addPlace = "/maps/api/place/add/json";
        return addPlace;
    }

    public static String addPlaceXMl(){
        String addPlace = "/maps/api/place/add/xml";
        return addPlace;

    }*/

    public static String jiraResource(){
        String jiraResourceauth = "rest/auth/1/session";
        return jiraResourceauth;
    }

    public static String createDefectResource(){
        String createDefect= "rest/api/2/issue";
        return createDefect;
    }

    public static String deleteDefect(){
        String deleteBug = "rest/api/2/issue/";
        return deleteBug;
    }

    public static String addComment(){
        String addComment = "rest/api/2/issue/"+Helper.createJiraDefect()+"/comment";
        return addComment;
    }

    public static String updateComment(){
        String updateComment = addComment()+"/"+Helper.addComment();
        return updateComment;

    }







    }
