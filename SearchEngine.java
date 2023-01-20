import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

class SearchHandler implements URLHandler {
    List<String> words = new ArrayList<String>();

    @Override
    public String handleRequest(URI url) {
        String urlPath = url.getPath();
        if(urlPath.equals("/")){
            return "Vincent's search engine";
        }
        if(urlPath.equals("/add")){
            String[] parameters = url.getQuery().split("=");
            if (parameters[0].equals("s")){
                words.add(parameters[1]);
                return String.format("Added %s", parameters[1]);
            }
        }
        if(urlPath.equals("/search")){
            String[] parameters = url.getQuery().split("=");
            if (parameters[0].equals("s")){
                if(words.contains(parameters[1])){
                    return String.format("Found %s", parameters[1]);
                }
                return String.format("Did not find %s", parameters[1]);
            }
        }

        System.out.println("Path: " + url.getPath());
        return "404 Not Found!";
    }

}

class SearchEngine {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new SearchHandler());
    }
}
